package dan.tesco.buscabiblioteca;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import dan.tesco.buscabiblioteca.R;
import dan.tesco.buscabiblioteca.async.AsyncBook;
import dan.tesco.buscabiblioteca.data.UserData;


public class SearchActivity extends Activity {

	public static final String PREFS_NAME = "LoginPrefs";
	String user;
	TextView txt_user;
	Button boton;
	EditText search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window wa = getWindow(); // text window
        wa.setTitle(user); //text window
        setContentView(R.layout.activity_search);
        
//txt_user= (TextView) findViewById(R.id.textViewUser);          
        
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   user  = extras.getString("usuario");//usuario
     	   setTitle("Bienvenid@ "+user);
        }else{
     	   user="error";
     	   setTitle(user);
     	   }
        
 //       txt_user.setText(user);//cambiamos texto al nombre del usuario logueado
        boton = (Button) findViewById(R.id.buttonSearch);
        boton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//BookAsync...
				search= (EditText)findViewById(R.id.editTextSearch);
				String searchString = search.getText().toString();
				ListView lv = (ListView)findViewById(R.id.listViewSearch);
				AsyncBook bs= new AsyncBook();
				try {
					bs.searchBook(searchString, SearchActivity.this, lv);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
            
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search, menu);
        return true;
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.logout) {
			UserData preferences = new UserData();
			preferences.deletePeferences(SearchActivity.this);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
    
    
}
