package com.example.uf_happy_hour;


import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	public static int g = 0;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	//showToast("sup ma ninJ1");
    	super.onCreate(savedInstanceState);
    	//showToast("sup ma ninJA2");
    	// Set  view layer
    	setContentView(R.layout.main_base);
    	
    	/*
    	 * 
    	 * Get list of bars/flags/times here
    	 */
    	
    	// Add a header to the screen
    	ListView lv = (ListView)findViewById(R.id.ListViewId);
    	LayoutInflater inflater = getLayoutInflater();
    	View header = inflater.inflate(R.layout.main_header, (ViewGroup) findViewById(R.id.header_layout_root));
    	lv.addHeaderView(header, null, false);

    	
    	
    	/*
    	 * Create custom arrayadaptor
    	 */
    	
    	
    	
    	
    	
    	
    	
    	///*
    	 //* Hardcoded stuff
    	 
    	Vector<String> v_string = new Vector<String>();
    	v_string.add("Salty Dog Saloon						   1pm-7pm");
    	v_string.add("BJ's Brewhouse						   4pm-6pm");
    	v_string.add("The Midnight						   10pm-Midnight, duh");
    	
    	Vector<String> hh_times = new Vector<String>();
    	hh_times.add("4pm-6pm");
    	hh_times.add("10pm-Midnight, duh");
    	
    	ListView listView = (ListView) findViewById(R.id.ListViewId);
    	listView.setAdapter(new ListViewAdapter(this, R.layout.main_text_view,
    			v_string));
    	ListView listView2 = (ListView) findViewById(R.id.ListViewId);
    	//listView2.setAdapter(new ListViewAdapter(this, R.layout.main_text_view,
    		//    	hh_times));
    	
    	
    	//Adding a header adds a section section header
    	
    	//*/
    }
    
    
    //Adapter 
    public class ListViewAdapter extends ArrayAdapter<String>
    {

    	private java.util.Vector<String> attr_string_vector;
    	private Context context;
    	
    	//private int flag = 0;
    	MainActivity m = new MainActivity();
    	
    	public ListViewAdapter(Context context, int textViewResourceId,
    			java.util.Vector<String> constr_string_vector)
    	{
    		super(context, textViewResourceId, constr_string_vector);
    		this.context = context;
    		//this.textViewResourceId = textViewResourceId;
    		this.attr_string_vector = constr_string_vector;
    		//this.buildings = buildings;
    	}
	
		@Override
		// overriding the getView method with our own version
		// to set values of UI components as we choose
		// when the app displays a listview(one item of list), it will call
		// getView
		public View getView(int position, View convertView, ViewGroup parent)
		{
			//showToast("in get view");
			
		    View v = convertView;
	
		    // null check
		    if (v == null)
		    {
			LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.main_cell_info, null);
		    }
	
		    //Building bldng = buildings.get(position);
		    String sup = attr_string_vector.get(position);
		    
		    // For the bar's name
		    if (sup != null)
		    {
		    	TextView t = (TextView) v.findViewById(R.id.testText);
	
		    	if (t != null)
		    	{
		    		//t.setText("Sup ma ninJAAAaAAA");
		    		t.setText(sup);
		    		//MainActivity m = new MainActivity();
		    	
		        	
		    		//m.g = 1;
		    	}
		    }
		    
		    // For the happy hour time
		    if (sup != null && (sup == "4pm-6pm" || sup == "10pm-Midnight, duh" || sup == "1pm-7pm"))
		    {
		    	TextView t = (TextView) v.findViewById(R.id.hh_time);
	
		    	if (t != null)
		    	{
		    		// Set the text at this particular row cell
		    		//t.setText(sup);
		    	}
		    }
		    
		    //ImageView myImgView = 
		    //myImgView.setImageDrawable(getResources().getDrawable(R.drawable.dasda));
		    
		    return v;
		}
    } //end adapter class

    private void showToast(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
