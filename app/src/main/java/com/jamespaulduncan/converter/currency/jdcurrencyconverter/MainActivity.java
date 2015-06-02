package com.jamespaulduncan.converter.currency.jdcurrencyconverter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity
{
    //"TAG" variable used for debugging in LogCat
    private static final String TAG = "JD CC: MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate ~");

        super.onCreate(savedInstanceState);

        try
        {
            setContentView(R.layout.activity_main);


        }
        catch (Exception e)
        {
            Log.e(TAG, "onCreate ~ " + e.toString());
        }
    }

    @Override
    protected void onStart()
    {
        Log.d(TAG, "onStart ~");
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        Log.d(TAG, "onResume ~");
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        Log.d(TAG, "onPause ~");
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        Log.d(TAG, "onStop ~");
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        Log.d(TAG, "onDestroy ~");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d(TAG, "onCreateOptionsMenu ~");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.d(TAG, "onOptionsItemSelected ~");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
