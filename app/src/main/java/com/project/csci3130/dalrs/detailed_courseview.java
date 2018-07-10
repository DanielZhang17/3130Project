package com.project.csci3130.dalrs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class detailed_courseview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_courseview);
        TextView crn,title,name,time,cred,loc,descrip;
        crn = findViewById(R.id.crn);
        title = findViewById(R.id.titile);
        name = findViewById(R.id.name);
        time = findViewById(R.id.time);
        cred = findViewById(R.id.cred);
        loc = findViewById(R.id.loc);
        descrip = findViewById(R.id.descrip);
        Course c = RegistActivity.selected;
        crn.setText(c.getCourseID());
        title.setText(c.getCourseTitle());
        name.setText(c.getCourseName());
        time.setText(c.getCourseTime());
        cred.setText(c.getCredit());
        loc.setText(c.getLocation());
        descrip.setText(c.getCourseInformation());
        String[] locSplit = c.getLocation().split(" ");
        String locF = "";//formats the location for google maps
        for (int i =0;i<locSplit.length -1;i++)
            locF += locSplit[i]+"+";
        locF = locF.substring(0,locF.length()-2);
        locF = locF.toLowerCase();
        final String url = "https://www.google.com/maps/search/?api=1&query="+locF;
        ImageButton b = findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
