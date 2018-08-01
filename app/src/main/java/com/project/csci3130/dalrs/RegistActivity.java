package com.project.csci3130.dalrs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Regist activity.
 */
public class RegistActivity extends AppCompatActivity {
    DatabaseHelper myDB=new DatabaseHelper(this);
    DatabaseHelper2 myDB2=new DatabaseHelper2(this);
    DatabaseHelper3 myDB3=new DatabaseHelper3(this);
    /**
     * The Ref.
     */
    DatabaseReference ref;
    /**
     * The M reference.
     */
    DatabaseReference mReference;
    /**
     * The N reference.
     */
    DatabaseReference nReference;
    /**
     * The M ref.
     */
    DatabaseReference mRef;
    /**
     * The C ref.
     */
    DatabaseReference cRef;
    /**
     * The List view.
     */
    ListView listView;
    /**
     * The Button add.
     */
    Button buttonAdd;
    /**
     * The Button drop.
     */
    Button buttonDrop;
    /**
     * The Edit text.
     */
    EditText editText;
    /**
     * The Course.
     */
    Course course;
    /**
     * The Registration.
     */
    Registration registration;
    /**
     * The Course lec.
     */
    Course courseLec;
    /**
     * The User.
     */
    User user;
    /**
     * The Users.
     */
    List<String> users;
    private FirebaseListAdapter<Registration> adapter;
    /**
     * The Courses.
     */
    //public static ArrayList<Course> courses = new ArrayList<Course>();
    private static final String TAG = "TasksSample";
    /**
     * The Courses all.
     */
    ArrayList<Course> coursesAll = new ArrayList<>();
    /**
     * The Courses lec.
     */
    private ArrayList<Course> registedCourse = new ArrayList<>();

