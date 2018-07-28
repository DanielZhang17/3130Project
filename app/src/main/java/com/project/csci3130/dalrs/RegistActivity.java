package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

/**
 * The type Regist activity.
 */
public class RegistActivity extends AppCompatActivity {
    DatabaseHelper myDB=new DatabaseHelper(this);
    DatabaseHelper2 myDB2=new DatabaseHelper2(this);
    DatabaseHelper3 myDB3=new DatabaseHelper3(this);
    private static int i=0;
    ViewListContents v=new ViewListContents();

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
     * The constant selected.
     */
    public static Course selected;
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
    ArrayList<Course> courses = new ArrayList<Course>();
    private static final String TAG = "TasksSample";
    /**
     * The Courses all.
     */
    ArrayList<Course> coursesAll = new ArrayList<>();
    /**
     * The Courses lec.
     */
    ArrayList<Course> coursesLec = new ArrayList<>();
    ArrayList<Course> registedCourse = new ArrayList<>();
    ArrayList<Course> courseSpot= new ArrayList<>();

    ArrayList<Registration> registed = new ArrayList<>();
    int currSpot;
    String registFee;
    DatabaseReference wRef;
    public static String tempID;
    public static String tempSpot;
    Course c1;

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


        ref = FirebaseDatabase.getInstance().getReference("Courses");
        mReference = FirebaseDatabase.getInstance().getReference("Registrations");
        mRef = FirebaseDatabase.getInstance().getReference("Registrations").child(LoginInterfaceActivity.uid).child(FirstFragment.termNumber);
        nReference = FirebaseDatabase.getInstance().getReference("Users");
        cRef = FirebaseDatabase.getInstance().getReference("Courses");
        users = new ArrayList<String>();
        cRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*for(DataSnapshot ds: dataSnapshot.getChildren()){
                    courseLec = ds.getValue(Course.class);
                    coursesAll.add(courseLec);
                    for(int i = 0; i < coursesAll.size(); i++){
                        String lec = coursesAll.get(i).getCourseType();
                        if(lec.equals("Lec")){
                            coursesLec.add(coursesAll.get(i));
                        }
                    }
                }*/
                courseLec=dataSnapshot.getValue(Course.class);
                coursesLec.add(courseLec);
                for (int i=0;i<coursesAll.size();i++){
                        String lec=coursesAll.get(i).getCourseType();
                        if (lec.equals("Lec")){
                            coursesLec.add(coursesAll.get(i));
                        }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*for(DataSnapshot ds: dataSnapshot.getChildren()){
                    registration = ds.getValue(Registration.class);
                    registed.add(registration);
                }*/
                registration=dataSnapshot.getValue(Registration.class);
                registed.add(registration);
                for(int i=0;i<registed.size();i++){
                    for(int j=0;j<coursesAll.size();j++) {
                        if(coursesAll.get(j).getCourseID()!=null) {
                            if (coursesAll.get(j).getCourseID().equals(registed.get(i).getRegistCourseID())) {
                                registedCourse.add(coursesAll.get(j));
                                break;
                            }
                        }
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*for(DataSnapshot ds: dataSnapshot.getChildren()){
                    course = ds.getValue(Course.class);
                    courses.add(course);
                }*/
                course=dataSnapshot.getValue(Course.class);
                courses.add(course);
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



        //myDB.updater("3","8:35",null,null,null,null,null);
        //myDB.updater("4","9:05",null,null,null,null,null);
        //myDB.updater("5","9:35",null,null,null,null,null);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//set add button click
                //temp code begins
                //temp code ends
                String checkTerm = FirstFragment.termNumber;
                String courseId = editText.getText().toString();

                boolean c = false;
                for (int m = 0; m < courses.size(); m++) {//check course term
                    String tempTerm = courses.get(m).getCourseTerm();
                    String temp = courses.get(m).getCourseID();

                    //if (tempTerm.equals(checkTerm) && temp.equals(courseId)) {
                    //  c = true;
                    //}
                    //}
                    if (courseId.equals("10001")) {
                        myDB.changes("Monday", "CSCI3110", "8:35");
                        myDB.changes("Monday", "CSCI3110", "9:05");
                        myDB.changes("Monday", "CSCI3110", "9:35");
                        myDB.changes("Wednesday", "CSCI3110", "8:35");
                        myDB.changes("Wednesday", "CSCI3110", "9:05");
                        myDB.changes("Wednesday", "CSCI3110", "9:35");
                    }
                    if (courseId.equals("10003")) {
                        myDB.changes("Tuesday", "CSCI2141", "14:35");
                        myDB.changes("Tuesday", "CSCI2141", "15:05");
                        myDB.changes("Tuesday", "CSCI2141", "15:35");
                        myDB.changes("Thursday", "CSCI2141", "14:35");
                        myDB.changes("Thursday", "CSCI2141", "15:05");
                        myDB.changes("Thursday", "CSCI2141", "15:35");
                    }
                    if (courseId.equals("10005")) {
                        myDB.changes("Tuesday", "CSCI1108", "8:35");
                        myDB.changes("Tuesday", "CSCI1108", "9:05");
                        myDB.changes("Tuesday", "CSCI1108", "9:35");
                        myDB.changes("Thursday", "CSCI1108", "8:35");
                        myDB.changes("Thursday", "CSCI1108", "9:05");
                        myDB.changes("Thursday", "CSCI1108", "9:35");

                    }
                    if (courseId.equals("10007")) {
                        myDB.changes("Monday", "CSCI 1105", "10:05");
                        myDB.changes("Monday", "CSCI 1105", "10:35");
                        myDB.changes("Monday", "CSCI 1105", "11:05");
                        myDB.changes("Wednesday", "CSCI 1105", "10:05");
                        myDB.changes("Wednesday", "CSCI 1105", "10:35");
                        myDB.changes("Wednesday", "CSCI 1105", "11:05");
                    }
                    if (courseId.equals("10877")) {
                        myDB.changes("Monday", "ECON 1101", "14:35");
                        myDB.changes("Monday", "ECON 1101", "15:05");
                        myDB.changes("Wednesday", "ECON 1101", "14:35");
                        myDB.changes("Wednesday", "ECON 1101", "15:05");
                    }
                    if (courseId.equals("10915")) {
                        myDB.changes("Monday", "ECON 2200", "16:05");
                        myDB.changes("Monday", "ECON 2200", "16:35");
                        myDB.changes("Monday", "ECON 2200", "17:05");
                        myDB.changes("Wednesday", "ECON 2200", "16:05");
                        myDB.changes("Wednesday", "ECON 2200", "16:35");
                        myDB.changes("Wednesday", "ECON 2200", "17:05");
                        myDB.changes("Friday", "ECON 2200", "16:05");
                        myDB.changes("Friday", "ECON 2200", "16:35");
                        myDB.changes("Friday", "ECON 2200", "17:05");
                    }
                    if (courseId.equals("12563")) {
                        myDB.changes("Monday", "STAT 1060", "13:35");
                        myDB.changes("Monday", "STAT 1060", "14:05");
                        myDB.changes("Wednesday", "STAT 1060", "13:35");
                        myDB.changes("Wednesday", "STAT 1060", "14:05");
                        myDB.changes("Friday", "STAT 1060", "13:35");
                        myDB.changes("Friday", "STAT 1060", "14:05");

                    }
                    if (courseId.equals("12575")) {
                        myDB.changes("Monday", "STAT 2060", "10:35");
                        myDB.changes("Monday", "STAT 2060", "11:05");
                        myDB.changes("Wednesday", "STAT 2060", "10:35");
                        myDB.changes("Wednesday", "STAT 2060", "11:05");
                        myDB.changes("Friday", "STAT 2060", "10:35");
                        myDB.changes("Friday", "STAT 2060", "11:05");
                    }
                    if (courseId.equals("12584")) {
                        myDB.changes("Monday", "STAT 2600", "10:05");
                        myDB.changes("Monday", "STAT 2600", "10:35");
                        myDB.changes("Monday", "STAT 2600", "11:05");
                        myDB.changes("Wednesday", "STAT 2600", "10:05");
                        myDB.changes("Wednesday", "STAT 2600", "10:35");
                        myDB.changes("Wednesday", "STAT 2600", "11:05");

                    }
                    if (courseId.equals("12586")) {
                        myDB.changes("Tuesday", "STAT 3360", "10:05");
                        myDB.changes("Tuesday", "STAT 3360", "10:35");
                        myDB.changes("Tuesday", "STAT 3360", "11:05");
                        myDB.changes("Thursday", "STAT 3360", "10:05");
                        myDB.changes("Thursday", "STAT 3360", "10:35");
                        myDB.changes("Thursday", "STAT 3360", "11:05");

                    }
                    if (courseId.equals("12587")) {
                        myDB.changes("Monday", "STAT 4066", "14:35");
                        myDB.changes("Monday", "STAT 4066", "15:05");
                        myDB.changes("Wednesday", "STAT 4066", "14:35");
                        myDB.changes("Wednesday", "STAT 4066", "15:05");
                        myDB.changes("Friday", "STAT 4066", "14:35");
                        myDB.changes("Friday", "STAT 4066", "15:05");
                    }
                    if (courseId.equals("20638")) {
                        myDB2.changes("Monday", "CSCI 1170", "8:35");
                        myDB2.changes("Monday", "CSCI 1170", "9:05");
                        myDB2.changes("Monday", "CSCI 1170", "9:35");
                        myDB2.changes("Tuesday", "CSCI 1170", "8:35");
                        myDB2.changes("Tuesday", "CSCI 1170", "9:05");
                        myDB2.changes("Tuesday", "CSCI 1170", "9:35");
                    }
                    if (courseId.equals("20650")) {
                        myDB2.changes("Monday", "CSCI2100", "14:35");
                        myDB2.changes("Monday", "CSCI2100", "15:05");
                        myDB2.changes("Wednesday", "CSCI2100", "14:35");
                        myDB2.changes("Wednesday", "CSCI2100", "15:05");
                        myDB2.changes("Friday", "CSCI2100", "14:35");
                        myDB2.changes("Friday", "CSCI2100", "15:05");

                    }
                    if (courseId.equals("20651")) {
                        myDB2.changes("Wednesday", "CSCI2110", "8:35");
                        myDB2.changes("Wednesday", "CSCI2110", "9:05");
                        myDB2.changes("Wednesday", "CSCI2110", "9:35");
                        myDB2.changes("Friday", "CSCI2110", "8:35");
                        myDB2.changes("Friday", "CSCI2110", "9:05");
                        myDB2.changes("Friday", "CSCI2110", "9:35");

                    }
                    if (courseId.equals("20689")) {
                        myDB2.changes("Monday", "CSCI3130", "8:35");
                        myDB2.changes("Monday", "CSCI3120", "9:05");
                        myDB2.changes("Monday", "CSCI3120", "9:35");
                        myDB2.changes("Wednesday", "CSCI3130", "8:35");
                        myDB2.changes("Wednesday", "CSCI3120", "9:05");
                        myDB2.changes("Wednesday", "CSCI3120", "9:35");
                    }
                    if (courseId.equals("20720")) {
                        myDB2.changes("Tuesday", "CSCI4174", "13:35");
                        myDB2.changes("Tuesday", "CSCI4174", "14:05");
                        myDB2.changes("Tuesday", "CSCI4174", "14:35");
                        myDB2.changes("Thursday", "CSCI4174", "13:35");
                        myDB2.changes("Thursday", "CSCI4174", "14:05");
                        myDB2.changes("Thursday", "CSCI4174", "14:35");
                        myDB2.changes("Friday", "CSCI4174", "13:35");
                        myDB2.changes("Friday", "CSCI4174", "14:05");
                        myDB2.changes("Friday", "CSCI4174", "14:35");
                    }
                    if (courseId.equals("20807")) {
                        myDB2.changes("Monday", "ECON1101", "16:05");
                        myDB2.changes("Monday", "ECON1101", "16:35");
                        myDB2.changes("Monday", "ECON1101", "17:05");
                        myDB2.changes("Wednesday", "ECON1101", "16:05");
                        myDB2.changes("Wednesday", "ECON1101", "16:35");
                        myDB2.changes("Wednesday", "ECON1101", "17:05");
                    }
                    if (courseId.equals("22361")) {
                        myDB2.changes("Tuesday", "STAT 1060", "13:05");
                        myDB2.changes("Tuesday", "STAT 1060", "13:35");
                        myDB2.changes("Tuesday", "STAT 1060", "14:05");
                        myDB2.changes("Thursday", "STAT 1060", "13:05");
                        myDB2.changes("Thursday", "STAT 1060", "13:35");
                        myDB2.changes("Thursday", "STAT 1060", "14:05");
                    }
                    if (courseId.equals("22384")) {
                        myDB2.changes("Monday", "STAT 2060", "10:35");
                        myDB2.changes("Monday", "STAT 2060", "11:05");
                        myDB2.changes("Wednesday", "STAT 2060", "10:35");
                        myDB2.changes("Wednesday", "STAT 2060", "11:05");
                        myDB2.changes("Friday", "STAT 2060", "10:35");
                        myDB2.changes("Friday", "STAT 2060", "11:05");

                    }
                    if (courseId.equals("22390")) {
                        myDB2.changes("Monday", "STAT2080", "14:35");
                        myDB2.changes("Monday", "STAT2080", "15:05");
                        myDB2.changes("Wednesday", "STAT2080", "14:35");
                        myDB2.changes("Wednesday", "STAT2080", "15:05");
                        myDB2.changes("Friday", "STAT2080", "14:35");
                        myDB2.changes("Friday", "STAT2080", "15:05");

                    }
                    if (courseId.equals("22397")) {
                        myDB2.changes("Monday", "STAT3380", "14:35");
                        myDB2.changes("Monday", "STAT3380", "15:05");
                        myDB2.changes("Monday", "STAT3380", "15:35");
                        myDB2.changes("Wednesday", "STAT3380", "14:35");
                        myDB2.changes("Wednesday", "STAT3380", "15:05");
                        myDB2.changes("Wednesday", "STAT3380", "15:35");
                    }
                    if (courseId.equals("22401")) {
                        myDB2.changes("Monday", "STAT4390", "16:05");
                        myDB2.changes("Monday", "STAT4390", "16:35");
                        myDB2.changes("Monday", "STAT4390", "17:05");
                        myDB2.changes("Wednesday", "STAT4390", "16:05");
                        myDB2.changes("Wednesday", "STAT4390", "16:35");
                        myDB2.changes("Wednesday", "STAT4390", "17:05");

                    }
                    if (courseId.equals("31268")) {
                        myDB3.changes("Monday", "CSCI1105", "13:05");
                        myDB3.changes("Monday", "CSCI1105", "13:35");
                        myDB3.changes("Monday", "CSCI1105", "14:05");
                        myDB3.changes("Wednesday", "CSCI1105", "13:05");
                        myDB3.changes("Wednesday", "CSCI1105", "13:35");
                        myDB3.changes("Wednesday", "CSCI1105", "14:05");
                    }
                    if (courseId.equals("31270")) {
                        myDB3.changes("Monday", "CSCI1110", "13:05");
                        myDB3.changes("Monday", "CSCI1110", "13:35");
                        myDB3.changes("Monday", "CSCI1110", "14:05");
                        myDB3.changes("Wednesday", "CSCI1110", "13:05");
                        myDB3.changes("Wednesday", "CSCI1110", "13:35");
                        myDB3.changes("Wednesday", "CSCI1110", "14:05");

                    }
                    if (courseId.equals("31272")) {
                        myDB3.changes("Tuesday", "CSCI2691", "11:35");
                        myDB3.changes("Tuesday", "CSCI2691", "12:05");
                        myDB3.changes("Tuesday", "CSCI2691", "12:35");
                        myDB3.changes("Thursday", "CSCI2691", "11:35");
                        myDB3.changes("Thursday", "CSCI2691", "12:05");
                        myDB3.changes("Thursday", "CSCI2691", "12:35");
                    }
                    if (courseId.equals("31273")) {
                        myDB3.changes("Tuesday", "CSCI3110", "14:35");
                        myDB3.changes("Tuesday", "CSCI3110", "15:05");
                        myDB3.changes("Tuesday", "CSCI3110", "15:35");
                        myDB3.changes("Thursday", "CSCI3110", "14:35");
                        myDB3.changes("Thursday", "CSCI3110", "15:05");
                        myDB3.changes("Thursday", "CSCI3110", "15:35");

                    }
                    if (courseId.equals("31460")) {
                        myDB3.changes("Monday", "STAT2060", "18:05");
                        myDB3.changes("Monday", "STAT2060", "18:35");
                        myDB3.changes("Monday", "STAT2060", "19:05");
                        myDB3.changes("Monday", "STAT2060", "19:35");
                        myDB3.changes("Monday", "STAT2060", "20:05");
                        myDB3.changes("Monday", "STAT2060", "20:35");
                        myDB3.changes("Monday", "STAT2060", "21:05");
                        myDB3.changes("Wednesday", "STAT2060", "18:05");
                        myDB3.changes("Wednesday", "STAT2060", "18:35");
                        myDB3.changes("Wednesday", "STAT2060", "19:05");
                        myDB3.changes("Wednesday", "STAT2060", "19:35");
                        myDB3.changes("Wednesday", "STAT2060", "20:05");
                        myDB3.changes("Wednesday", "STAT2060", "20:35");
                        myDB3.changes("Wednesday", "STAT2060", "21:05");

                    }
                    if (courseId.equals("31803")) {
                        myDB3.changes("Monday", "STAT1060", "18:05");
                        myDB3.changes("Monday", "STAT1060", "18:35");
                        myDB3.changes("Monday", "STAT1060", "19:05");
                        myDB3.changes("Monday", "STAT1060", "19:35");
                        myDB3.changes("Monday", "STAT1060", "20:05");
                        myDB3.changes("Monday", "STAT1060", "20:35");
                        myDB3.changes("Wednesday", "STAT1060", "18:05");
                        myDB3.changes("Wednesday", "STAT1060", "18:35");
                        myDB3.changes("Wednesday", "STAT1060", "19:05");
                        myDB3.changes("Wednesday", "STAT1060", "19:35");
                        myDB3.changes("Wednesday", "STAT1060", "20:05");
                        myDB3.changes("Wednesday", "STAT1060", "20:35");

                    }
                    if (courseId.equals("31836")) {
                        myDB3.changes("Monday", "STAT2080", "18:05");
                        myDB3.changes("Monday", "STAT2080", "18:35");
                        myDB3.changes("Monday", "STAT2080", "19:05");
                        myDB3.changes("Monday", "STAT2080", "19:35");
                        myDB3.changes("Monday", "STAT2080", "20:05");
                        myDB3.changes("Monday", "STAT2080", "20:35");
                        myDB3.changes("Wednesday", "STAT2080", "18:05");
                        myDB3.changes("Wednesday", "STAT2080", "18:35");
                        myDB3.changes("Wednesday", "STAT2080", "19:05");
                        myDB3.changes("Wednesday", "STAT2080", "19:35");
                        myDB3.changes("Wednesday", "STAT2080", "20:05");
                        myDB3.changes("Wednesday", "STAT2080", "20:35");

                    }
                }




                    if(c == true){//if course term is corresponding term, add course.
                    addCourse();
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
                String courseId = editText.getText().toString();
                if (courseId.equals("10001")) {
                    myDB.changes("Monday","","8:35");
                    myDB.changes("Monday","","9:05");
                    myDB.changes("Monday","","9:35");
                    myDB.changes("Wednesday","","8:35");
                    myDB.changes("Wednesday","","9:05");
                    myDB.changes("Wednesday","","9:35");
                } else if (courseId.equals("10003")) {
                    myDB.changes("Tuesday","","14:35");
                    myDB.changes("Tuesday","","15:05");
                    myDB.changes("Tuesday","","15:35");
                    myDB.changes("Thursday","","14:35");
                    myDB.changes("Thursday","","15:05");
                    myDB.changes("Thursday","","15:35");
                }else if (courseId.equals("10005")){
                    myDB.changes("Tuesday","","8:35");
                    myDB.changes("Tuesday","","9:05");
                    myDB.changes("Tuesday","","9:35");
                    myDB.changes("Thursday","","8:35");
                    myDB.changes("Thursday","","9:05");
                    myDB.changes("Thursday","","9:35");

                }else if (courseId.equals("10007")){
                    myDB.changes("Monday","","10:05");
                    myDB.changes("Monday","","10:35");
                    myDB.changes("Monday","","11:05");
                    myDB.changes("Wednesday","","10:05");
                    myDB.changes("Wednesday","","10:35");
                    myDB.changes("Wednesday","","11:05");
                }else if (courseId.equals("10877")){
                    myDB.changes("Monday","","14:35");
                    myDB.changes("Monday","","15:05");
                    myDB.changes("Wednesday","","14:35");
                    myDB.changes("Wednesday","","15:05");
                }else if (courseId.equals("10915")){
                    myDB.changes("Monday","","16:05");
                    myDB.changes("Monday","","16:35");
                    myDB.changes("Monday","","17:05");
                    myDB.changes("Wednesday","","16:05");
                    myDB.changes("Wednesday","","16:35");
                    myDB.changes("Wednesday","","17:05");
                    myDB.changes("Friday","","16:05");
                    myDB.changes("Friday","","16:35");
                    myDB.changes("Friday","","17:05");
                }else if (courseId.equals("12563")) {
                    myDB.changes("Monday","","13:35");
                    myDB.changes("Monday","", "14:05");
                    myDB.changes("Wednesday","","13:35");
                    myDB.changes("Wednesday","","14:05");
                    myDB.changes("Friday","","13:35");
                    myDB.changes("Friday","","14:05");

                }else if (courseId.equals("12575")){
                    myDB.changes("Monday","","10:35");
                    myDB.changes("Monday","", "11:05");
                    myDB.changes("Wednesday","","10:35");
                    myDB.changes("Wednesday","", "11:05");
                    myDB.changes("Friday","","10:35");
                    myDB.changes("Friday","", "11:05");
                }else if (courseId.equals("12584")){
                    myDB.changes("Monday","", "10:05");
                    myDB.changes("Monday","", "10:35");
                    myDB.changes("Monday","", "11:05");
                    myDB.changes("Wednesday","", "10:05");
                    myDB.changes("Wednesday","", "10:35");
                    myDB.changes("Wednesday","", "11:05");

                }else if (courseId.equals("12586")){
                    myDB.changes("Tuesday","","10:05");
                    myDB.changes("Tuesday","","10:35");
                    myDB.changes("Tuesday","","11:05");
                    myDB.changes("Thursday","","10:05");
                    myDB.changes("Thursday","","10:35");
                    myDB.changes("Thursday","","11:05");

                }else if (courseId.equals("12587")){
                    myDB.changes("Monday","","14:35");
                    myDB.changes("Monday","","15:05");
                    myDB.changes("Wednesday","","14:35");
                    myDB.changes("Wednesday","","15:05");
                    myDB.changes("Friday","","14:35");
                    myDB.changes("Friday","","15:05");
                }else if (courseId.equals("20638")){
                    myDB2.changes("Monday","","8:35");
                    myDB2.changes("Monday","","9:05");
                    myDB2.changes("Monday","","9:35");
                    myDB2.changes("Tuesday","","8:35");
                    myDB2.changes("Tuesday","","9:05");
                    myDB2.changes("Tuesday","","9:35");
                }else if (courseId.equals("20650")){
                    myDB2.changes("Monday","","14:35");
                    myDB2.changes("Monday","","15:05");
                    myDB2.changes("Wednesday","","14:35");
                    myDB2.changes("Wednesday","","15:05");
                    myDB2.changes("Friday","","14:35");
                    myDB2.changes("Friday","","15:05");

                }else if (courseId.equals("20651")){
                    myDB2.changes("Wednesday","","8:35");
                    myDB2.changes("Wednesday","","9:05");
                    myDB2.changes("Wednesday","","9:35");
                    myDB2.changes("Friday","","8:35");
                    myDB2.changes("Friday","","9:05");
                    myDB2.changes("Friday","","9:35");

                }else if (courseId.equals("20689")){
                    myDB2.changes("Monday","","8:35");
                    myDB2.changes("Monday","","9:05");
                    myDB2.changes("Monday","","9:35");
                    myDB2.changes("Wednesday","","8:35");
                    myDB2.changes("Wednesday","","9:05");
                    myDB2.changes("Wednesday","","9:35");
                }else if (courseId.equals("20720")){
                    myDB2.changes("Tuesday","","13:35");
                    myDB2.changes("Tuesday","","14:05");
                    myDB2.changes("Tuesday","","14:35");
                    myDB2.changes("Thursday","","13:35");
                    myDB2.changes("Thursday","","14:05");
                    myDB2.changes("Thursday","","14:35");
                    myDB2.changes("Friday","","13:35");
                    myDB2.changes("Friday","","14:05");
                    myDB2.changes("Friday","","14:35");
                }else if (courseId.equals("20807")){
                    myDB2.changes("Monday","","16:05");
                    myDB2.changes("Monday","","16:35");
                    myDB2.changes("Monday","","17:05");
                    myDB2.changes("Wednesday","","16:05");
                    myDB2.changes("Wednesday","","16:35");
                    myDB2.changes("Wednesday","","17:05");
                }else if (courseId.equals("22361")){
                    myDB2.changes("Tuesday","","13:05");
                    myDB2.changes("Tuesday","","13:35");
                    myDB2.changes("Tuesday","","14:05");
                    myDB2.changes("Thursday","","13:05");
                    myDB2.changes("Thursday","","13:35");
                    myDB2.changes("Thursday","","14:05");
                }else if (courseId.equals("22384")){
                    myDB2.changes("Monday","","10:35");
                    myDB2.changes("Monday","","11:05");
                    myDB2.changes("Wednesday","","10:35");
                    myDB2.changes("Wednesday","","11:05");
                    myDB2.changes("Friday","","10:35");
                    myDB2.changes("Friday","","11:05");

                }else if (courseId.equals("22390")){
                    myDB2.changes("Monday","","14:35");
                    myDB2.changes("Monday","","15:05");
                    myDB2.changes("Wednesday","","14:35");
                    myDB2.changes("Wednesday","","15:05");
                    myDB2.changes("Friday","","14:35");
                    myDB2.changes("Friday","","15:05");

                }else if (courseId.equals("22397")){
                    myDB2.changes("Monday","","14:35");
                    myDB2.changes("Monday","","15:05");
                    myDB2.changes("Monday","","15:35");
                    myDB2.changes("Wednesday","","14:35");
                    myDB2.changes("Wednesday","","15:05");
                    myDB2.changes("Wednesday","","15:35");
                }else if (courseId.equals("22401")){
                    myDB2.changes("Monday","","16:05");
                    myDB2.changes("Monday","","16:35");
                    myDB2.changes("Monday","","17:05");
                    myDB2.changes("Wednesday","","16:05");
                    myDB2.changes("Wednesday","","16:35");
                    myDB2.changes("Wednesday","","17:05");

                }else if (courseId.equals("31268")){
                    myDB3.changes("Monday","","13:05");
                    myDB3.changes("Monday","","13:35");
                    myDB3.changes("Monday","","14:05");
                    myDB3.changes("Wednesday","","13:05");
                    myDB3.changes("Wednesday","","13:35");
                    myDB3.changes("Wednesday","","14:05");
                }else if (courseId.equals("31270")){
                    myDB3.changes("Monday","","13:05");
                    myDB3.changes("Monday","","13:35");
                    myDB3.changes("Monday","","14:05");
                    myDB3.changes("Wednesday","","13:05");
                    myDB3.changes("Wednesday","","13:35");
                    myDB3.changes("Wednesday","","14:05");

                }else if (courseId.equals("31272")){
                    myDB3.changes("Tuesday","","11:35");
                    myDB3.changes("Tuesday","","12:05");
                    myDB3.changes("Tuesday","","12:35");
                    myDB3.changes("Thursday","","11:35");
                    myDB3.changes("Thursday","","12:05");
                    myDB3.changes("Thursday","","12:35");
                }else if (courseId.equals("31273")){
                    myDB3.changes("Tuesday","","14:35");
                    myDB3.changes("Tuesday","","15:05");
                    myDB3.changes("Tuesday","","15:35");
                    myDB3.changes("Thursday","","14:35");
                    myDB3.changes("Thursday","","15:05");
                    myDB3.changes("Thursday","","15:35");

                }else if (courseId.equals("31460")){
                    myDB3.changes("Monday","","18:05");
                    myDB3.changes("Monday","","18:35");
                    myDB3.changes("Monday","","19:05");
                    myDB3.changes("Monday","","19:35");
                    myDB3.changes("Monday","","20:05");
                    myDB3.changes("Monday","","20:35");
                    myDB3.changes("Monday","","21:05");
                    myDB3.changes("Wednesday","","18:05");
                    myDB3.changes("Wednesday","","18:35");
                    myDB3.changes("Wednesday","","19:05");
                    myDB3.changes("Wednesday","","19:35");
                    myDB3.changes("Wednesday","","20:05");
                    myDB3.changes("Wednesday","","20:35");
                    myDB3.changes("Wednesday","","21:05");

                }else if (courseId.equals("31803")){
                    myDB3.changes("Monday","","18:05");
                    myDB3.changes("Monday","","18:35");
                    myDB3.changes("Monday","","19:05");
                    myDB3.changes("Monday","","19:35");
                    myDB3.changes("Monday","","20:05");
                    myDB3.changes("Monday","","20:35");
                    myDB3.changes("Wednesday","","18:05");
                    myDB3.changes("Wednesday","","18:35");
                    myDB3.changes("Wednesday","","19:05");
                    myDB3.changes("Wednesday","","19:35");
                    myDB3.changes("Wednesday","","20:05");
                    myDB3.changes("Wednesday","","20:35");

                }else if (courseId.equals("31836")){
                    myDB3.changes("Monday","","18:05");
                    myDB3.changes("Monday","","18:35");
                    myDB3.changes("Monday","","19:05");
                    myDB3.changes("Monday","","19:35");
                    myDB3.changes("Monday","","20:05");
                    myDB3.changes("Monday","","20:35");
                    myDB3.changes("Wednesday","","18:05");
                    myDB3.changes("Wednesday","","18:35");
                    myDB3.changes("Wednesday","","19:05");
                    myDB3.changes("Wednesday","","19:35");
                    myDB3.changes("Wednesday","","20:05");
                    myDB3.changes("Wednesday","","20:35");

                }

                //dropCourse();
            }
        });//set button drop click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {// set listView is clickable and jump to new activity
                detailed_courseview.setCourse(registedCourse.get(position));
                startActivity(new Intent(RegistActivity.this,detailed_courseview.class));
            }
        });
    }
    private void addCourse(){//add course
        final String courseID = editText.getText().toString();
        tempID = courseID;
        String courseTitle = "";
        String courseTerm = "";
        int t = 0;
        for (int i = 0; i < courses.size(); i++) {
            String temp = courses.get(i).getCourseID();
            if (temp.equals(courseID)) {
                course = courses.get(i);
                courseTitle = course.getCourseTitle();
                currSpot = Integer.parseInt(courses.get(i).getAvailableSpot().toString());

            }
        }
        if(currSpot > 0) {
            String courseType = "";
            for (int i = 0; i < courses.size(); i++) {
                String temp = courses.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = courses.get(i);
                    courseType = course.getCourseType();
                    registFee = course.getCourseFee();
                }
            }
            for (int i = 0; i < courses.size(); i++) {
                String temp = courses.get(i).getCourseID();
                String tempTerm = courses.get(i).getCourseTerm();

                if (temp.equals(courseID) && tempTerm.equals(FirstFragment.termNumber)) {
                    course = courses.get(i);
                    courseTerm = course.getCourseTerm();
                }
            }
            String id1 = LoginInterfaceActivity.uid;
            Registration reg = new Registration(courseID, courseTitle, courseType, id1, courseTerm,registFee);
            mReference.child(id1).child(courseTerm).child(courseID).setValue(reg);
            String spot = Integer.toString(currSpot - 1);
            course.setAvailableSpot(spot);
            cRef = FirebaseDatabase.getInstance().getReference("Courses");
            cRef.child(courseID).setValue(course);
        }
        else{
            Toast.makeText(RegistActivity.this, "No available seat for this course", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registmenu, menu);
        return true;
    }
    private void dropCourse(){//drop class
        String id2 = LoginInterfaceActivity.uid;
        String courseID = editText.getText().toString();
        mRef.child(courseID).removeValue();
        for (int i = 0; i < courses.size(); i++) {
            String temp = courses.get(i).getCourseID();
            if (temp.equals(courseID)) {
                course = courses.get(i);
                currSpot = Integer.parseInt(courses.get(i).getAvailableSpot().toString());

            }
        }
        String spot = Integer.toString(currSpot + 1);
        course.setAvailableSpot(spot);
        cRef = FirebaseDatabase.getInstance().getReference("Courses");
        cRef.child(courseID).setValue(course);
    }

    public int geti(){
        return i;
    }
}
