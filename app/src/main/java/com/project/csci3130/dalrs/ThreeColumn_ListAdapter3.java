package com.project.csci3130.dalrs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter3 extends ArrayAdapter<Use3> {

    private LayoutInflater sInflater;
    private ArrayList<Use3> users;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter3(Context context, int textViewResourceId, ArrayList<Use3> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        sInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = sInflater.inflate(mViewResourceId, null);
        Use3 user = users.get(position);
        if(user!=null){
            TextView shijian=(TextView) convertView.findViewById(R.id.timer);
            TextView zhouyi=(TextView) convertView.findViewById(R.id.un);
            TextView zhouer=(TextView) convertView.findViewById(R.id.deux);
            TextView zhousan=(TextView) convertView.findViewById(R.id.trois);
            TextView zhousi=(TextView) convertView.findViewById(R.id.quatre);
            TextView zhouwu=(TextView) convertView.findViewById(R.id.sinq);
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

