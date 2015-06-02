package com.jamespaulduncan.converter.currency.jdcurrencyconverter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
{
    //"TAG" variable used for debugging in LogCat
    private static final String TAG = "JD CC: MainActivity";

    //These variables are used to avoid code duplication
    private static final char ITEM_CURRENCYA = 0;
    private static final char ITEM_CURRENCYB = 1;

//    private Spinner[]   spinner_Currency = { null, null };
//    private EditText[]  text_Currency = { null, null };
    private TextView				text_BaseCurrency;
    //private CurrencyListAdapter		adapter_currencylist;
    private ListView				listview_rate;
    //private CurrencyRateListAdapter	adapter_currencyratelist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate ~");

        super.onCreate(savedInstanceState);

        try
        {
            setContentView(R.layout.activity_main);

            //TODO: Load last used spinner selections


            // initialize control variables
//            spinner_Currency[ITEM_CURRENCYA] = (Spinner) findViewById(R.id.currencyASpinner);
//            spinner_Currency[ITEM_CURRENCYB] = (Spinner) findViewById(R.id.currencyBSpinner);
//            text_Currency[ITEM_CURRENCYA] = (EditText) findViewById(R.id.currencyAEditText);
//            text_Currency[ITEM_CURRENCYB] = (EditText) findViewById(R.id.currencyBEditText);
//            text_BaseCurrency = (TextView) findViewById(R.id.baseCurrencyTextView);
//            listview_rate = (ListView) findViewById(R.id.currentRatesListView);

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
