package com.jamespaulduncan.converter.currency.jdcurrencyconverter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by James on 15/06/2015.
 *
 * This class populates the spinners
 */
public class CurrencyListAdapter extends BaseAdapter
    {
        // This variable is used for debug log (LogCat)
        private static final String TAG = "CC:CurrencyListAdapter";

        //TODO: Edit and understand this later
        private LayoutInflater mInflater;
        private Bitmap[] mIcon;
        private String[] mName;

        public CurrencyListAdapter(Context context, String[] name, Integer[] bitmapID) {
            mInflater = LayoutInflater.from(context);

            mIcon = new Bitmap[bitmapID.length];

            // load all bitmap and name
            for(int i=0; i<bitmapID.length; i++) {
                mIcon[i] = BitmapFactory.decodeResource(context.getResources(), bitmapID[i].intValue());
            }

            mName = name.clone();
        }

        public int getCount() {
            return mIcon.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder	holder;

            try {
                if(convertView == null) {
                    // uses currencylist.xml to display each currency selection
                    convertView = mInflater.inflate(R.layout.currencylist, null);
                    // then create a holder for this view for faster access
                    holder = new ViewHolder();

                    holder.icon = (ImageView) convertView.findViewById(R.id.list_icon);
                    holder.name = (TextView) convertView.findViewById(R.id.list_text);

                    // store this holder in the list
                    convertView.setTag(holder);
                } else {
                    // load the holder of this view
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.icon.setImageBitmap(mIcon[position]);
                holder.name.setText(mName[position]);
            } catch (Exception e) {
                Log.e(TAG, "getView:" + e.toString());
            }

            return convertView;
        }

        /* class ViewHolder */
        private class ViewHolder {
            ImageView	icon;
            TextView	name;
        }
}
