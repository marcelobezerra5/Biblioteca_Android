package dan.tesco.buscabiblioteca;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import dan.tesco.buscabiblioteca.R;
import dan.tesco.buscabiblioteca.async.AsyncBook;
import dan.tesco.buscabiblioteca.data.Book;
import dan.tesco.buscabiblioteca.data.UserData;


public class SearchActivity extends Activity {

	public static final String PREFS_NAME = "LoginPrefs";
	String user;
	Button boton;
	EditText search;
	ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        search= (EditText)findViewById(R.id.editTextSearch);
        lv = (ListView)findViewById(R.id.listViewSearch);
        boton = (Button) findViewById(R.id.buttonSearch);
        
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   user  = extras.getString("usuario");//usuario
     	   setTitle("Bienvenid@ "+user);
        }else{
     	   user="error";
     	   setTitle(user);
     	   }
        
        boton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//BookAsync...
				String searchString = search.getText().toString();
				AsyncBook bs= new AsyncBook();
				try {
					bs.searchBook(searchString, SearchActivity.this, lv);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

            lv.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(),
					//	      "Click ListItem Number " + position, Toast.LENGTH_LONG)
					//	      .show();
					Book libro= (Book) parent.getItemAtPosition(position);
					Intent i=new Intent(android.content.Intent.ACTION_SEND);
					i.setType("text/plain");
					i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Pedido libro");
					i.putExtra(android.content.Intent.EXTRA_EMAIL,"biblioteca@tesco.mx");
					i.putExtra(android.content.Intent.EXTRA_TEXT, "quiero apartar el siguiente libro: ");
					i.putExtra(android.content.Intent.EXTRA_TEXT,"Título: "+libro.getTitulo());
					i.putExtra(android.content.Intent.EXTRA_TEXT,"Autor: "+libro.getAutor());
					startActivity(Intent.createChooser(i,"Share via"));
					return true;
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
