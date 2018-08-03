package com.project.csci3130.dalrs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class columnsetter extends ArrayAdapter<Use> {

    private LayoutInflater sInflater;
    private ArrayList<Use> users;
    private int mViewResourceId;

    public columnsetter(Context context, int textViewResourceId, ArrayList<Use> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        sInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = sInflater.inflate(mViewResourceId, null);
        Use user = users.get(position);
        if(user!=null){
            //below code changed
            TextView shijian=(TextView) convertView.findViewById(R.id.time);
            TextView zhouyi=(TextView) convertView.findViewById(R.id.Monday);
            TextView zhouer=(TextView) convertView.findViewById(R.id.Tuesday);
            TextView zhousan=(TextView) convertView.findViewById(R.id.Wednesday);
            TextView zhousi=(TextView) convertView.findViewById(R.id.Thursday);
            TextView zhouwu=(TextView) convertView.findViewById(R.id.Friday);
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
