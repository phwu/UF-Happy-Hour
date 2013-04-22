package com.example.uf_happy_hour;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {
	
	Button b1;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.menu_base);
    	
    	// Set onClickListeners
    	b1 = (Button) findViewById(R.id.hh_now);
    	b1.setOnClickListener(hh_now);
    }
    
    View.OnClickListener hh_now = new View.OnClickListener() {
        public void onClick(View v) {
        	
        	// Get the current day 
        	Calendar calendar = Calendar.getInstance();
        	int day = calendar.get(Calendar.DAY_OF_WEEK);        	
        	// Get current time
        	int hour = calendar.get(Calendar.HOUR_OF_DAY);
        	
        	//Convert int to String
        	String dayy = Integer.toString(day);
        	String hourr = Integer.toString(hour);
        	
        	// Go to MainActivity
           	Intent intent = new Intent();
        	intent.setClass(Menu.this, MainActivity.class);  
        	intent.putExtra("day", dayy);
        	intent.putExtra("hour", hourr);
   	     	startActivity(intent);
   	     
        }
    };

}
