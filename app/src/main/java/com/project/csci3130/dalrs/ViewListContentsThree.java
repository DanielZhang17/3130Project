package com.project.csci3130.dalrs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewListContentsThree extends AppCompatActivity {
    DatabaseHelper myDB;
    DatabaseHelper2 myDB2;
    DatabaseHelper3 myDB3;
    ArrayList<Use3> userList;
    ListView listview;
    Use3 users;
    private static int i=0;
    DatabaseReference mRef;
    DatabaseReference cRef;
    ArrayList<Course> coursesAll = new ArrayList<>();
    ArrayList<Registration> registed;
    ArrayList<Course> registedCourse = new ArrayList<>();
    Registration registration;
    public static String uid = LoginInterfaceActivity.uid;

    public ViewListContentsThree(){ }
    public ViewListContentsThree(DatabaseHelper3 dbh){
        myDB3=dbh;


    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seetablethree);

        myDB3=new DatabaseHelper3(this);
        if (i == 0) {

            myDB3.addData(null,"Monday","Tuesday","Wednesday","Thursday","Friday");
            myDB3.addData("08:05",null,null,null,null,null);
            myDB3.addData("08:35",null,null,null,null,null);
            myDB3.addData("09:05",null,null,null,null,null);
            myDB3.addData("09:35",null,null,null,null,null);
            myDB3.addData("10:05",null,null,null,null,null);
            myDB3.addData("10:35",null,null,null,null,null);
            myDB3.addData("11:05",null,null,null,null,null);
            myDB3.addData("11:35",null,null,null,null,null);
            myDB3.addData("12:05",null,null,null,null,null);
            myDB3.addData("12:35",null,null,null,null,null);
            myDB3.addData("13:05",null,null,null,null,null);
            myDB3.addData("13:35",null,null,null,null,null);
            myDB3.addData("14:05",null,null,null,null,null);
            myDB3.addData("14:35",null,null,null,null,null);
            myDB3.addData("15:05",null,null,null,null,null);
            myDB3.addData("15:35",null,null,null,null,null);
            myDB3.addData("16:05",null,null,null,null,null);
            myDB3.addData("16:35",null,null,null,null,null);
            myDB3.addData("17:05",null,null,null,null,null);
            myDB3.addData("17:35",null,null,null,null,null);
            myDB3.addData("18:05",null,null,null,null,null);
            myDB3.addData("18:35",null,null,null,null,null);
            myDB3.addData("19:05",null,null,null,null,null);
            myDB3.addData("19:35",null,null,null,null,null);
            myDB3.addData("20:05",null,null,null,null,null);
            myDB3.addData("20:35",null,null,null,null,null);
            myDB3.addData("21:05",null,null,null,null,null);
            myDB3.addData("21:35",null,null,null,null,null);

            i=1;
        }
        mRef = FirebaseDatabase.getInstance().getReference("Registrations").child(uid).child("3");
        cRef = FirebaseDatabase.getInstance().getReference("Courses");
        cRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Course courseLec = ds.getValue(Course.class);
                    coursesAll.add(courseLec);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                registed = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    registration = ds.getValue(Registration.class);
                    registed.add(registration);
                }
                registedCourse = new ArrayList<>();
                for (int i = 0; i < registed.size(); i++) {
                    for (int j = 0; j < coursesAll.size(); j++) {
                        if (coursesAll.get(j).getCourseID() != null) {
                            if (coursesAll.get(j).getCourseID().equals(registed.get(i).getRegistCourseID())) {
                                registedCourse.add(coursesAll.get(j));
                            }
                        }
                    }
                }
                timeTable();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

    public int geti(){
        return i;
    }

    public void timeTable(){
        for (int k=0;
             k<registedCourse.size();k++){
            String term=registedCourse.get(k).getCourseTerm();
            String title=registedCourse.get(k).getCourseTitle();
            String day=registedCourse.get(k).getCourseDayTime();
            String time=registedCourse.get(k).getCourseTime();
            ArrayList<Character> weekday=new ArrayList<Character> ();
            for (int x=0;x<day.length();x++)
                weekday.add(day.charAt(x));
            String startingtime=time.substring(0,5);
            String endingtime=time.substring(6);
            SimpleDateFormat sim=new SimpleDateFormat("HH:mm");
            Date d= null;
            try {
                d = sim.parse(endingtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal=Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE,-20);
            String newTime=sim.format(cal.getTime());
            for (int j=0;j<weekday.size();j++) {
                if (weekday.get(j) == 'M' && term.equals("1")) {
                    myDB.changes("Monday", title, startingtime);
                    myDB.changes("Monday", title, newTime);
                } else if (weekday.get(j) == 'M' && term.equals("2")) {
                    myDB2.changes("Monday", title, startingtime);
                    myDB2.changes("Monday", title, newTime);
                } else if (weekday.get(j) == 'M' && term.equals("3")) {
                    myDB3.changes("Monday", title, startingtime);
                    myDB3.changes("Monday", title, newTime);
                } else if (weekday.get(j) == 'T' && term.equals("1")) {
                    myDB.changes("Tuesday", title, startingtime);
                    myDB.changes("Tuesday", title, newTime);
                } else if (weekday.get(j) == 'T' && term.equals("2")){
                    myDB2.changes("Tuesday", title, startingtime);
                    myDB2.changes("Tuesday", title, newTime);
                } else if (weekday.get(j) == 'T' && term.equals("3")) {
                    myDB3.changes("Tuesday", title, startingtime);
                    myDB3.changes("Tuesday", title, newTime);
                } else if (weekday.get(j) == 'W' && term.equals("1")) {
                    myDB.changes("Wednesday", title, startingtime);
                    myDB.changes("Wednesday", title, newTime);
                } else if (weekday.get(j) == 'W' && term.equals("2")) {
                    myDB2.changes("Wednesday", title, startingtime);
                    myDB2.changes("Wednesday", title, newTime);
                } else if (weekday.get(j) == 'W' && term.equals("3")) {
                    myDB3.changes("Wednesday", title, startingtime);
                    myDB3.changes("Wednesday", title, newTime);
                } else if (weekday.get(j) == 'R' && term.equals("1")) {
                    myDB.changes("Thursday", title, startingtime);
                    myDB.changes("Thursday", title, newTime);
                } else if (weekday.get(j) == 'R' && term.equals("2")) {
                    myDB2.changes("Thursday", title, startingtime);
                    myDB2.changes("Thursday", title, newTime);
                } else if (weekday.get(j) == 'R' && term.equals("3")) {
                    myDB3.changes("Thursday", title, startingtime);
                    myDB3.changes("Thursday", title, newTime);
                } else if (weekday.get(j) == 'F' && term.equals("1")) {
                    myDB.changes("Friday", title, startingtime);
                    myDB.changes("Friday", title, newTime);
                } else if (weekday.get(j) == 'F' && term.equals("2")) {
                    myDB2.changes("Friday", title, startingtime);
                    myDB2.changes("Friday", title, newTime);
                } else if (weekday.get(j) == 'F' && term.equals("3")) {
                    myDB3.changes("Friday", title, startingtime);
                    myDB3.changes("Friday", title, newTime);
                }
            }
        }
        userList= new ArrayList<>();
        Cursor data=myDB3.getListContents();
        int numRows=data.getCount();
        if (numRows==0)
            Toast.makeText(ViewListContentsThree.this,"Nothing in database", Toast.LENGTH_LONG).show();
        else{
            int i=0;
            while(data.moveToNext()) {
                users = new Use3(data.getString(1),data.getString(2), data.getString(3), data.getString(4),data.getString(5),data.getString(6));
                userList.add(users);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                System.out.println(userList.get(i).gettime());
                i++;
            }


            ThreeColumn_ListAdapter3 adapter=new ThreeColumn_ListAdapter3(this,R.layout.timetable3,userList);
            listview=(ListView) findViewById(R.id.listView3);
            listview.setAdapter(adapter);
        }
    }
}


