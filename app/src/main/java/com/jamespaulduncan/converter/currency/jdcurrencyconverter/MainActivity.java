package com.jamespaulduncan.converter.currency.jdcurrencyconverter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.util.Locale;


public class MainActivity extends ActionBarActivity
{
    //"TAG" variable used for debugging in LogCat
    private static final String TAG = "JD CC: MainActivity";

    private ConverterDB_RBA DB;

    //These variables are used to avoid code duplication
    private static final char ITEM_CURRENCYA = 0;
    private static final char ITEM_CURRENCYB = 1;

    private Spinner[]               spinner_Currency = { null, null };
    private EditText[]              text_Currency = { null, null };
    private TextView text_BaseCurrency;
    private CurrencyListAdapter		currencylist_adapter;
    private ListView				listview_rate;
    private CurrencyRateListAdapter adapter_currencyratelist; //for the actual list

    //Initial Control variables
    //TODO: Refactoring variable names
    private String					Base_C = "";
    private String[]				Selected_C = { " ", " " };
    private int						current_active_currency = ITEM_CURRENCYA;
    private String					ListViewSelected_C = "";
    private String					SavedInstanceText = "";
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
            spinner_Currency[ITEM_CURRENCYA] = (Spinner) findViewById(R.id.currencyASpinner);
            spinner_Currency[ITEM_CURRENCYB] = (Spinner) findViewById(R.id.currencyBSpinner);
            text_Currency[ITEM_CURRENCYA] = (EditText) findViewById(R.id.currencyAEditText);
            text_Currency[ITEM_CURRENCYB] = (EditText) findViewById(R.id.currencyBEditText);
            text_BaseCurrency = (TextView) findViewById(R.id.baseCurrencyTextView);
            listview_rate = (ListView) findViewById(R.id.currentRatesListView);


            DB = new ConverterDB_RBA(this);

            //TODO: "savedInstanceState" thing


            currencylist_adapter = new CurrencyListAdapter(this,ConverterDB_RBA.currency_name, ConverterDB_RBA.currency_icon);

            for(int i=0; i<2; i++)
            {
                //TODO: Comment and edit this later
                spinner_Currency[i].setAdapter(currencylist_adapter);

                spinner_Currency[i].setOnItemSelectedListener(selectedListener_Currency);

                // set listener for edit text controls
                text_Currency[i].setOnKeyListener(keyListener_Currency);
                text_Currency[i].setOnFocusChangeListener(focusListener_Currency);
                text_Currency[i].setOnEditorActionListener(EditorActionListener);

                // set input type for edit text controls
                text_Currency[i].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                // when users press enter key, close soft keyboard automatically
                text_Currency[i].setImeOptions(EditorInfo.IME_ACTION_DONE);
            }

            // set EditText control will be focused on itself when "enter" key is pressed
            text_Currency[ITEM_CURRENCYA].setNextFocusDownId(R.id.currencyAEditText);
            text_Currency[ITEM_CURRENCYB].setNextFocusDownId(R.id.currencyBEditText);

            // create currency rate list adapter
            adapter_currencyratelist = new CurrencyRateListAdapter(this, ConverterDB_RBA.currency_longname, ConverterDB_RBA.currency_icon, DB.GetAllData());

            listview_rate.setAdapter(adapter_currencyratelist);

            // set listener for list view control
            listview_rate.setOnItemClickListener(clickListener_listview);
            listview_rate.setOnItemLongClickListener(longclickListener_listview);

            // display base currency
            //m_Base_C = mPrefs.getString(KEY_BASECURRENCY, ConverterDB_RBA.currency_name[0]);
            adapter_currencyratelist.SetBaseCurrencyIndex(DB.GetCurrencyPosition(Base_C));
            //m_text_BaseCurrency.setText(m_Base_C);

            //Selected_C[ITEM_CURRENCYA] = mPrefs.getString(KEY_SEL_CURRENCYA, CurrencyConverterDB.currency_name[0]);
            //Selected_C[ITEM_CURRENCYB] = mPrefs.getString(KEY_SEL_CURRENCYB, CurrencyConverterDB.currency_name[0]);

