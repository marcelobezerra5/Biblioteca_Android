package dan.tesco.buscabiblioteca.async;

import org.json.*;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.*;

import dan.tesco.buscabiblioteca.SearchActivity;
import dan.tesco.buscabiblioteca.data.UserData;

public class AsyncUser {

	ProgressDialog pDialog;
	// private String webServiceUrl = "index.php/api/example/user?";
	private String webServiceUrl = "user?";
	private UserData preferences = new UserData();

	public void loginUser(final String user, final String pass,
			final Context context) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("usuario", user);
		params.put("password", pass);

		Log.e("restclientUsage", "pass params");
		LibraryHttpClient.post(webServiceUrl, params,
				new JsonHttpResponseHandler() {
					@Override
					public void onStart() {
						// al inicio de la tarea mostramos el dialogo con
						// mensaje "autenticando"
						pDialog = new ProgressDialog(context);
						pDialog.setMessage("Autenticando....");
						pDialog.setIndeterminate(false);
						pDialog.setCancelable(false);
						pDialog.show();
					}

					@Override
					public void onSuccess(JSONArray response) {
						// para resultados del tipo [{logstatus:0}]
						// Obten el primer objeto, en este caso logstatus
						JSONObject loginStatus;
						try {
							loginStatus = response.getJSONObject(0);
							int logstatus = loginStatus.getInt("logstatus");
							Log.e("string", " " + logstatus);
							if (logstatus == 1) {
								Toast toast1 = Toast.makeText(context,
										"Login correcto", Toast.LENGTH_SHORT);
								toast1.show();
							}
							if (logstatus == 0) {
								Toast toast1 = Toast.makeText(context,
										"Login incorrecto", Toast.LENGTH_SHORT);
								toast1.show();
							}

							Log.e("error", "ok");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.e("JSONe", "fail");
							Toast toast1 = Toast.makeText(context,
									"server incorrecto", Toast.LENGTH_SHORT);
							toast1.show();
						}

						// guardar aqui datos en el cel
						Log.e("onSuccessJSONArray", "ok");
					}

					@Override
					public void onSuccess(JSONObject response) {
						// para resultados de tipo {logstatus:0}
						try {
							Log.e("logstatus", response.toString());
							int logstatus = response.getInt("logstatus");
							Log.e("string", " " + logstatus);
							if (logstatus == 1) {
								Toast toast1 = Toast.makeText(context,
										"Login correcto", Toast.LENGTH_SHORT);
								toast1.show();
								preferences
										.savePreferences(context, user, pass);
								loadIntent(context, user);
							}
							if (logstatus == 0) {
								Toast toast1 = Toast.makeText(context,
										"Login incorrecto", Toast.LENGTH_SHORT);
								toast1.show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.e("JSONe", "fail");
							Toast toast1 = Toast.makeText(context,
									"server incorrecto", Toast.LENGTH_SHORT);
							toast1.show();
						}

						// guardar aqui datos en el cel
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
								"servidor no disponible temporalmente",
								Toast.LENGTH_SHORT);
						toast1.show();
						Log.e("onFailure", "" + e);
					}

					@Override
					public void onFinish() {
						// Completed the request (either success or failure)
						// al final de la tarea cerramos el dialogo para que el
						// usuario pueda seguir trabajando
						Log.e("onFinish", "Termina y nadie hace nada");
						pDialog.dismiss();// ocultamos progess dialog.
					}
				});
		Log.e("despues de asyncClient", "termina");
	}

	public void loadIntent(Context context, String usr) {
		Intent i = new Intent(context, SearchActivity.class);
		i.putExtra("usuario", usr);
		context.startActivity(i);

		((Activity) context).finish();

	}
}
