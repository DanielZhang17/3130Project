package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;


public class Adder extends AppCompatActivity {

    private Context context;
    private String courseId="";
    EditText time, Monday, Tuesday, Wednesday,Thursday,Friday;
    Button btnAdd, btnView;
    DatabaseHelper myDB;
    int marker=0;
    //RegistActivity reg=new RegistActivity();

    public Adder(){


    }

    public Adder(String str){
        courseId=str;

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adder);


        time = (EditText) findViewById(R.id.Time);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        //btnAdd=(Button) findViewById(R.id.Timetable);
        btnView = (Button) findViewById(R.id.btnView);
        //addcourse=(Button) findViewById(R.id.button1);
        myDB = new DatabaseHelper(this);

        AddData(null,"Monday","Tuesday","Wednesday","Thursday","Friday");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tim = "";

                courseId = time.getText().toString();
                String id = "";
            if (courseId.equals("10001")) {
                changing("3", "8:35", "CSCI3130", null, "CSCI3130", null, null);
                changing("4", "9:05", "CSCI3130", null, "CSCI3130", null, null);
                changing("5", "9:35", "CSCI3130", null, "CSCI3130", null, null);
            } else if (courseId.equals("10003")) {
                changing("15", "14:35", null, "CSCI2141", null, "CSCI2141", null);
                changing("16", "15:05", null, "CSCI2141", null, "CSCI2141", null);
                changing("17", "15:35", null, "CSCI2141", null, "CSCI2141", null);
            } else if (courseId.equals("10005")) {
                changing("3", "8:35", null, "CSCI1108", null, "CSCI1108", null);
                changing("4", "9:05", null, "CSCI1108", null, "CSCI1108", null);
                changing("5", "9:35", null, "CSCI1108", null, "CSCI1108", null);
            } else if (courseId.equals("10007")) {
                changing("6", "10:05", "CSCI 1105", null, "CSCI 1105", null, null);
                changing("7", "10:35", "CSCI 1105", null, "CSCI 1105", null, null);
                changing("8", "11:35", "CSCI 1105", null, "CSCI 1105", null, null);
            } else if (courseId.equals("10877")) {
                changing("15", "14:35", "ECON 1101", null, "ECON 1101", null, null);
                changing("16", "15:05", "ECON 1101", null, "ECON 1101", null, null);
            } else if (courseId.equals("10915")) {
                changing("18", "16:05", "ECON 2200", null, "ECON 2200", null, null);
                changing("19", "16:35", "ECON 2200", null, "ECON 2200", null, null);
                changing("20", "17:05", "ECON 2200", null, "ECON 2200", null, null);
            } else if (courseId.equals("10926")) {
                changing("21", "17:35", null, null, "ECON 2233", null, null);
                changing("22", "18:05", null, null, "ECON 2233", null, null);
                changing("23", "18:35", null, null, "ECON 2233", null, null);
                changing("24", "19:05", null, null, "ECON 2233", null, null);
                changing("25", "19:35", null, null, "ECON 2233", null, null);
                changing("26", "20:05", null, null, "ECON 2233", null, null);
            } else if (courseId.equals("10940")) {
                changing("3", "8:35", null, "ECON 3338", null, "ECON 3338", null);
                changing("4", "9:05", null, "ECON 3338", null, "ECON 3338", null);
                changing("5", "9:35", null, "ECON 3338", null, "ECON 3338", null);
            } else if (courseId.equals("10948")) {
                changing("8", "11:35", "ECON 4421", null, "ECON 4421", null, null);
                changing("9", "12:05", "ECON 4421", null, "ECON 4421", null, null);
                changing("10", "12:35", "ECON 4421", null, "ECON 4421", null, null);
            } else if (courseId.equals("12563")) {
                changing("13", "13:35", "STAT 1060", null, "STAT 1060", null, "STAT 1060");
                changing("14", "14:05", "STAT 1060", null, "STAT 1060", null, "STAT 1060");
            } else if (courseId.equals("12575")) {
                changing("7", "10:35", "STAT 2060", null, "STAT 2060", null, "STAT 2060");
                changing("8", "11:05", "STAT 2060", null, "STAT 2060", null, "STAT 2060");
            } else if (courseId.equals("12584")) {
                changing("6", "10:05", "STAT 2600", null, "STAT 2600", null, null);
                changing("7", "10:35", "STAT 2600", null, "STAT 2600", null, null);
                changing("8", "11:05", "STAT 2600", null, "STAT 2600", null, null);

            } else if (courseId.equals("12586")) {
                changing("6", "10:05", null, "STAT 3360", "null", "STAT 3360", null);
                changing("7", "10:35", null, "STAT 3360", "null", "STAT 3360", null);
                changing("8", "11:05", null, "STAT 3360", "null", "STAT 3360", null);

            } else if (courseId.equals("12587")) {
                changing("15", "14:35", "STAT 4066", null, "STAT 4066", null, "STAT 4066");
                changing("16", "15:05", "STAT 4066", null, "STAT 4066", null, "STAT 4066");
            } else if (courseId.equals("20638")) {
                changing("3", "8:35", "CSCI1170", "CSCI1170", null, null, null);
                changing("4", "9:05", "CSCI1170", "CSCI1170", null, null, null);
                changing("5", "9:35", "CSCI1170", "CSCI1170", null, null, null);
            } else if (courseId.equals("20650")) {
                changing("15", "14:35", "CSCI2100", null, "CSCI2100", null, "CSCI2100");
                changing("16", "15:05", "CSCI2100", null, "CSCI2100", null, "CSCI2100");
            } else if (courseId.equals("20651")) {
                changing("3", "8:35", null, null, "CSCI2110", null, "CSCI2110");
                changing("4", "9:05", null, null, "CSCI2110", null, "CSCI2110");
                changing("5", "9:35", null, null, "CSCI2110", null, "CSCI2110");
            } else if (courseId.equals("20689")) {
                changing("3", "8:35", "CSCI3130", null, "CSCI3130", null, null);
                changing("4", "9:05", "CSCI3130", null, "CSCI3130", null, null);
                changing("5", "9:35", "CSCI3130", null, "CSCI3130", null, null);
            } else if (courseId.equals("20720")) {
                changing("13", "13:35", null, "CSCI4174", null, "CSCI4174", "CSCI4174");
                changing("14", "14:05", null, "CSCI4174", null, "CSCI4174", "CSCI4174");

            } else if (courseId.equals("20807")) {
                changing("18", "16:05", "ECON 1101", null, "ECON 1101", null, null);
                changing("19", "16:35", "ECON 1101", null, "ECON 1101", null, null);
                changing("20", "17:05", "ECON 1101", null, "ECON 1101", null, null);

            } else if (courseId.equals("20843")) {
                changing("18", "16:05", null, "ECON 2200", null, "ECON 2200", null);
                changing("19", "16:35", null, "ECON 2200", null, "ECON 2200", null);
                changing("20", "17:05", null, "ECON 2200", null, "ECON 2200", null);
            } else if (courseId.equals("22361")) {
                changing("12", "13:05", null, "STAT 1060", null, "STAT 1060", null);
                changing("13", "13:35", null, "STAT 1060", null, "STAT 1060", null);
                changing("14", "14:05", null, "STAT 1060", null, "STAT 1060", null);

            } else if (courseId.equals("22384")) {
                changing("6", "10:35", "STAT 2060", null, "STAT 2060", null, "STAT 2060");
                changing("7", "11:05", "STAT 2060", null, "STAT 2060", null, "STAT 2060");
            } else if (courseId.equals("22390")) {
                changing("15", "14:35", "STAT 2080", null, "STAT 2080", null, "STAT 2080");
                changing("16", "15:05", "STAT 2080", null, "STAT 2080", null, "STAT 2080");
            } else if (courseId.equals("22397")) {
                changing("15", "14:35", "STAT3380", null, "STAT3380", null, null);
                changing("16", "15:05", "STAT3380", null, "STAT3380", null, null);
                changing("17", "15:35", "STAT3380", null, "STAT3380", null, null);
            } else if (courseId.equals("22401")) {
                changing("18", "16:05", "STAT 4390", null, "STAT 4390", null, null);
                changing("19", "16:35", "STAT 4390", null, "STAT 4390", null, null);
                changing("20", "17:05", "STAT 4390", null, "STAT 4390", null, null);
            } else if (courseId.equals("31268")) {
                changing("12", "13:05", "CSCI1105", null, "CSCI1105", null, null);
                changing("13", "13:35", "CSCI1105", null, "CSCI1105", null, null);
                changing("14", "14:05", "CSCI1105", null, "CSCI1105", null, null);
            } else if (courseId.equals("31270")) {
                changing("12", "13:05", "CSCI1110", null, "CSCI1110", null, null);
                changing("13", "13:35", "CSCI1110", null, "CSCI1110", null, null);
                changing("14", "14:05", "CSCI1110", null, "CSCI1110", null, null);
            } else if (courseId.equals("31272")) {
                changing("8", "11:35", null, "CSCI2691", null, "CSCI2691", null);
                changing("9", "12:05", null, "CSCI2691", null, "CSCI2691", null);
                changing("10", "12:35", null, "CSCI2691", null, "CSCI2691", null);

            } else if (courseId.equals("31273")) {
                changing("15", "14:35", null, "CSCI3110", null, "CSCI3110", null);
                changing("16", "15:05", null, "CSCI3110", null, "CSCI3110", null);
                changing("17", "15:35", null, "CSCI3110", null, "CSCI3110", null);
            } else if (courseId.equals("31460")) {
                changing("22", "18:05", "STAT 2060", null, "STAT 2060", null, null);
                changing("23", "18:35", "STAT 2060", null, "STAT 2060", null, null);
                changing("24", "19:05", "STAT 2060", null, "STAT 2060", null, null);
                changing("25", "19:35", "STAT 2060", null, "STAT 2060", null, null);
                changing("26", "20:05", "STAT 2060", null, "STAT 2060", null, null);
                changing("27", "20:35", "STAT 2060", null, "STAT 2060", null, null);
                changing("28", "21:05", "STAT 2060", null, "STAT 2060", null, null);
            } else if (courseId.equals("31803")) {
                changing("22", "18:05", "STAT 1060", null, "STAT 1060", null, null);
                changing("23", "18:35", "STAT 1060", null, "STAT 1060", null, null);
                changing("24", "19:05", "STAT 1060", null, "STAT 1060", null, null);
                changing("25", "19:35", "STAT 1060", null, "STAT 1060", null, null);
                changing("26", "20:05", "STAT 1060", null, "STAT 1060", null, null);
                changing("27", "20:35", "STAT 1060", null, "STAT 1060", null, null);
            } else if (courseId.equals("31836")) {
                changing("22", "18:05", "STAT 2080", null, "STAT 2080", null, null);
                changing("23", "18:35", "STAT 2080", null, "STAT 2080", null, null);
                changing("24", "19:05", "STAT 2080", null, "STAT 2080", null, null);
                changing("25", "19:35", "STAT 2080", null, "STAT 2080", null, null);
                changing("26", "20:05", "STAT 2080", null, "STAT 2080", null, null);
                changing("27", "20:35", "STAT 2080", null, "STAT 2080", null, null);
            }