    ArrayList<Registration> registed = new ArrayList<>();
    //int currSpot;
    String registFee;
    public String tempID;
    String term;
    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setTitle("");
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buttonAdd = (Button) findViewById(R.id.button1);
        buttonDrop = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);

        //ref = FirebaseDatabase.getInstance().getReference("Courses");
       // mReference = FirebaseDatabase.getInstance().getReference("Registrations");
        mRef = FirebaseDatabase.getInstance().getReference("Registrations").child(LoginInterfaceActivity.uid).child(FirstFragment.termNumber);
        nReference = FirebaseDatabase.getInstance().getReference("Users");
        cRef = FirebaseDatabase.getInstance().getReference("Courses");
        //users = new ArrayList<String>();
        term = FirstFragment.termNumber;
        cRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    courseLec = ds.getValue(Course.class);
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
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    registration = ds.getValue(Registration.class);
                    registed.add(registration);
                }
                registedCourse = new ArrayList<>();
                for(int i=0;i<registed.size();i++){
                    for(int j=0;j<coursesAll.size();j++) {
                        if(coursesAll.get(j).getCourseID()!=null) {
                            if (coursesAll.get(j).getCourseID().equals(registed.get(i).getRegistCourseID())) {
                                    registedCourse.add(coursesAll.get(j));
                                }
                            }
                        }
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        nReference.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    user = ds.getValue(User.class);
                    Log.i(TAG,"userID = " + user.UID);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        adapter = new FirebaseListAdapter<Registration>(this, Registration.class,
                android.R.layout.simple_list_item_1, mRef) {
            @Override
            protected void populateView(View v, Registration model, int position) {
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText("CRN: " + model.getRegistCourseID() + "\nCourse Title: "
                        + model.getRegistTitle() + "   Course Type: " + model.getRegistType());
                Log.d(TAG, "courseTitle = " + textView);

            }
        };

        listView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//set add button click

                boolean c = false;
                tempID = editText.getText().toString();
                if(tempID.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter a input!",Toast.LENGTH_LONG).show();
                    editText.setError("No input!");
                    return;
                }
                for(int m = 0; m < coursesAll.size(); m++) {//check course term
                    String tempTerm = coursesAll.get(m).getCourseTerm();
                    String temp = coursesAll.get(m).getCourseID();

                    if (tempTerm.equals(term) && temp.equals(tempID)) {
                        c = true;
                    }
                }
                if(c == true){//if course term is corresponding term, add course.
                    try {
                        checkConflict();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(RegistActivity.this, "You may entered a CRN not belongs to current term", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
        buttonDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dropCourse();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        //set button drop click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {// set listView is clickable and jump to new activity
                detailed_courseview.setCourse(registedCourse.get(position));
                startActivity(new Intent(RegistActivity.this,detailed_courseview.class));
            }
        });
    }
    private void addCourse() throws java.text.ParseException {//add course
        tempID = editText.getText().toString();
        if(tempID.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter an input !")
                    .setTitle("WARNING!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to add the class?")
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String courseTitle = "";
                        String courseTerm = "";
                        int currSpot = 0;
                        boolean flag = false;
                        int t = 0;
                        if (tempID == null) {
                            Toast.makeText(RegistActivity.this, "No CRN input", Toast.LENGTH_LONG).show();
                        } else {
                            for (int i = 0; i < coursesAll.size(); i++) {
                                String temp = coursesAll.get(i).getCourseID();
                                if (temp.equals(tempID)) {
                                    course = coursesAll.get(i);
                                    courseTitle = course.getCourseTitle();
                                    currSpot = Integer.parseInt(coursesAll.get(i).getAvailableSpot().toString());
                                    break;
                                }
                            }
                            for (int i = 0; i < registedCourse.size(); i++) {
                                if (registedCourse.get(i).getCourseID().equals(tempID)) {
                                    flag = true;
                                }
                            }
                            if (currSpot > 0 && flag == false) {
                                String courseType = "";
                                for (int i = 0; i < coursesAll.size(); i++) {
                                    String temp = coursesAll.get(i).getCourseID();

                                    if (temp.equals(tempID)) {
                                        course = coursesAll.get(i);
                                        courseType = course.getCourseType();
                                        registFee = course.getCourseFee();
                                        String title = course.getCourseTitle();
                                        String day = course.getCourseDayTime();
                                        String time = course.getCourseTime();
                                        ArrayList<Character> weekday = new ArrayList<Character>();
                                        for (int x = 0; x < day.length(); x++)
                                            weekday.add(day.charAt(x));
                                        String startingtime = time.substring(0, 5);
                                        String endingtime = time.substring(6);
                                        SimpleDateFormat sim = new SimpleDateFormat("HH:mm");
                                        try{
                                        Date d = sim.parse(endingtime);
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTime(d);
                                        cal.add(Calendar.MINUTE, -20);
                                        String newTime = sim.format(cal.getTime());
                                        for (int j = 0; j < weekday.size(); j++) {
                                            if (weekday.get(j) == 'M' && tempID.charAt(0) == '1') {
                                                myDB.changes("Monday", title, startingtime);
                                                myDB.changes("Monday", title, newTime);
                                            } else if (weekday.get(j) == 'M' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Monday", title, startingtime);
                                                myDB2.changes("Monday", title, newTime);
                                            } else if (weekday.get(j) == 'M' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Monday", title, startingtime);
                                                myDB3.changes("Monday", title, newTime);
                                            } else if (weekday.get(j) == 'T' && tempID.charAt(0) == '1') {
                                                myDB.changes("Tuesday", title, startingtime);
                                                myDB.changes("Tuesday", title, newTime);
                                            } else if (weekday.get(j) == 'T' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Tuesday", title, startingtime);
                                                myDB2.changes("Tuesday", title, newTime);
                                            } else if (weekday.get(j) == 'T' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Tuesday", title, startingtime);
                                                myDB3.changes("Tuesday", title, newTime);
                                            } else if (weekday.get(j) == 'W' && tempID.charAt(0) == '1') {
                                                myDB.changes("Wednesday", title, startingtime);
                                                myDB.changes("Wednesday", title, newTime);
                                            } else if (weekday.get(j) == 'W' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Wednesday", title, startingtime);
                                                myDB2.changes("Wednesday", title, newTime);
                                            } else if (weekday.get(j) == 'W' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Wednesday", title, startingtime);
                                                myDB3.changes("Wednesday", title, newTime);
                                            } else if (weekday.get(j) == 'R' && tempID.charAt(0) == '1') {
                                                myDB.changes("Thursday", title, startingtime);
                                                myDB.changes("Thursday", title, newTime);
                                            } else if (weekday.get(j) == 'R' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Thursday", title, startingtime);
                                                myDB2.changes("Thursday", title, newTime);
                                            } else if (weekday.get(j) == 'R' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Thursday", title, startingtime);
                                                myDB3.changes("Thursday", title, newTime);
                                            } else if (weekday.get(j) == 'F' && tempID.charAt(0) == '1') {
                                                myDB.changes("Friday", title, startingtime);
                                                myDB.changes("Friday", title, newTime);
                                            } else if (weekday.get(j) == 'F' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Friday", title, startingtime);
                                                myDB2.changes("Friday", title, newTime);
                                            } else if (weekday.get(j) == 'F' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Friday", title, startingtime);
                                                myDB3.changes("Friday", title, newTime);
                                            }
                                        }
                                        }catch (ParseException e){

                                        }
                                    }
                                }
                                for (int i = 0; i < coursesAll.size(); i++) {
                                    String temp = coursesAll.get(i).getCourseID();
                                    String tempTerm = coursesAll.get(i).getCourseTerm();

                                    if (temp.equals(tempID) && tempTerm.equals(term)) {
                                        course = coursesAll.get(i);
                                        courseTerm = course.getCourseTerm();
                                    }
                                }
                                String id1 = LoginInterfaceActivity.uid;
                                Registration reg = new Registration(tempID, courseTitle, courseType, id1, courseTerm, registFee);
                                mRef.child(tempID).setValue(reg);
                                String spot = Integer.toString(currSpot - 1);
                                course.setAvailableSpot(spot);
                                cRef = FirebaseDatabase.getInstance().getReference("Courses");
                                cRef.child(tempID).setValue(course);
                            } else {
                                Toast.makeText(RegistActivity.this, "You cannot add this course", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registmenu, menu);
        return true;
    }
    private void dropCourse() throws ParseException {//drop class
        tempID = editText.getText().toString();
        if(tempID.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter an input !")
                    .setTitle("WARNING!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to drop the class?")
                .setCancelable(false)
                .setPositiveButton("Drop", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int maxSpot = 0;
                        int currSpot = 0;
                        boolean flag = false;
                        tempID = editText.getText().toString();
                        if (tempID == null) {
                            Toast.makeText(RegistActivity.this, "No CRN input", Toast.LENGTH_LONG).show();

                        } else {
                            for (int i = 0; i < coursesAll.size(); i++) {
                                String temp = coursesAll.get(i).getCourseID();
                                if (temp.equals(tempID)) {
                                    course = coursesAll.get(i);
                                    currSpot = Integer.parseInt(coursesAll.get(i).getAvailableSpot().toString());
                                    maxSpot = Integer.parseInt(coursesAll.get(i).getSpotMax().toString());
                                    String day = course.getCourseDayTime();
                                    String time = course.getCourseTime();
                                    ArrayList<Character> weekday = new ArrayList<Character>();
                                    for (int x = 0; x < day.length(); x++)
                                        weekday.add(day.charAt(x));
                                    String startingtime = time.substring(0, 5);
                                    String endingtime = time.substring(6);
                                    SimpleDateFormat sim = new SimpleDateFormat("HH:mm");
                                    try {
                                        Date d = sim.parse(endingtime);
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTime(d);
                                        cal.add(Calendar.MINUTE, -20);
                                        String newTime = sim.format(cal.getTime());
                                        for (int j = 0; j < weekday.size(); j++) {
                                            if (weekday.get(j) == 'M' && tempID.charAt(0) == '1') {
                                                myDB.changes("Monday", "", startingtime);
                                                myDB.changes("Monday", "", newTime);
                                            } else if (weekday.get(j) == 'M' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Monday", "", startingtime);
                                                myDB2.changes("Monday", "", newTime);
                                            } else if (weekday.get(j) == 'M' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Monday", "", startingtime);
                                                myDB3.changes("Monday", "", newTime);
                                            } else if (weekday.get(j) == 'T' && tempID.charAt(0) == '1') {
                                                myDB.changes("Tuesday", "", startingtime);
                                                myDB.changes("Tuesday", "", newTime);
                                            } else if (weekday.get(j) == 'T' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Tuesday", "", startingtime);
                                                myDB2.changes("Tuesday", "", newTime);
                                            } else if (weekday.get(j) == 'T' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Tuesday", "", startingtime);
                                                myDB3.changes("Tuesday", "", newTime);
                                            } else if (weekday.get(j) == 'W' && tempID.charAt(0) == '1') {
                                                myDB.changes("Wednesday", "", startingtime);
                                                myDB.changes("Wednesday", "", newTime);
                                            } else if (weekday.get(j) == 'W' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Wednesday", "", startingtime);
                                                myDB2.changes("Wednesday", "", newTime);
                                            } else if (weekday.get(j) == 'W' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Wednesday", "", startingtime);
                                                myDB3.changes("Wednesday", "", newTime);
                                            } else if (weekday.get(j) == 'R' && tempID.charAt(0) == '1') {
                                                myDB.changes("Thursday", "", startingtime);
                                                myDB.changes("Thursday", "", newTime);
                                            } else if (weekday.get(j) == 'R' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Thursday", "", startingtime);
                                                myDB2.changes("Thursday", "", newTime);
                                            } else if (weekday.get(j) == 'R' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Thursday", "", startingtime);
                                                myDB3.changes("Thursday", "", newTime);
                                            } else if (weekday.get(j) == 'F' && tempID.charAt(0) == '1') {
                                                myDB.changes("Friday", "", startingtime);
                                                myDB.changes("Friday", "", newTime);
                                            } else if (weekday.get(j) == 'F' && tempID.charAt(0) == '2') {
                                                myDB2.changes("Friday", "", startingtime);
                                                myDB2.changes("Friday", "", newTime);
                                            } else if (weekday.get(j) == 'F' && tempID.charAt(0) == '3') {
                                                myDB3.changes("Friday", "", startingtime);
                                                myDB3.changes("Friday", "", newTime);
                                            }
                                        }
                                    }catch (ParseException e){

                                    }
                                }
                            }
                            for (int i = 0; i < registedCourse.size(); i++) {
                                if (registedCourse.get(i).getCourseID().equals(tempID)) {
                                    flag = true;

                                }
                            }
                            if (currSpot < maxSpot && flag == true) {
                                for (int j = 0; j < registedCourse.size(); j++) {
                                    String temp = registedCourse.get(j).getCourseID();
                                    if (temp.equals(tempID)) {
                                        registedCourse.remove(j);
                                    }
                                }
                                mRef.child(tempID).removeValue();
                                String spot = Integer.toString(currSpot + 1);
                                course.setAvailableSpot(spot);
                                cRef = FirebaseDatabase.getInstance().getReference("Courses");
                                cRef.child(tempID).setValue(course);
                            } else {
                                Toast.makeText(RegistActivity.this, "You cannot drop this course", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
    }
    public static boolean testConflict(String leftStart, String leftEnd, String rightStart, String rightEnd) throws ParseException {
        Date leftStartTime=null, leftEndTime=null, rightStartTime=null, rightEndTime=null;
        try{
            leftStartTime = format.parse(leftStart);
            leftEndTime = format.parse(leftEnd);
            rightStartTime = format.parse(rightStart);
            rightEndTime = format.parse(rightEnd);
        }catch (ParseException e) {
            return false;
        }
        return
                ((leftStartTime.getTime() >= rightStartTime.getTime())
                        && (leftStartTime.getTime() < rightEndTime.getTime()))
                        || ((leftStartTime.getTime() > rightStartTime.getTime())
                        && (leftStartTime.getTime() <= rightEndTime.getTime()))
                        || ((rightStartTime.getTime() >= leftStartTime.getTime())
                        && (rightStartTime.getTime() < leftEndTime.getTime()))
                        || ((rightStartTime.getTime() > leftStartTime.getTime())
                        && (rightStartTime.getTime() <= leftEndTime.getTime())) ;
    }

    public void checkConflict() throws ParseException {
        boolean flag = false;

            //String tempDayTime;//the day time of registering course.
            //String courseDayTime;//the day time of registered course.
            String time1 = null;
            String time2 = null;
            ArrayList<String> day = new ArrayList<>();
            //ArrayList registeredDay = new ArrayList<>();
            //String tempRegistedID = null;
            for (int i = 0; i < coursesAll.size(); i++) {
                String temp = coursesAll.get(i).getCourseID();
                //tempRegistID = editText.getText().toString();
                if (temp.equals(tempID)) {
                    String courseDayTime = coursesAll.get(i).getCourseDayTime();
                    time1 = coursesAll.get(i).getCourseTime();
                    String[] courseday = courseDayTime.split("");
                    for (int k = 1; k < courseday.length; k++) {
                        if (courseday[k] != "") {
                            day.add(courseday[k]);
                        }
                    }
                }
            }
            for (int i = 0; i < registedCourse.size(); i++) {
                String tempDayTime = registedCourse.get(i).getCourseDayTime();
                String tempRegistedID = registedCourse.get(i).getCourseID();
                ArrayList registeredDay = new ArrayList<>();
                time2 = registedCourse.get(i).getCourseTime();
                String[] stringArray = tempDayTime.split("");
                for (int k = 1; k < stringArray.length; k++) {
                    if (stringArray[k] != "") {
                        registeredDay.add(stringArray[k]);
                    }
                }
                if (tempID.equals(tempRegistedID)) {
                   flag = false;

                }
               else {
                    for (int m = 0; m < registeredDay.size(); m++) {
                        if (day.contains(registeredDay.get(m))) {
                            String[] stringArray1 = time1.split("-");
                            String[] stringArray2 = time2.split("-");
                            flag = testConflict(stringArray1[0], stringArray1[1], stringArray2[0], stringArray2[1]);
                            if (flag == true) {
                                break;
                            }
                        }
                    }
                }
            }

        if(flag == false){

            Toast.makeText(RegistActivity.this, "Add course success!", Toast.LENGTH_LONG).show();
            addCourse();

            //time not conflict
        }
        else{
            Toast.makeText(RegistActivity.this, "Can not add course, time conflict !!!!", Toast.LENGTH_LONG).show();
            //time conflict
        }


    }
}
