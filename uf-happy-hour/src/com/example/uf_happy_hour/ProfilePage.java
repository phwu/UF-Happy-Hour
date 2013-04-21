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

public class ProfilePage extends ListActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.profile_base);
    	
    	// Set individual cells
        ListView lv = getListView();
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.profile_cell_info, (ViewGroup) findViewById(R.id.profile_cell_root));
        lv.addHeaderView(v, null, false);
        
        //Get static map from URL
        /*
        Bitmap bb;
        try {
        	showToast("lehgo");
        	URL static_map_url;
		
			static_map_url = new URL("http://maps.googleapis.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&sensor=false");
		
			 bb = BitmapFactory.decodeStream(static_map_url.openConnection().getInputStream());
			 
		     //ImageView image = (ImageView) findViewById(R.id.static_map);
   	         //image.setImageBitmap(bb);
   	      showToast("lehgoing");
        }
        catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //profile_photo.setImageBitmap(bb); 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
        
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
        
 
        // Populate
        String listItems[] = {"List Item One", "List Item Two", "List Item Three", "List Item Four", "List Item Five"};
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
    	
    }
    
    private void showToast(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
