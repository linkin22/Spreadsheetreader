package com.ramogi.app.spreadsheetreader;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kstanoev on 1/14/2015.
 */
public class RealWinnerAdapter extends ArrayAdapter<RealWinner> {

    Context context;
    private ArrayList<RealWinner> teams;

    public RealWinnerAdapter(Context context, int textViewResourceId, ArrayList<RealWinner> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.teams = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.realwinner, null);
        }
        RealWinner o = teams.get(position);
        if (o != null) {
            TextView deliveryId =  v.findViewById(R.id.deliveryId);
            TextView drivername =  v.findViewById(R.id.drivername);
            TextView lat =  v.findViewById(R.id.lat);
            TextView lng =  v.findViewById(R.id.lng);
            TextView acc =  v.findViewById(R.id.acc);
            
            deliveryId.setText(o.getDeliveryId());
            drivername.setText(o.getDrivername());
            lat.setText(""+o.getLat());
            lng.setText(""+o.getLng());
            acc.setText(""+o.getAcc());
        }
        return v;
    }
}
