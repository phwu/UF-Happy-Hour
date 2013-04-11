package com.example.uf_happy_hour;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import android.util.Log;

public class LoadScreenActivity extends Activity
{
    // properties for splash screen
    private final double _splashTime = 300;
    private final Activity activity = this;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_load_screen);
        this.setTitle(" Loading...");
        
        new LoadingTask().execute();
        DatabaseHelper dh = new DatabaseHelper(this);
         // Logger.ToastMessage(this, fact,Toast.LENGTH_SHORT);
         // Logger.ToastMessage(this, fact,Toast.LENGTH_SHORT);
        
        // NEED TO PARSE TOKENS FROM ASSETS/BARS.TXT FILE USING INPUT STREAM (see DatabaseHelperV1)
        // use Log class to debug-- will print statements into LogCat!
        Log.d("Insert: ", "Inserting ..");
        dh.addContact(new Contact("Ravi", "9100000000"));
        dh.addContact(new Contact("Srinivas", "9199999999"));
        dh.addContact(new Contact("Tommy", "9522222222"));
        dh.addContact(new Contact("Karthik", "9533333333"));
        

    }

    
    
     private class LoadingTask extends AsyncTask<Void, Integer, Integer> 
     {
             

             protected void onProgressUpdate(Integer... progress) 
             {
                
                 activity.setProgress(progress[0]);
                 
             }

             protected void onPostExecute(Integer result) 
             {
                    //finish();
                    //startActivity(new Intent(activity,SecondActivity.class));
             }

            @Override
            protected Integer doInBackground(Void... params)
            {
       
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