package com.project.csci3130.dalrs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListContentsTwo extends AppCompatActivity {
    DatabaseHelper myDB;
    DatabaseHelper2 myDB2,myDB3;
    ArrayList<Use2> userList;
    ListView listview;
    Use2 user;
    private static int i=0;

    public ViewListContentsTwo(){ }
    public ViewListContentsTwo(DatabaseHelper2 dbh){
        myDB2=dbh;


    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seetabletwo);

        myDB2=new DatabaseHelper2(this);
        /*if (i == 0) {

            myDB2.addData(null,"Monday","Tuesday","Wednesday","Thursday","Friday");
            myDB2.addData("8:05",null,null,null,null,null);
            myDB2.addData("8:35",null,null,null,null,null);
            myDB2.addData("9:05",null,null,null,null,null);
            myDB2.addData("9:35",null,null,null,null,null);
            myDB2.addData("10:05",null,null,null,null,null);
            myDB2.addData("10:35",null,null,null,null,null);
            myDB2.addData("11:05",null,null,null,null,null);
            myDB2.addData("11:35",null,null,null,null,null);
            myDB2.addData("12:05",null,null,null,null,null);
            myDB2.addData("12:35",null,null,null,null,null);
            myDB2.addData("13:05",null,null,null,null,null);
            myDB2.addData("13:35",null,null,null,null,null);
            myDB2.addData("14:05",null,null,null,null,null);
            myDB2.addData("14:35",null,null,null,null,null);
            myDB2.addData("15:05",null,null,null,null,null);
            myDB2.addData("15:35",null,null,null,null,null);
            myDB2.addData("16:05",null,null,null,null,null);
            myDB2.addData("16:35",null,null,null,null,null);
            myDB2.addData("17:05",null,null,null,null,null);
            myDB2.addData("17:35",null,null,null,null,null);
            myDB2.addData("18:05",null,null,null,null,null);
            myDB2.addData("18:35",null,null,null,null,null);
            myDB2.addData("19:05",null,null,null,null,null);
            myDB2.addData("19:35",null,null,null,null,null);
            myDB2.addData("20:05",null,null,null,null,null);
            myDB2.addData("20:35",null,null,null,null,null);
            myDB2.addData("21:05",null,null,null,null,null);
            myDB2.addData("21:35",null,null,null,null,null);

            i=1;
        }*/
        //myDB2.addData("9:05","Mon","Tue","Wed","thur","Fri");
        //myDB2.updater("1","9:05","Monday","Tuesday","Wednesday","Thursday","Friday");
        userList= new ArrayList<>();
        Cursor data=myDB2.getListContents();
        int numRows=data.getCount();
        if (numRows==0)
            Toast.makeText(ViewListContentsTwo.this,"Nothing in database", Toast.LENGTH_LONG).show();
        else{
            int i=0;
            while(data.moveToNext()) {
                user = new Use2(data.getString(1),data.getString(2), data.getString(3), data.getString(4),data.getString(5),data.getString(6));
                userList.add(user);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                System.out.println(userList.get(i).gettime());
                i++;
            }


            ThreeColumn_ListAdapter2 adapter=new ThreeColumn_ListAdapter2(this,R.layout.timetable2,userList);
            listview=(ListView) findViewById(R.id.listView2);
            listview.setAdapter(adapter);
        }
    }

}

