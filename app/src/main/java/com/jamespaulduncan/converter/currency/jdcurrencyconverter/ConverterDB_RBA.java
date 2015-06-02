package com.jamespaulduncan.converter.currency.jdcurrencyconverter;

/**
 * Created by James on 2/06/2015.
 * This DB will store the exchange rates as provided by the Reserve Bank of Australia
 * http://www.rba.gov.au/rss/rss-cb-exchange-rates.xml
 *
 */

//TODO: Made DB more generic so as to cover as many currencies as possible
public class ConverterDB_RBA
{
    //"TAG" variable used for debugging in LogCat
    private static final String TAG = "JD CC: ConverterDB";

    private static final String DATABASE_NAME = "currencyConverter_DB.db";
    private static final int DATABASE_VERSION = 1;



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

    public static final Integer[] currency_icon = {
            R.drawable.aud_flag, R.drawable.usd_flag, R.drawable.cny_flag,
            R.drawable.jpy_flag, R.drawable.eur_flag, R.drawable.krw_flag,
            R.drawable.sgd_flag, R.drawable.nzd_flag, R.drawable.gbp_flag,
            R.drawable.myr_flag, R.drawable.thb_flag, R.drawable.idr_flag,
            R.drawable.inr_flag, R.drawable.twd_flag, R.drawable.vnd_flag,
            R.drawable.hkd_flag, R.drawable.pgk_flag, R.drawable.chf_flag,
            R.drawable.aed_flag, R.drawable.cad_flag, R.drawable.php_flag,
    };


}
