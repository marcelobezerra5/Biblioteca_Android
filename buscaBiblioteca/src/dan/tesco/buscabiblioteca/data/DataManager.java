package dan.tesco.buscabiblioteca.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DataManager {
	public static final String PREFS_NAME = "LoginPrefs";
	private String user;
	private String pass;
	
	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}

	public void savePreferences(Context context, String usr, String psw){
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("logged", "logged");
		editor.putString("prefUser", usr);
		editor.putString("prefPass", psw);
		Log.e("user",editor.putString("prefUser", usr).toString());
		editor.commit();
	}
	public boolean getPreferences(Context context){
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		if (settings.getString("logged", "").toString().equals("logged")) {
			this.user = settings.getString("prefUser", "");
			this.pass = settings.getString("prefPass", "");
			return true;
	}else{
		return false;
	}
}
	public void deletePeferences(Context context) {
		// TODO Auto-generated method stub
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.remove("logged");
		editor.remove("prefUser");
		editor.remove("prefPass");
		editor.commit();
	}
	
}