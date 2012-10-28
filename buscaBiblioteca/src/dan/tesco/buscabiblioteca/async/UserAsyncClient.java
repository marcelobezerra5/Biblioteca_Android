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
import dan.tesco.buscabiblioteca.data.DataManager;

public class UserAsyncClient {
	//private static String login_tag = "login";
	//private String login_url = "webservice/login/acces.php?";
	ProgressDialog pDialog;
	//private String login_url = "index.php/api/example/user?";
	private String login_url = "user?";
	private DataManager preferences= new DataManager();
	
	
	 public void loginUser( final String user, final String pass, final Context context) throws JSONException {
		 RequestParams params = new RequestParams();
		 params.put("usuario", user);
		 params.put("password", pass);
		 Log.e("restclientUsage","pass params");
	        LibraryHttpClient.post(login_url, params, new JsonHttpResponseHandler() {
	        	@Override
	        	public void onStart(){
	        		pDialog = new ProgressDialog(context);
					pDialog.setMessage("Autenticando....");
					pDialog.setIndeterminate(false);
					pDialog.setCancelable(false);
					pDialog.show();
	        	}
	            @Override
	            public void onSuccess(JSONArray response) { //para resultados del tipo [{logstatus:0}]
	                // Pull out the first event on the public timeline
	                JSONObject firstEvent;
					try {
						firstEvent = response.getJSONObject(0);
						int logstatus = firstEvent.getInt("logstatus");
						Log.e("string"," "+logstatus);
						if (logstatus==1){
							Toast toast1 = Toast.makeText(context,"Login correcto", Toast.LENGTH_SHORT);
							toast1.show();	
						}if (logstatus==0){
							Toast toast1 = Toast.makeText(context,"Login incorrecto", Toast.LENGTH_SHORT);
							toast1.show();	
						}
						
						Log.e("error","ok");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.e("JSONe","fail");
						Toast toast1 = Toast.makeText(context,"server incorrecto", Toast.LENGTH_SHORT);
						toast1.show();	
					}
	                
					//guardar aqui datos en el cel
	                // Do something with the response
	                //System.out.println(tweetText);
					Log.e("onSuccessJSONArray","ok");
	            }
	            @Override
	            public void onSuccess(JSONObject response) { // para resultados de tipo {logstatus:0}
	                // Pull out the first event on the public timeline
	                //JSONObject firstEvent;
					try {
						//firstEvent = response.getJSONObject("logstatus");
						Log.e("logstatus",response.toString());
						int logstatus = response.getInt("logstatus");
						Log.e("string"," "+logstatus);
						if (logstatus==1){
							Toast toast1 = Toast.makeText(context,"Login correcto", Toast.LENGTH_SHORT);
							toast1.show();	
							preferences.savePreferences(context, user, pass);
							loadIntent(context, user);
						}if (logstatus==0){
							Toast toast1 = Toast.makeText(context,"Login incorrecto", Toast.LENGTH_SHORT);
							toast1.show();	
						}
						
						Log.e("error","ok");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.e("JSONe","fail");
						Toast toast1 = Toast.makeText(context,"server incorrecto", Toast.LENGTH_SHORT);
						toast1.show();	
					}
	                
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
	
	public void loadIntent(Context context, String usr){
		Intent i=new Intent(context, SearchActivity.class);
		i.putExtra("usuario",usr);
		context.startActivity(i);
		
		((Activity) context).finish();
		
	}
}
