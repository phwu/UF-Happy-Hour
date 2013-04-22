package com.example.uf_happy_hour;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {
	
	String day;
	String hour;
	public static String barName;	//Pass to ProfilePage
	public static Vector<String> pass = new Vector<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	//showToast("sup ma ninJ1");
    	super.onCreate(savedInstanceState);
    	//showToast("sup ma ninJA2");
    	// Set  view layer
    	setContentView(R.layout.main_base);
    	
    	//Get day and hour from previous Activity
    	Bundle bundle = this.getIntent().getExtras();
    	//hour = bundle.getString("hour");
    	//day = bundle.getString("day");
    	
    	// Test hour/day by hardcoding
    	hour = "18";
    	day = "1";
    	
    	//Convert day int to a String
    	if (day.equals("1"))
    	{
    		day = "Sun";
    	}
    	else if (day.equals("2"))
    	{
    		day = "Mon";    		
    	}
    	else if (day.equals("3"))
    	{
    		day = "Tue";    		
    	}
    	else if (day.equals("4"))
    	{
    		day = "Wed";    		
    	}
    	else if (day.equals("5"))
    	{
    		day = "Thu";    		
    	}
    	else if (day.equals("6"))
    	{
    		day = "Fri";    		
    	}
    	else
    	{
    		day = "Sat";
    	}
    
    	// Debug
    	//showToast(day + ": " + hour);
   
    	
    	
    	/*
    	 * 
    	 * Get list of bars/flags/times here
    	 */
    	
    	// Add a header to the screen
    	ListView lv = (ListView)findViewById(R.id.ListViewId);
    	LayoutInflater inflater = getLayoutInflater();
    	View header = inflater.inflate(R.layout.main_header, (ViewGroup) findViewById(R.id.header_layout_root));
    	lv.addHeaderView(header, null, false);
    	
   


    	DatabaseHelper dh = new DatabaseHelper(this);
    	int latestVer = dh.latestDBVersion();
        int currentVer = dh.getLocalDBVer();
    	
        if (dh.isEmpty()) { //if database tables are empty
        	Log.d("Insert: ", "Inserting BAR DATA ..");

        	try {
        		InputStream is = getResources().openRawResource(R.raw.bars);
        		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        		int i = 0;

        		try {
        			String input = null;
        			i = is.read();
        			while (i != -1) {
        				byteArrayOutputStream.write(i);
        				i = is.read();
        			}
        			
        			input = byteArrayOutputStream.toString();
        			Scanner scanner = new Scanner(input);
        			
        			//debugging
        			/**while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				Log.d("Scanner: ", line);
        			}**/
        			
        			while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				StringTokenizer st = new StringTokenizer(line, ";");
        				String name, address, phone, venue, hoursOpen, specials, fullBar;
        				int ageReq, food;
        				Log.d("TOKENIZER : ", "tokenizer initiated");
        			
        				while (st.hasMoreElements()) {
        					name = st.nextToken();
        					Log.d("name : ", name);
        					address = st.nextToken();
        					Log.d("address : ", address);
        					phone = st.nextToken();
        					Log.d("phone : ", phone);
        					venue = st.nextToken();
        					Log.d("venue : ", venue);
        					hoursOpen = st.nextToken();
        					Log.d("hours open : ", hoursOpen);
        					fullBar = st.nextToken();
        					Log.d("full bar : ", fullBar);
       						specials = st.nextToken();
       						Log.d("specials : ", specials);
       						ageReq = Integer.parseInt(st.nextToken());
       						Log.d("age req : ", Integer.toString(ageReq));
       						food = Integer.parseInt(st.nextToken());
       						Log.d("food : ", Integer.toString(food));
       					
       						Log.d("Adding Bar! ", "bar being added to db!");
       						dh.addBar(new Bar(name, address, phone, venue, hoursOpen, fullBar, specials, ageReq, food));
        				}
        			}
        		}
        		finally {
        			is.close();
        			byteArrayOutputStream.close();
        		}
        	}
        	catch(IOException ex) {
        		Log.d("ERROR", "Error in inserting data from input");
        		ex.printStackTrace();
        	}
        	
        	Log.d("Insert: ", "Inserting SPECIALS DATA ..");
        	
        	try {
        		InputStream is = getResources().openRawResource(R.raw.specials);
        		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        		int i = 0;

        		try {
        			String input = null;
        			i = is.read();
        			while (i != -1) {
        				byteArrayOutputStream.write(i);
        				i = is.read();
        			}
        			
        			input = byteArrayOutputStream.toString();
        			Scanner scanner = new Scanner(input);
        			
        			//debugging
        			/**while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				Log.d("Scanner: ", line);
        			}**/
        			
        			while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				StringTokenizer st = new StringTokenizer(line, ";");
        				String name, loc, day, hhb, hhe, spec;
        				Log.d("TOKENIZER : ", "tokenizer initiated");
        			
        				while (st.hasMoreElements()) {
        					name = st.nextToken();
        					Log.d("name : ", name);
        					loc = st.nextToken();
        					Log.d("loc : ", loc);
        					day = st.nextToken();
        					Log.d("day : ", day);
        					hhb = st.nextToken();
        					Log.d("hhb : ", hhb);
        					hhe = st.nextToken();
        					Log.d("hhe : ", hhe);
        					spec = st.nextToken();
        					Log.d("Spec : ", spec);
       					
       						Log.d("Adding Special! ", "special being added to db!");
       						dh.addSpecial(name, loc, day, hhb, hhe, spec);
        				}
        			}
        		}
        		finally {
        			is.close();
        			byteArrayOutputStream.close();
        		}
        	}
        	catch(IOException ex) {
        		Log.d("ERROR", "Error in inserting data from input");
        		ex.printStackTrace();
        	}
        }
        
        else if (latestVer > currentVer) {
        	dh.upgrade(currentVer,latestVer);
        	
        	Log.d("Insert: ", "Inserting BAR DATA ..");

        	try {
        		InputStream is = getResources().openRawResource(R.raw.bars);
        		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        		int i = 0;

        		try {
        			String input = null;
        			i = is.read();
        			while (i != -1) {
        				byteArrayOutputStream.write(i);
        				i = is.read();
        			}
        			
        			input = byteArrayOutputStream.toString();
        			Scanner scanner = new Scanner(input);
        			
        			//debugging
        			/**while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				Log.d("Scanner: ", line);
        			}**/
        			
        			while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				StringTokenizer st = new StringTokenizer(line, ";");
        				String name, address, phone, venue, hoursOpen, specials, fullBar;
        				int ageReq, food;
        				Log.d("TOKENIZER : ", "tokenizer initiated");
        			
        				while (st.hasMoreElements()) {
        					name = st.nextToken();
        					Log.d("name : ", name);
        					address = st.nextToken();
        					Log.d("address : ", address);
        					phone = st.nextToken();
        					Log.d("phone : ", phone);
        					venue = st.nextToken();
        					Log.d("venue : ", venue);
        					hoursOpen = st.nextToken();
        					Log.d("hours open : ", hoursOpen);
        					fullBar = st.nextToken();
        					Log.d("full bar : ", fullBar);
       						specials = st.nextToken();
       						Log.d("specials : ", specials);
       						ageReq = Integer.parseInt(st.nextToken());
       						Log.d("age req : ", Integer.toString(ageReq));
       						food = Integer.parseInt(st.nextToken());
       						Log.d("food : ", Integer.toString(food));
       					
       						Log.d("Adding Bar! ", "bar being added to db!");
       						dh.addBar(new Bar(name, address, phone, venue, hoursOpen, fullBar, specials, ageReq, food));
        				}
        			}
        		}
        		finally {
        			is.close();
        			byteArrayOutputStream.close();
        		}
        	}
        	catch(IOException ex) {
        		Log.d("ERROR", "Error in inserting data from input");
        		ex.printStackTrace();
        	}
        	
        	Log.d("Insert: ", "Inserting SPECIALS DATA ..");
        	
        	try {
        		InputStream is = getResources().openRawResource(R.raw.specials);
        		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        		int i = 0;

        		try {
        			String input = null;
        			i = is.read();
        			while (i != -1) {
        				byteArrayOutputStream.write(i);
        				i = is.read();
        			}
        			
        			input = byteArrayOutputStream.toString();
        			Scanner scanner = new Scanner(input);
        			
        			//debugging
        			/**while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				Log.d("Scanner: ", line);
        			}**/
        			
        			while (scanner.hasNextLine()) {
        				String line = scanner.nextLine();
        				StringTokenizer st = new StringTokenizer(line, ";");
        				String name, loc, day, hhb, hhe, spec;
        				Log.d("TOKENIZER : ", "tokenizer initiated");
        			
        				while (st.hasMoreElements()) {
        					name = st.nextToken();
        					Log.d("name : ", name);
        					loc = st.nextToken();
        					Log.d("loc : ", loc);
        					day = st.nextToken();
        					Log.d("day : ", day);
        					hhb = st.nextToken();
        					Log.d("hhb : ", hhb);
        					hhe = st.nextToken();
        					Log.d("hhe : ", hhe);
        					spec = st.nextToken();
        					Log.d("Spec : ", spec);
       					
       						Log.d("Adding Special! ", "special being added to db!");
       						dh.addSpecial(name, loc, day, hhb, hhe, spec);
        				}
        			}
        		}
        		finally {
        			is.close();
        			byteArrayOutputStream.close();
        		}
        	}
        	catch(IOException ex) {
        		Log.d("ERROR", "Error in inserting data from input");
        		ex.printStackTrace();
        	}
        }
    	
    	
    	// Reading all bars - debugging
        Log.d("Reading: ", "Reading all bars..");
        
        
        // right now, using getAllBars! Can filter to show only bars with happy hours right now but
        // how do we handle bars with no happy hours?
        List<Bar> bars = dh.getAllBars();      
        
        
        // Vector of bar names (Strings) for display in MainActivity ListView
        Vector<String> v_string = new Vector<String>();
        //Vector<String> hh_hours = new Vector<String>();
        
        for (Bar b : bars) {
        	//will be used for MainActivity list
        	v_string.add(b.getName());
        	//hh_hours.add(b.getHoursOpen());
        	
            String log = b.getName() + b.getPhoneNumber() + b.getAddress() + b.getVenue() +
            		b.getHoursOpen() + b.getAlcohol() + b.getSpecials() + b.getAgeReq() +
            		b.getFood();
            // Writing bars to log
            Log.d("Name: ", log);
        }
        Log.d("---", "---");
        
        List<String> specials = dh.getAllSpecials();
        for (String s: specials) {
        	Log.d("Specials: ", s);
        }
        Log.d("---", "---");
        List<String> now = dh.getBarsNow("Mon", "16");
        for (String s: now) {
        	Log.d("NOW ", s);
        }
        Log.d("---", "---");
        List<String> days = dh.getBarsByDay("Sun");
        for (String s: days) {
        	Log.d("DAYS ", s);
        }
        Log.d("---", "---");
        List<String> locs = dh.getBarsByLoc("Midtown");
        for (String s: now) {
        	Log.d("LOCS ", s);
        }
        Log.d("---", "---");

    
    	 // Hardcoded stuff    	 
    	/*
    	Vector<String> hh_times = new Vector<String>();
    	hh_times.add("4pm-6pm");
    	hh_times.add("10pm-Midnight, duh");
    	*/
        
        
        /*
         * Call getBarsNow and convert List to Vector         
         */
        
        List<String> list = dh.getBarsNow(day, hour);
        //Vector<String> barsNow = new Vector<String>();
        
        //List to array
        String[] array = new String[list.size()];
        array = list.toArray(array);
        //Array to vector
        Vector<String> barsNow = new Vector<String>(Arrays.asList(array));
        
        /*
        for (int i = 0; i < list.size(); i++)
        {
        	barsNow.add(list.get(i));
        }
        */
        
       //showToast("bar one: " + barsNow.get(0));
        
        /*
         * Instantiate ListView
         */
    	ListView listView = (ListView) findViewById(R.id.ListViewId);
    	
    	listView.setAdapter(new ListViewAdapter(this, R.layout.main_text_view,
    			barsNow));
    	
    	//Send correct bars based off FilterSearch screen
    	//Bar bb = new Bar();
    	//listView.setAdapter(new ListViewAdapter(this, R.layout.main_text_view,
    		//	bb));
    	
    	listView.setClickable(true);    	
    	listView.setOnItemClickListener(BarClick); 
    	
     	//Set header text
    	TextView t = (TextView) findViewById(R.id.header);
    	t.setText("Happy Hours right now: " + day);
    }
    
    /*
     * OnClickListener for Bars in ListView
     */
    private OnItemClickListener BarClick = new OnItemClickListener()
    {
    	public void onItemClick(AdapterView<?> parent, View view, int position,
    			long id)
    	{
    		
    		 Intent intent = new Intent();
    	     intent.setClass(MainActivity.this, ProfilePage.class);    	     
    	     // Save bar name for next activity, ProfilePage
    	     TextView t = (TextView) findViewById(R.id.testText);
    	     //t.getText();
    	     barName = pass.get(position);
    	     //showToast("passsize: " + pass.size());
    	     
    	     intent.putExtra("barName", barName);         	
    	     startActivity(intent);
		}
    };
    
    
    //Adapter 
    public class ListViewAdapter extends ArrayAdapter<String>
    {

    	private java.util.Vector<String> attr_string_vector;
    	//private java.util.Vector<Bar> bar;
    	private Context context;
    	
    	public ListViewAdapter(Context context, int textViewResourceId,
    			java.util.Vector<String> constr_string_vector)
    	//public ListViewAdapter(Context context, int textViewResourceId,
        	//		java.util.Vector<Bar> bar)
    	{
    		super(context, textViewResourceId, constr_string_vector);
    		//super(context, textViewResourceId, bar);
    		this.context = context;    		
    		this.attr_string_vector = constr_string_vector;
    		//this.bar = bar;
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
		   //pass = attr_string_vector;
		   ///*
		    // Save to global variable
		    for (int i = 0; i < attr_string_vector.size(); i++)
		    //for (int i = attr_string_vector.size()-1; i > 0 ; i--)
		    {
		    	pass.add(attr_string_vector.get(i));
		    }
		    //*/
		   
		    // For the bar's name
		    if (sup != null)		 
		    {
		    	TextView t = (TextView) v.findViewById(R.id.testText);
	
		    	if (t != null)
		    	{		    		
		    		t.setText(sup);		    
		    		
		    	}
		    }
		    
		    return v;
		}
    } //end adapter class

    private void showToast(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}