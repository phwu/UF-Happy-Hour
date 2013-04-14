package com.example.uf_happy_hour;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


import android.app.ListActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
 
        // Populate
        String listItems[] = {"List Item One", "List Item Two", "List Item Three", "List Item Four", "List Item Five"};
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
    	
    }
    
    private void showToast(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
