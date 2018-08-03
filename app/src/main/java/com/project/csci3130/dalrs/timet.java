package com.project.csci3130.dalrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class timet extends AppCompatActivity {
    Button fallterm;
    Button winterterm;
    Button summerterm;
    DatabaseHelper myDB;
    DatabaseHelper2 myDB2,myDB3;
    ViewListContentsTwo vlct;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_selection);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        fallterm=(Button) findViewById(R.id.fal);
        winterterm=(Button) findViewById(R.id.win);
        summerterm=(Button) findViewById(R.id.sum);
        myDB=new DatabaseHelper(this);
        myDB2=new DatabaseHelper2 (this);

        //changing("3","8:35",null,null,null,null,null);
        //changing("4","9:05",null,null,null,null,null);
        //changing("5","9:35",null,null,null,null,null);


        fallterm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(timet.this,termtable.class);
                startActivity(intent);
            }
        });

        winterterm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                vlct=new ViewListContentsTwo(myDB2);
                Intent intent2=new Intent(timet.this,termtabletwo.class);
                startActivity(intent2);
            }
        });

        summerterm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(timet.this,termtablethree.class);
                startActivity(intent);
            }
        });

    }


    public void AddData(String time, String Monday, String Tuesday,String Wednesday,String Thursday,String Friday) {
        boolean insertData = myDB.addData(time, Monday, Tuesday,Wednesday,Thursday,Friday);
        if (insertData == true) {
            Toast.makeText(timet.this, "Successful", Toast.LENGTH_LONG).show();
        }
    }


    public void init(String time){
        boolean initialization=myDB.addData(time,null,null,null,null,null);

    }

    public void changing(String a, String b, String c, String d, String e, String f, String g){
        myDB.updater(a,b,c,d,e,f,g);
    }

}

