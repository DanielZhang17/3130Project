package com.project.csci3130.dalrs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//change myDB to myDB2
public class ViewListContents extends AppCompatActivity {
    DatabaseHelper myDB;
    DatabaseHelper myDB2,myDB3;
    ArrayList<Use> userList;
    ListView listview;
    Use user;
    private static int i=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seetable);

        myDB=new DatabaseHelper(this);
        myDB2=new DatabaseHelper(this);
        /*if (i==0) {

            myDB.addData(null,"Monday","Tuesday","Wednesday","Thursday","Friday");
            myDB.addData("8:05",null,null,null,null,null);
            myDB.addData("8:35",null,null,null,null,null);
            myDB.addData("9:05",null,null,null,null,null);
            myDB.addData("9:35",null,null,null,null,null);
            myDB.addData("10:05",null,null,null,null,null);
            myDB.addData("10:35",null,null,null,null,null);
            myDB.addData("11:05",null,null,null,null,null);
            myDB.addData("11:35",null,null,null,null,null);
            myDB.addData("12:05",null,null,null,null,null);
            myDB.addData("12:35",null,null,null,null,null);
            myDB.addData("13:05",null,null,null,null,null);
            myDB.addData("13:35",null,null,null,null,null);
            myDB.addData("14:05",null,null,null,null,null);
            myDB.addData("14:35",null,null,null,null,null);
            myDB.addData("15:05",null,null,null,null,null);
            myDB.addData("15:35",null,null,null,null,null);
            myDB.addData("16:05",null,null,null,null,null);
            myDB.addData("16:35",null,null,null,null,null);
            myDB.addData("17:05",null,null,null,null,null);
            myDB.addData("17:35",null,null,null,null,null);
            myDB.addData("18:05",null,null,null,null,null);
            myDB.addData("18:35",null,null,null,null,null);
            myDB.addData("19:05",null,null,null,null,null);
            myDB.addData("19:35",null,null,null,null,null);
            myDB.addData("20:05",null,null,null,null,null);
            myDB.addData("20:35",null,null,null,null,null);
            myDB.addData("21:05",null,null,null,null,null);
            myDB.addData("21:35",null,null,null,null,null);

            i=1;
        }*/
        userList= new ArrayList<>();
        Cursor data=myDB.getListContents();
        int numRows=data.getCount();
        if (numRows==0)
            Toast.makeText(ViewListContents.this,"Nothing in database", Toast.LENGTH_LONG).show();
        else{
            int i=0;
            while(data.moveToNext()) {
                user = new Use(data.getString(1),data.getString(2), data.getString(3), data.getString(4),data.getString(5),data.getString(6));
                userList.add(user);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                System.out.println(userList.get(i).gettime());
                i++;
            }


            ThreeColumn_ListAdapter adapter=new ThreeColumn_ListAdapter(this,R.layout.timetable,userList);
            listview=(ListView) findViewById(R.id.listView);
            listview.setAdapter(adapter);
        }

    }

    public int geti(){
        return i;
    }

}
