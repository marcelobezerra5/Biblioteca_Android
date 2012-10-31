package dan.tesco.buscabiblioteca.async;

import android.util.Log;

import com.loopj.android.http.*;

public class LibraryHttpClient {

	// Url de la web que contiene el webservice

	// private static final String BASE_URL = "http://bibliotecanico.16mb.com/";
	private static final String BASE_URL = "http://192.168.1.94/webservice/";
	// private static final String BASE_URL = "http://192.168.1.94/biblioteca/";

	// instanciamos objeto client de la clase AsyncHttpClient
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.setTimeout(10000);// 10 segundos antes de mandar error
		client.get(getAbsoluteUrl(url), params, responseHandler);
		Log.e("asyncget", getAbsoluteUrl(url) + params);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.setTimeout(10000);// 10 segundos antes de mandar error
		client.post(getAbsoluteUrl(url), params, responseHandler);
		Log.e("asyncpost", getAbsoluteUrl(url) + params);
	}

	// Método para obtener la ruta completa del webservice
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}
