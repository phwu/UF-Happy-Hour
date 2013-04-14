package com.example.uf_happy_hour;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ProfilePage extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	showToast("sup ma profP");
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.profile_base);
    	
    }
    
    private void showToast(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
