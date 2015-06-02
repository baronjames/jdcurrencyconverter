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
                    "JPY", "EUR", "SGD",
                    "NZD", "GBP", "MYR",
                    "THB", "IDR", "INR",
                    "TWD", "VND", "HKD",
                    "PGK", "CHF", "AED",
                    "CAD", "PHP"
    };

    //Define long name of currencies
    public static final Integer[] currency_longname = {
            R.string.string_AUD, R.string.string_USD, R.string.string_CNY,
            R.string.string_JPY, R.string.string_EUR, R.string.string_SGD,
            R.string.string_NZD, R.string.string_GBP, R.string.string_MYR,
            R.string.string_THB, R.string.string_IDR, R.string.string_INR,
            R.string.string_TWD, R.string.string_VND, R.string.string_HKD,
            R.string.string_PGK, R.string.string_CHF, R.string.string_AED,
            R.string.string_CAD, R.string.string_PHP
    };

    //TODO: Define currency icons
//    public static final Integer[] currency_icon = {
//            R.drawable.flag_eur, R.drawable.flag_usd, R.drawable.flag_jpy, R.drawable.flag_bgn,
//            R.drawable.flag_czk, R.drawable.flag_dkk, R.drawable.flag_gbp,
//            R.drawable.flag_huf, R.drawable.flag_ltl, R.drawable.flag_lvl, R.drawable.flag_pln,
//            R.drawable.flag_ron, R.drawable.flag_sek, R.drawable.flag_chf, R.drawable.flag_nok,
//            R.drawable.flag_hrk, R.drawable.flag_rub, R.drawable.flag_try, R.drawable.flag_aud,
//            R.drawable.flag_brl, R.drawable.flag_cad, R.drawable.flag_cny, R.drawable.flag_hkd,
//            R.drawable.flag_idr, R.drawable.flag_ils, R.drawable.flag_inr, R.drawable.flag_kpw, R.drawable.flag_mxn,
//            R.drawable.flag_myr, R.drawable.flag_nzd, R.drawable.flag_php, R.drawable.flag_sgd,
//            R.drawable.flag_thb, R.drawable.flag_zar
//    };


}
