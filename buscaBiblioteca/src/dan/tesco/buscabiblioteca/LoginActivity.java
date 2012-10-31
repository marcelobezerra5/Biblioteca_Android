package dan.tesco.buscabiblioteca;

import org.json.JSONException;

import dan.tesco.buscabiblioteca.async.AsyncUser;
import dan.tesco.buscabiblioteca.data.UserData;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import dan.tesco.buscabiblioteca.LoginActivity;
import dan.tesco.buscabiblioteca.R;

public class LoginActivity extends Activity {
	EditText user;
	EditText pass;
	Button button;
	ProgressDialog pDialog;
	public static final String PREFS_NAME = "LoginPrefs";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        
        user = (EditText) findViewById(R.id.editTextLoginUser);
        pass = (EditText) findViewById(R.id.editTextLoginPassword);
        button = (Button) findViewById(R.id.buttonLogin);
        
        UserData settings = new UserData();
		if (settings.getPreferences(LoginActivity.this)) {
			String usuario = settings.getUser();
			String passw = settings.getPass();
			AsyncUser user=new AsyncUser();
			try {
				user.loginUser(usuario, passw,LoginActivity.this);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
        
        button.setOnClickListener(new View.OnClickListener() {
			
			//@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String usr= user.getText().toString();
		        String psw = pass.getText().toString();

		        AsyncUser user=new AsyncUser();
		        try {
		        	Log.e("LoginActivity","loginuser");
					user.loginUser(usr, psw,LoginActivity.this);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
}
