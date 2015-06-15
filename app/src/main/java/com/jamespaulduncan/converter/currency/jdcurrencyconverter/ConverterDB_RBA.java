package com.jamespaulduncan.converter.currency.jdcurrencyconverter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by James on 2/06/2015.
 * This DB will store the exchange rates as provided by the Reserve Bank of Australia
 * http://www.rba.gov.au/rss/rss-cb-exchange-rates.xml
 */

//TODO: Made DB more generic so as to cover as many currencies as possible
public class ConverterDB_RBA
{
    //"TAG" variable used for debugging in LogCat
    private static final String TAG = "JD CC: ConverterDB";

    //DB Settings
    private static final String DATABASE_NAME = "currencyConverter_DB.db";
    private static final int DATABASE_VERSION = 1;

    private Context local_context = null;


    //Define default currency 3 letter names
    public static final String[] currency_name =
            {
                    "AUD", "USD", "CNY",
                    "JPY", "EUR", "KRW",
                    "SGD", "NZD", "GBP",
                    "MYR", "THB", "IDR",
                    "INR", "TWD", "VND",
                    "HKD", "PGK", "CHF",
                    "AED", "CAD", "PHP"
            };

    //Define long name of currencies
    public static final Integer[] currency_longname = {
            R.string.string_AUD, R.string.string_USD, R.string.string_CNY,
            R.string.string_JPY, R.string.string_EUR, R.string.string_KRW,
            R.string.string_SGD, R.string.string_NZD, R.string.string_GBP,
            R.string.string_MYR, R.string.string_THB, R.string.string_IDR,
            R.string.string_INR, R.string.string_TWD, R.string.string_VND,
            R.string.string_HKD, R.string.string_PGK, R.string.string_CHF,
            R.string.string_AED, R.string.string_CAD, R.string.string_PHP
    };

    //Icon ID's: Integer
    public static final Integer[] currency_icon = {
            R.drawable.aud_flag, R.drawable.usd_flag, R.drawable.cny_flag,
            R.drawable.jpy_flag, R.drawable.eur_flag, R.drawable.krw_flag,
            R.drawable.sgd_flag, R.drawable.nzd_flag, R.drawable.gbp_flag,
            R.drawable.myr_flag, R.drawable.thb_flag, R.drawable.idr_flag,
            R.drawable.inr_flag, R.drawable.twd_flag, R.drawable.vnd_flag,
            R.drawable.hkd_flag, R.drawable.pgk_flag, R.drawable.chf_flag,
            R.drawable.aed_flag, R.drawable.cad_flag, R.drawable.php_flag,
    };


//    DB Table: CurrencyConverterRates
//
//    Columns:
//
//    currency_name     TEXT
//    conversion_rate   FLOAT

    private static final String TABLE_NAME = "CurrencyConverterRates";
    private static final String COLUMN_NAME = "currency_name";
    private static final String COLUMN_RATE = "conversion_rate";

    //DB Helper Class
    private class Converter_DB_Helper extends SQLiteOpenHelper
    {
        public Converter_DB_Helper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            //create backup
            local_context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                String sql_string;

                //create db if none exists
                sql_string = "CREATE TABLE " + TABLE_NAME + " ( " +
                        COLUMN_NAME + " TEXT" + ", " +
                        COLUMN_RATE + " FLOAT" + " );";
                Log.d(TAG, "Create Tables: SQL String = " + sql_string);

                db.execSQL(sql_string);

                //setup tables
                //TODO: Replace with foreach
                for (int i = 0; i < currency_name.length; i++)
                {
                    sql_string = "INSERT INTO " + TABLE_NAME + " ( " +
                            COLUMN_NAME + " TEXT" + ", " +
                            COLUMN_RATE + " FLOAT" + " ) " +
                            "VALUES ('" + currency_name[i] + "', 1.0);";
                    Log.d(TAG, "Populate Tables: SQL String = " + sql_string);
                    //execute query
                    db.execSQL(sql_string);
                }
                //update from file in raw folder
                UpdateTableFromFile(db);
            } catch (Exception e)
            {
                Log.e(TAG, "onCreate:" + e.toString());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.i(TAG, "Update DB:" +
                    " old version = " + Integer.toString(oldVersion) +
                    " new version = " + Integer.toString(newVersion));

            //Delete old records and replace with new ones
            try
            {
                String sql_string;

                //delete all records
                sql_string = "DELETE FROM " + TABLE_NAME + ";";
                Log.d(TAG, "Delete all records: SQL String = " + sql_string);
                db.execSQL(sql_string);

                //setup tables
                //TODO: Replace with foreach
                for (int i = 0; i < currency_name.length; i++)
                {
                    sql_string = "INSERT INTO " + TABLE_NAME + " ( " +
                            COLUMN_NAME + " TEXT" + ", " +
                            COLUMN_RATE + " FLOAT" + " ) " +
                            "VALUES ('" + currency_name[i] + "', 1.0);";
                    Log.d(TAG, "Populate Tables: SQL String = " + sql_string);
                    //execute query
                    db.execSQL(sql_string);
                }

                //TODO: Delete this
                for (String item : currency_name)
                {
                    Log.d(TAG, "testing for each loop" + item);
                }
                //update from file in raw folder
                UpdateTableFromFile(db);
            } catch (Exception e)
            {
                Log.e(TAG, "onUpdate:" + e.toString());
            }
        }

