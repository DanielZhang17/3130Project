package com.project.csci3130.dalrs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class columnsettertwo extends ArrayAdapter<Use2> {

    private LayoutInflater sInflater;
    private ArrayList<Use2> users;
    private int mViewResourceId;

    public columnsettertwo(Context context, int textViewResourceId, ArrayList<Use2> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        sInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = sInflater.inflate(mViewResourceId, null);
        Use2 user = users.get(position);
        if(user!=null){
            TextView shijian=(TextView) convertView.findViewById(R.id.shi);
            TextView zhouyi=(TextView) convertView.findViewById(R.id.yi);
            TextView zhouer=(TextView) convertView.findViewById(R.id.er);
            TextView zhousan=(TextView) convertView.findViewById(R.id.san);
            TextView zhousi=(TextView) convertView.findViewById(R.id.si);
            TextView zhouwu=(TextView) convertView.findViewById(R.id.wu);
            if (shijian!=null){
                shijian.setText(user.gettime());
            }
            if(zhouyi!=null){
                zhouyi.setText(user.getmonday());
            }
            if(zhouer!=null){
                zhouer.setText(user.gettuesday());
            }
            if(zhousan!=null){
                zhousan.setText(user.getwednesday());
            }
            if(zhousi!=null){
                zhousi.setText(user.getthursday());
            }
            if(zhouwu!=null){
                zhouwu.setText(user.getfriday());
            }
        }
        return convertView;
    }
}
