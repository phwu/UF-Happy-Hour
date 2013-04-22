package com.example.uf_happy_hour;


import java.io.IOException;
import java.util.List;

/*
import doodoo.maps.DatabaseHelper;
import doodoo.maps.Logger;
import doodoo.maps.R;
import doodoo.maps.SecondActivity;
import doodoo.maps.SplashScreen.LoadingTask;
*/
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class SplashScreen extends Activity {
	
    // properties for splash screen
    private final double _splashTime = 2000; //originally 300
    private final Activity activity = this;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {

		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.splash_screen);
		this.setTitle(" Loading...");
		
		new LoadingTask().execute();	
		
		
    }
	
	 private class LoadingTask extends AsyncTask<Void, Integer, Integer> 
     {
	     protected void onProgressUpdate(Integer... progress) 
	     {
		
	         activity.setProgress(progress[0]);
	     }
	     protected void onPostExecute(Integer result) 
	     {
		    finish();
		    startActivity(new Intent(activity, Menu.class));
	     }

	    @Override
	    protected Integer doInBackground(Void... params)
	    {
		//Logger.deleteLogFile();	
			try
			{
			    double waited = 0;
			    while (waited < _splashTime)
			    {				    
				    waited += 10;
				    double prog = (waited/_splashTime)*10000;
				    publishProgress((int)prog);				    
				    Thread.sleep(10);				
			    }
			}
			catch (InterruptedException e)
			{
			    return -1;
			}
			
		return 0;
	    }	   
	 }
}