        private void UpdateTableFromFile(SQLiteDatabase db)
        {
            //TODO: Create XML Parser
//            CurrencyRateParser_RBA parser = new CurrencyRateParser_RBA();
//            parser.start (send local context and R.raw.default_rates)
//            List<CurrencyRate> filedata = parser.getRates();
//
//            try {
//                String str_sql;
//
//                // use default date from raw folder to update table
//                for(int i=0; i<filedata.size(); i++) {
//                    str_sql = "UPDATE " + TABLE_CC_RATE + " SET " +
//                            COL_CC_RATE + "=" + Double.toString(filedata.get(i).m_rate) +
//                            " WHERE " + COL_CC_NAME + "='" + filedata.get(i).m_name + "';";
//                    Log.d(TAG, "setup tables: SQL="+str_sql);
//                    db.execSQL(str_sql);
//                }
//            } catch (Exception e) {
//                Log.e(TAG, "onCreate:" + e.toString());
//            }
        }
    }

    //db variables - what is core_db doing
    private Converter_DB_Helper helper;
    private SQLiteDatabase core_db = null;

    public ConverterDB_RBA(Context context)
    {
        helper = new Converter_DB_Helper(context);
        if (core_db == null)
        {
            Log.d(TAG, "Open database!");
            core_db = helper.getWritableDatabase();
        }
    }

    @Override
    public void finalize()
    {
        CloseDB();
    }

    private void CloseDB()
    {
        if (core_db != null)
        {
            if (core_db.isOpen())
            {
                Log.d(TAG, "Close database!");
                core_db.close();
                core_db = null;
            }
        }
    }

    //get position
    //TODO: Change this to a foreach
    public int GetCurrencyPosition(String name)
    {
        int i;

        for (i = 0; i < currency_name.length; i++)
        {
            if (name.compareToIgnoreCase(currency_name[i]) == 0)
            {
                return i;
            }
        }

        return 0;
    }

    //get rate by given currency name
    //TODO: Clean this up
    public double GetRates(String currencyName)
    {
        Cursor result;
        String str_sql = "SELECT " + COLUMN_RATE + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "= ?";
        double result_rate = 0;

        Log.d(TAG, "Get COLUMN_NAME = " + currencyName + " rate...");

        try
        {
            result = core_db.rawQuery(str_sql, new String[]{currencyName});

            if (result.moveToFirst())
            {
                result_rate = result.getDouble(0);
            }

            result.close();
            //result = null;
        } catch (Exception e)
        {
            Log.e(TAG, "GetRates:" + e.toString());
        }
        return result_rate;
    }

    // get all data from database
    //TODO: Clean this up
    public Cursor GetAllData()
    {
        String str_sql = "SELECT * FROM " + TABLE_NAME;

        try
        {
            return core_db.rawQuery(str_sql, null);
        } catch (Exception e)
        {
            Log.e(TAG, "GetAllData:" + e.toString());
        }
        return null;
    }

    // set rates by given currency name
    //TODO: clean this up too!
    public void SetRates(String currencyName, double currencyRates)
    {
        String str_sql;

        Log.d(TAG, "Update COLUMN_NAME = " + currencyName + " COLUMN_RATE = " + Double.toString(currencyRates));

        try
        {
            // update new rate
            str_sql = "UPDATE " + TABLE_NAME + " SET " +
                    COLUMN_RATE + "=" + Double.toString(currencyRates) +
                    " WHERE " + COLUMN_NAME + "='" + currencyName + "';";
            Log.d(TAG, "SetRates: SQL=" + str_sql);
            core_db.execSQL(str_sql);
        } catch (Exception e)
        {
            Log.e(TAG, "SetRates:" + e.toString());
        }
    }

    //end ConverterDB_RBA
}