            // register broadcast receiver
            //IntentFilter filter = new IntentFilter(SERVICE_TO_ACTIVITY_BROADCAST);
            //my_intent_receiver = new Broadcast_Receiver();
            //registerReceiver(my_intent_receiver, filter);




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

    //LISTENERS

    // Spinner control listeners
    private OnItemSelectedListener selectedListener_Currency = new OnItemSelectedListener() {
        int	nSelected = 0;

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId() == R.id.currencyASpinner) {
                nSelected = ITEM_CURRENCYA;
            } else {
                nSelected = ITEM_CURRENCYB;
            }

            Log.d(TAG, "Current selected item for currency [" + Integer.toString(nSelected) + "] is " + ConverterDB_RBA.currency_name[position]);
            // update selected currency
            Selected_C[nSelected] = ConverterDB_RBA.currency_name[position];
            // update another one
            if(current_active_currency == ITEM_CURRENCYA) {
                updateCurrencyDisplay(ITEM_CURRENCYB);
            } else {
                updateCurrencyDisplay(ITEM_CURRENCYA);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    };


    // List view control listeners
    private OnItemClickListener clickListener_listview = new OnItemClickListener() {
        //TODO: Make this work
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                //String sel = mPrefs.getString(KEY_LISTVIEW_OPT, "1");

//                if(sel.equalsIgnoreCase("0")) {
//                    ListViewSelected_C = ConverterDB_RBA.currency_name[position];
//
//                    Log.d(TAG, "Current selected item for listview is " + ListViewSelected_C);
//                    //showDialog(DIALOG_CURRENCYEDIT);
//                }
            } catch (Exception e) {
                Log.e(TAG, "onItemClick:" + e.toString());
            }
        }
    };

    private OnItemLongClickListener longclickListener_listview = new OnItemLongClickListener() {

        //TODO: make these work
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                //String sel = mPrefs.getString(KEY_LISTVIEW_OPT, "1");

//                if(sel.equalsIgnoreCase("1")) {
//                    m_ListViewSelected_C = CurrencyConverterDB.currency_name[position];
//
//                    Log.d(TAG, "Current selected item for listview is " + m_ListViewSelected_C);
//                    showDialog(DIALOG_CURRENCYEDIT);
//                }
            } catch (Exception e) {
                Log.e(TAG, "onItemLongClick:" + e.toString());
            }
            return true;
        }

    };


    // update currency display
    //TODO: Change this
    private void updateCurrencyDisplay(char toupdateitem) {
        double	rate_CA = 0, rate_CB = 0;
        double  in_CA = 0, in_CB = 0;
        double	result = 0;
        String  str_CA, str_CB, str_result;

        // get currency rates
        rate_CA = DB.GetRates(Selected_C[ITEM_CURRENCYA]);
        rate_CB = DB.GetRates(Selected_C[ITEM_CURRENCYB]);

        // get input values
        str_CA = text_Currency[ITEM_CURRENCYA].getText().toString();
        str_CB = text_Currency[ITEM_CURRENCYB].getText().toString();

        try {
            in_CA = Double.valueOf(str_CA);
        } catch(Exception e) {
            Log.e(TAG, "updateCurrencyDisplay:"+e.toString());
            in_CA = 0;
        }

        try {
            in_CB = Double.valueOf(str_CB);
        } catch(Exception e) {
            Log.e(TAG, "updateCurrencyDisplay:"+e.toString());
            in_CB = 0;
        }

        Log.d(TAG, "CA: in = " + in_CA + " rate = " + rate_CA);
        Log.d(TAG, "CB: in = " + in_CB + " rate = " + rate_CB);

        if(toupdateitem == ITEM_CURRENCYA) {
            if(rate_CB == 0) {
                result = 0;
            } else {
                result = rate_CA / rate_CB * in_CB;
            }

            str_result = formCurrencyDisplay(result);
            text_Currency[ITEM_CURRENCYA].setText(str_result);
        } else {
            if(rate_CA == 0) {
                result = 0;
            } else {
                result = rate_CB / rate_CA * in_CA;
            }

            str_result = formCurrencyDisplay(result);
            text_Currency[ITEM_CURRENCYB].setText(str_result);
        }
    }
    // Convert double to string for currency
    private String formCurrencyDisplay(double value) {
        String format = "";
        format = "%.2f"; //Temp setting

        //TODO: Create prefs
//        try {
//            switch(Integer.parseInt(mPrefs.getString(KEY_PRECISION, "2"))) {
//                case 0:
//                    format = "%.0f";
//                    break;
//
//                case 1:
//                    format = "%.1f";
//                    break;
//
//                case 3:
//                    format = "%.3f";
//                    break;
//
//                default:
//                    format = "%.2f";
//                    break;
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "formCurrencyDisplay:" + e.toString());
//            format = "%.2f";
//        }

        String result = String.format(Locale.US, format, value);

        return result;
    }

    // EditText controls listeners
    private OnEditorActionListener EditorActionListener = new OnEditorActionListener() {
        int nSelected = 0;

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(v.getId() == R.id.currencyAEditText) {
                nSelected = ITEM_CURRENCYA;
            } else {
                nSelected = ITEM_CURRENCYB;
            }

            Log.d(TAG, "Currency [" + Integer.toString(nSelected) + "] EditorAction receives: " + Integer.toString(actionId) + ".");

            if(nSelected == ITEM_CURRENCYA) {
                // change current selected currency and update another one
                updateCurrencyDisplay(ITEM_CURRENCYB);
            } else {
                // change current selected currency and update another one
                updateCurrencyDisplay(ITEM_CURRENCYA);
            }

            return false;
        }
    };

    private OnKeyListener keyListener_Currency = new OnKeyListener() {
        int nSelected = 0;

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            boolean ret_flag = false;

            if(v.getId() == R.id.currencyAEditText) {
                nSelected = ITEM_CURRENCYA;
            } else {
                nSelected = ITEM_CURRENCYB;
            }

            if(event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(TAG, "Currency [" + Integer.toString(nSelected) + "] EditText receives key: " + Integer.toString(keyCode) + ".");

                switch(keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        if(nSelected == ITEM_CURRENCYA) {
                            // change current selected currency and update another one
                            updateCurrencyDisplay(ITEM_CURRENCYB);
                        } else {
                            // change current selected currency and update another one
                            updateCurrencyDisplay(ITEM_CURRENCYA);
                        }
                        ret_flag = true;
                        break;

                    default:
                        break;
                }
            }

            return ret_flag;
        }
    };

    private OnFocusChangeListener focusListener_Currency = new OnFocusChangeListener() {
        int	nSelected = 0, len;
        String	current_input_value = "";

        public void onFocusChange(View v, boolean hasFocus) {
            if(v.getId() == R.id.currencyAEditText) {
                nSelected = ITEM_CURRENCYA;
            } else {
                nSelected = ITEM_CURRENCYB;
            }

            current_input_value = text_Currency[nSelected].getText().toString();

            if(hasFocus) {
                Log.d(TAG, "Currency [" + Integer.toString(nSelected) + "] EditText on focus. text=" + current_input_value);
                len = current_input_value.length();
                if(len > 0) {
                    if(current_input_value.compareTo(SavedInstanceText) == 0) {
                        SavedInstanceText = "";
                        // move the cursor to the end of the text
                        text_Currency[nSelected].setSelection(len);
                    } else {
                        current_input_value = "";
                        text_Currency[nSelected].setText(current_input_value);
                    }
                }
                current_active_currency = nSelected;
            } else {
                Log.d(TAG, "Currency [" + Integer.toString(nSelected) + "] EditText loss focus. text=" + current_input_value);
                if(current_input_value.length() == 0) {
                    current_input_value = formCurrencyDisplay(0);
                    text_Currency[nSelected].setText(current_input_value);
                }
            }
        }
    };
}
