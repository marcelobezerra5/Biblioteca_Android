package dan.tesco.buscabiblioteca.async;

import android.app.ProgressDialog;
import android.util.Log;

import com.loopj.android.http.*;


public class LibraryHttpClient {
	
	// Url de la web que contiene el webservice
	//private static final String BASE_URL = "http://bibliotecanico.16mb.com/";
	private static final String BASE_URL = "http://192.168.1.94/webservice/";
	//private static final String BASE_URL = "http://192.168.1.94/biblioteca/";
	
	//Dialogo que muestra "Autenticando" mientras la app hace la petición al servidor
	static ProgressDialog pDialog;
	
	  private static AsyncHttpClient client = new AsyncHttpClient();

	  // post orginalmente estaba formado igual que get.
	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  client.get(getAbsoluteUrl(url), params, responseHandler);
	      Log.e("asyncget",getAbsoluteUrl(url)+params);
	  }

	  public static void post(String url, RequestParams params , AsyncHttpResponseHandler responseHandler) {
		  client.post(getAbsoluteUrl(url), params, responseHandler);
	      
	      Log.e("asyncpost",getAbsoluteUrl(url)+params);
	  }

	  private static String getAbsoluteUrl(String relativeUrl) {
	      return BASE_URL + relativeUrl;
	  }
}
