package com.example.uf_happy_hour;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Test_DB_Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_db_activity);
 
        DatabaseHelper dh = new DatabaseHelper(this);
 
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting BAR DATA ..");
        
        /** try {
      	   AssetManager am = getApplicationContext().getAssets();
      	   InputStream is = am.open("bars.txt");
      	   InputStreamReader irs = new InputStreamReader(is);
      	   BufferedReader br = new BufferedReader(irs); 
         
      	   try {
      		   String input = null;
  		
      		   input = br.readLine();
      		   while (input != null) {
      			   StringTokenizer st = new StringTokenizer(input, ";");
      			   String name, address, venue, hoursOpen, specials, fullBar;
      			   int phone, ageReq, food;
      	   
      			   while (st.hasMoreElements()) {
      				   name = st.nextToken();
      				   address = st.nextToken();
      				   phone = Integer.parseInt(st.nextToken());
      				   venue = st.nextToken();
      				   hoursOpen = st.nextToken();
      				   specials = st.nextToken();
      				   ageReq = Integer.parseInt(st.nextToken());
      				   food = Integer.parseInt(st.nextToken());
      				   fullBar = st.nextToken();
      	   
      				   dh.addBar(new Bar(name, address, phone, venue, hoursOpen, specials, ageReq, food, fullBar));
      			   }
      		   }
      	   }
      	   finally {
      		   br.close();
      		   irs.close();
      		   is.close();
      		   am.close();
      	   }
         }
         catch (IOException ex) {
      	   Log.d("ERROR", "Error in inserting data from input");
      	   ex.printStackTrace();
         }**/
       //   dh.addBar(new Bar("Stubbies", "123 Fake St", 3316983, "Bar", "2pm-2am", "M:$1 off", 21, 1, "Full bar"));
 
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Bar> bars = dh.getAllBars();       
 
        for (Bar b : bars) {
            String log = "Name: "+b.getName()+" , Phone: " + b.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
    }
    }
}
