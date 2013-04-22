package com.example.uf_happy_hour;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpConnection;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProfilePage extends ListActivity {
	
	String barName;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.profile_base);
    	
    	Bundle bundle = this.getIntent().getExtras();
    	barName = bundle.getString("barName");
    	showToast("bar: " + barName);
    	
    	// Set individual cells
        ListView lv = getListView();
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.profile_cell_info, (ViewGroup) findViewById(R.id.profile_cell_root));
        lv.addHeaderView(v, null, false);       
        
        // Programatically fill imageview
        
        //Map <String, Integer> map = new HashMap<String, Integer> ();
        //map.put("hello", R.drawable.hello);
        //ImageView image = (ImageView) findViewById(R.id.static_map);
        //image.setImageResource(map.get("hello"));
        //image.setImageResource();
        //image.setImageResource(R.drawable.hello);
        //image.setImageBitmap(bb);
        
        //new StaticMapImage((ImageView) findViewById(R.id.static_map)).execute("http://maps.googleapis.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&sensor=false");
        
        
        new StaticMapImage((ImageView) findViewById(R.id.static_map))
        	.execute("http://maps.googleapis.com/maps/api/staticmap?" +
        			"center=9+West+University+Ave+Gainesville,FL&zoom=14&size=400x400&sensor=false");
        
 
        // Populate test data
        String listItems[] = {"List Item One", "List Item Two", "List Item Three", "List Item Four", "List Item Five"};
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
        
     	//Set header text
    	TextView t = (TextView) findViewById(R.id.header);
    	t.setText(barName + " Date goes here");
    	
    }
    
    private void showToast(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
