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

public class BookAsyncClient {
	//private static String login_tag = "login";
	//private String login_url = "webservice/login/acces.php?";
	ProgressDialog pDialog;
	//private String login_url = "index.php/api/example/user?";
	private String ws_url = "book?";
	private ArrayList<Book> items=new ArrayList<Book>();
	
	 public void searchBook( final String searchString, final Context context, final ListView lv) throws JSONException {
		 RequestParams params = new RequestParams();
		 params.put("search", searchString);
		 Log.e("searchbook","pass params");
	        LibraryHttpClient.post(ws_url, params, new JsonHttpResponseHandler() {
	        	@Override
	        	public void onStart(){
	        		pDialog = new ProgressDialog(context);
					pDialog.setMessage("Buscando....");
					pDialog.setIndeterminate(false);
					pDialog.setCancelable(false);
					pDialog.show();
	        	}
	            @Override
	            public void onSuccess(JSONArray response) { //para resultados del tipo [{logstatus:0}]
	            	//[{"autor":"Alexander Dumas","titulo":"Three Musketeers","prestado":"0"}
	            	//,
	            	//{"autor":"ficticio","titulo":"one two three","prestado":"1"}]
	            	
	                JSONObject firstEvent;
					try {
						//firstEvent = response.getJSONObject(0);
						//String error = firstEvent.getString("error");
//						String author = firstEvent.getString("autor");
//	            		String titulo = firstEvent.getString("titulo");
//	            		int status = firstEvent.getInt("prestado");
//	            		//setItem(firstEvent);
//	            		setItems(response);
//						Log.e("string"," "+author);
//						Log.e("string"," "+titulo);
//						Log.e("string"," "+status);
//						ArrayList<Book> itemsCompra = getItems();
//						BookListAdapter adapter = new BookListAdapter((Activity)context, itemsCompra);
//				        lv.setAdapter(adapter);
//						if (error.equals("not found")){
//							Toast toast1 = Toast.makeText(context,"resultado no encontrado", Toast.LENGTH_SHORT);
//							toast1.show();	
//						}else{
							setItems(response);
							ArrayList<Book> itemsCompra = getItems();
							BookListAdapter adapter = new BookListAdapter((Activity)context, itemsCompra);
					        lv.setAdapter(adapter);
						//}
						
						Log.e("error","ok");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.e("JSONe","fail");
						Toast toast1 = Toast.makeText(context,"server incorrecto", Toast.LENGTH_SHORT);
						toast1.show();	
					}
	            	Log.e("onSuccessJSONArray","ok");
	            	//TODO: estas lineas dan error
					//ListView lv = (ListView)view.findViewById(R.id.listView1);
			        //ArrayList<Book> itemsCompra = obtenerItems();
			        //BookListAdapter adapter = new BookListAdapter((Activity)context, itemsCompra);
			        //probable linea chafa
			        //lv.setAdapter(adapter);
	                
					//guardar aqui datos en el cel
	                // Do something with the response
	                //System.out.println(tweetText);
					Log.e("onSuccessJSONArray","ok");
	            }
	            @Override
	            public void onSuccess(JSONObject response) { // para resultados de tipo {logstatus:0}
	                // Pull out the first event on the public timeline
	                JSONObject firstEvent;
					try {
//						//firstEvent = response.getJSONObject("error");
//						Log.e("logstatus",response.toString());
						String logstatus = response.getString("error");
//						Log.e("string"," "+logstatus);
						if (logstatus.equals("not found")){
							Toast toast1 = Toast.makeText(context,"resultado no encontrado", Toast.LENGTH_SHORT);
							toast1.show();	
							//preferences.savePreferences(context, searchString);
							//loadIntent(context, user);
						}//if (logstatus==0){
//							Toast toast1 = Toast.makeText(context,"Login incorrecto", Toast.LENGTH_SHORT);
//							toast1.show();	
//						}
//						
//						Log.e("error","ok");
					} catch (JSONException e) {
//						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.e("JSONe","fail");
						Toast toast1 = Toast.makeText(context,"server incorrecto", Toast.LENGTH_SHORT);
						toast1.show();	
					}
	            	Log.e("onSuccessJSONObject","ok");
					//ListView lv = (ListView)view.findViewById(R.id.listView1);
			        ArrayList<Book> itemsCompra = getItems();
			        BookListAdapter adapter = new BookListAdapter((Activity)context, itemsCompra);
			        lv.setAdapter(adapter);
	                
					//guardar aqui datos en el cel
	                // Do something with the response
	                //System.out.println(tweetText);
					Log.e("onSuccessJSONObject","ok");
	            }
	            @Override
	            public void onFailure (Throwable e,JSONArray errorResponse){
	            	Toast toast1 = Toast.makeText(context,"hubo pedos con los datos", Toast.LENGTH_SHORT);
					toast1.show();
					Log.e("errorArray",errorResponse.toString());
	            }
	            @Override
	            public void onFailure (Throwable e,JSONObject errorResponse){
	            	Toast toast1 = Toast.makeText(context,"hubo pedos con los datos", Toast.LENGTH_SHORT);
					toast1.show();
					Log.e("errorObject",errorResponse.toString());
	            }
	            @Override
			     public void onFailure(Throwable e, String response) {
			         // Response failed :(
	            	Toast toast1 = Toast.makeText(context,"hubo pedos con el servidor", Toast.LENGTH_SHORT);
					toast1.show();
			    	 Log.e("onFailure","overrided"+e);
			     }
	            @Override
			     public void onFinish() {
			         // Completed the request (either success or failure)
			    	 Log.e("onFinish","Termina y nadie hace nada");
			    	 pDialog.dismiss();//ocultamos progess dialog.
			     }
	        });
	        Log.e("despues del client usage","termina");
	    }
	
