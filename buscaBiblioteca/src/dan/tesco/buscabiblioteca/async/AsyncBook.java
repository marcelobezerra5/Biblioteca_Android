package dan.tesco.buscabiblioteca.async;

import java.util.ArrayList;

import org.json.*;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import dan.tesco.buscabiblioteca.data.Book;
import dan.tesco.buscabiblioteca.data.BookListAdapter;

public class AsyncBook {

	ProgressDialog pDialog;
	// private String webServiceUrl = "index.php/api/example/user?";
	private String webServiceUrl = "book?";
	private ArrayList<Book> books = new ArrayList<Book>();

	public void searchBook(final String searchString, final Context context,
			final ListView lv) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("search", searchString);
		Log.e("searchbook", "pass params");
		LibraryHttpClient.post(webServiceUrl, params,
				new JsonHttpResponseHandler() {
					@Override
					public void onStart() {
						pDialog = new ProgressDialog(context);
						pDialog.setMessage("Buscando....");
						pDialog.setIndeterminate(false);
						pDialog.setCancelable(false);
						pDialog.show();
					}

					@Override
					public void onSuccess(JSONArray response) {
						// para resultados del tipo
						// [{"autor":"Alexander Dumas","titulo":"Three Musketeers","prestado":"0"}
						// ,
						// {"autor":"ficticio","titulo":"one two three","prestado":"1"}]
						// cuando encuentra resultados regresa JSONArray

						try {
							setBooks(response);
							ArrayList<Book> listaDeLibros = getBooks();
							BookListAdapter adapter = new BookListAdapter(
									(Activity) context, listaDeLibros);
							lv.setAdapter(adapter);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.e("JSONe", "fail");
							Toast toast1 = Toast.makeText(context,
									"server incorrecto", Toast.LENGTH_SHORT);
							toast1.show();
						}

						Log.e("onSuccessJSONArray", "ok");
					}

					@Override
					public void onSuccess(JSONObject response) {
						// para resultados de tipo {"error":"not found"}
						// Cuando no encuentra nada regresa JSONObject
						try {
							String logstatus = response.getString("error");
							if (logstatus.equals("not found")) {
								Toast toast1 = Toast.makeText(context,
										"resultado no encontrado",
										Toast.LENGTH_SHORT);
								toast1.show();
							}
						} catch (JSONException e) {
							// // TODO Auto-generated catch block
							e.printStackTrace();
							Log.e("JSONObject catch", "fail");
							Toast toast1 = Toast.makeText(context,
									"server incorrecto", Toast.LENGTH_SHORT);
							toast1.show();
						}
						// para limpiar la lista en caso de que exista busqueda
						// anterior
						ArrayList<Book> itemsCompra = getBooks();
						BookListAdapter adapter = new BookListAdapter(
								(Activity) context, itemsCompra);
						lv.setAdapter(adapter);

						Log.e("onSuccessJSONObject", "ok");
					}

					@Override
					public void onFailure(Throwable e, JSONArray errorResponse) {
						Toast toast1 = Toast.makeText(context,
								"hubo pedos con los datos", Toast.LENGTH_SHORT);
						toast1.show();
						Log.e("errorArray", errorResponse.toString());
					}

					@Override
					public void onFailure(Throwable e, JSONObject errorResponse) {
						Toast toast1 = Toast.makeText(context,
								"hubo pedos con los datos", Toast.LENGTH_SHORT);
						toast1.show();
						Log.e("errorObject", errorResponse.toString());
					}

					@Override
					public void onFailure(Throwable e, String response) {
						// Response failed :(
						Toast toast1 = Toast.makeText(context,
								"hubo pedos con el servidor",
								Toast.LENGTH_SHORT);
						toast1.show();
						Log.e("onFailure", "overrided" + e);
					}

					@Override
					public void onFinish() {
						// Completed the request (either success or failure)
						Log.e("onFinish", "Termina y nadie hace nada");
						pDialog.dismiss();// ocultamos progess dialog.
					}
				});
		Log.e("despues del client usage", "termina");
	}

	public ArrayList<Book> getBooks() {
		return this.books;
	}

	public void setBooks(JSONArray libros) throws JSONException {

		for (int i = 0; i < libros.length(); i++) {
			JSONObject libro = libros.getJSONObject(i);

			String author = libro.getString("autor");
			String titulo = libro.getString("titulo");
			int status = libro.getInt("prestado");

			this.books.add(new Book(i, author, titulo,
					status == 0 ? "drawable/ok" : "drawable/no",
					status == 0 ? "disponible" : "no disponible"));//agregado para disponibilidad de libro
		}

	}

}