                //else if(courseId.equals("10003"))
                //String mon = Monday.getText().toString();
                //String tue = Tuesday.getText().toString();
                //String wed= Wednesday.getText().toString();
                //String thu=Thursday.getText().toString();
                //String fri=Friday.getText().toString();
               // changing("3","8:35","3130","null","3130","null","null");
                /*if (tim.equals("8:05"))
                    id="2";
                else if (tim.equals("8:35"))
                    id="3";
                else if (tim.equals("9:05"))
                    id="4";
                else if (tim.equals("9:35"))
                    id="5";
                else if (tim.equals("10:05"))
                    id="6";
                else if (tim.equals("10:35"))
                    id="7";
                else if (tim.equals("11:05"))
                    id="8";
                else if (tim.equals("11:35"))
                    id="9";
                else if (tim.equals("12:05"))
                    id="10";
                else if (tim.equals("12:35"))
                    id="11";
                else if (tim.equals("13:05"))
                    id="12";
                else if (tim.equals("13:35"))
                    id="13";
                else if (tim.equals("14:05"))
                    id="14";
                else if (tim.equals("14:35"))
                    id="15";
                else if (tim.equals("15:05"))
                    id="16";
                else if (tim.equals("15:35"))
                    id="17";
                else if (tim.equals("16:05"))
                    id="18";
                else if (tim.equals("16:35"))
                    id="19";
                else if (tim.equals("17:05"))
                    id="20";
                else if (tim.equals("17:35"))
                    id="21";
                else if (tim.equals("18:05"))
                    id="22";
                else if (tim.equals("18:35"))
                    id="23";
                else if (tim.equals("19:05"))
                    id="24";
                else if (tim.equals("19:35"))
                    id="25";
                else if (tim.equals("20:05"))
                    id="26";
                else if (tim.equals("20:35"))
                    id="27";
                else if (tim.equals("21:05"))
                    id="28";
                else if (tim.equals("21:35"))
                    id="29";
                changing(id,tim,"3130","null","3130","null","null");

                if (tim.length() != 0 && mon.length() != 0 && tue.length() != 0&&wed.length()!=0&&thu.length()!=0
                       &&fri.length()!=0)
                 changing(id,tim,mon,tue,wed,thu,fri);*/

            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Adder.this, termtable.class);
                startActivity(intent);

            }
        });
    }

            public void AddData(String time, String Monday, String Tuesday,String Wednesday,String Thursday,String Friday) {
                boolean insertData = myDB.addData(time, Monday, Tuesday,Wednesday,Thursday,Friday);
                if (insertData == true) {
                    Toast.makeText(Adder.this, "Successful", Toast.LENGTH_LONG).show();
                }
            }

            public void init(String time){
                boolean initialization=myDB.addData(time,null,null,null,null,null);
            }

            public void changing(String a, String b, String c, String d, String e, String f, String g){
                myDB.updater(a,b,c,d,e,f,g);
            }
            public void donothing(){


            }





}
