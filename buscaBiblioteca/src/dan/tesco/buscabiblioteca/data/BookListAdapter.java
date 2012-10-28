package dan.tesco.buscabiblioteca.data;

import java.util.ArrayList;

import dan.tesco.buscabiblioteca.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookListAdapter extends BaseAdapter{
	protected Activity activity;
	protected ArrayList<Book> items;
	
	public BookListAdapter(Activity activity, ArrayList<Book> items){
		this.activity=activity;
		this.items=items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi=convertView;
        
	    if(convertView == null) {
	      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      vi = inflater.inflate(R.layout.list_item_books, null);
	    }
	             
	    Book item = items.get(position);
	         
	    ImageView image = (ImageView) vi.findViewById(R.id.imageViewPrestado);
	    int imageResource = activity.getResources().getIdentifier(item.getDispImagen(), null, activity.getPackageName());
	    image.setImageDrawable(activity.getResources().getDrawable(imageResource));
	         
	    TextView nombre = (TextView) vi.findViewById(R.id.textViewTitle);
	    nombre.setText(item.getTitulo());
	         
	    TextView tipo = (TextView) vi.findViewById(R.id.textViewAuthor);
	    tipo.setText(item.getAutor());
	 
	    return vi;
		//return null;
	}

}