	 public ArrayList<Book> getItems() {
//	        ArrayList<Book> items = new ArrayList<Book>();
//	             
//	        items.add(new Book(1, "Andres calamaro","Matematicas 1", "drawable/ok"));
//	        items.add(new Book(2, "Tecla Varela","Matematicas 2", "drawable/ok"));
//	        items.add(new Book(3, "Memo Jarás", "Histria", "drawable/no"));
//	        items.add(new Book(3, "Mela Peláez","civismo", "drawable/ok"));
//	             
//	        return this.items=items;
		 return this.items;
	      }
	 public void setItem(JSONObject libro) throws JSONException{
		 //ArrayList<Book> items = new ArrayList<Book>();
		 
		 String author = libro.getString("autor");
 		String titulo = libro.getString("titulo");
 		int status = libro.getInt("prestado");
 		Log.e("string"," "+author);
		Log.e("string"," "+titulo);
		Log.e("string"," "+status);
        this.items.add(new Book(1, "Andres calamaro","Matematicas 1", "drawable/ok"));
        this.items.add(new Book(2, "Tecla Varela","Matematicas 2", "drawable/ok"));
        this.items.add(new Book(3, "Memo Jarás", "Histria", "drawable/no"));
        this.items.add(new Book(3, "Mela Peláez","civismo", "drawable/ok"));
 		this.items.add(new Book(1, author,titulo, status == 0?"drawable/ok":"drawable/no"));
		//return this.items=items;
		 
	 }
	 public void setItems(JSONArray libros) throws JSONException{
		 //String[] clientes = new String[libros.length()];
		 
	        for(int i=0; i<libros.length(); i++)
	        {
	            JSONObject libro = libros.getJSONObject(i);
	 
	            String author = libro.getString("autor");
	     		String titulo = libro.getString("titulo");
	     		int status = libro.getInt("prestado");
	 
	     		this.items.add(new Book(i, author,titulo, status == 0?"drawable/ok":"drawable/no"));
	        }
	 }
}
