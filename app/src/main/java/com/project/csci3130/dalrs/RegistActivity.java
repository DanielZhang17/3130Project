package com.project.csci3130.dalrs;

import android.app.Fragment;
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
public class RegistActivity extends AppCompatActivity {
    DatabaseReference ref;
    DatabaseReference mReference;
    DatabaseReference nReference;
    DatabaseReference mRef;
    DatabaseReference cRef;
    ListView listView;
    Button buttonAdd;
    Button buttonDrop;
    EditText editText;
    Course course;
    public static Course selected;
    Registration registration;
    Course courseLec;
    User user;
    List<String> users;
    private FirebaseListAdapter<Registration> adapter;
    ArrayList<Course> courses = new ArrayList<Course>();
    private static final String TAG = "TasksSample";
    ArrayList<Course> coursesAll = new ArrayList<>();
    ArrayList<Course> coursesLec = new ArrayList<>();
    ArrayList<Course> registedCourse = new ArrayList<>();
    ArrayList<Registration> registed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        buttonAdd = (Button) findViewById(R.id.button1);
        buttonDrop = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {// set listView is clickable and jump to new activity
                selected = courses.get((int) (listView.getSelectedItem()));
                startActivity(new Intent(RegistActivity.this,detailed_courseview.class));
            }
        });
        ref = FirebaseDatabase.getInstance().getReference("Courses");
        mReference = FirebaseDatabase.getInstance().getReference("Registrations");
        mRef = FirebaseDatabase.getInstance().getReference("Registrations").child(LoginInterfaceActivity.uid).child(FirstFragment.termNumber);
        nReference = FirebaseDatabase.getInstance().getReference("Users");
        cRef = FirebaseDatabase.getInstance().getReference("Courses");
        users = new ArrayList<String>();
        cRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    courseLec = ds.getValue(Course.class);
                    coursesAll.add(courseLec);
                    for(int i = 0; i < coursesAll.size(); i++){
                        String lec = coursesAll.get(i).getCourseType();
                        if(lec.equals("Lec")){
                            coursesLec.add(coursesAll.get(i));
                        }
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
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    registration = ds.getValue(Registration.class);
                    registed.add(registration);

                }
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
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    course = ds.getValue(Course.class);
                    courses.add(course);
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

                String checkTerm = FirstFragment.termNumber;
                String courseId = editText.getText().toString();

                boolean c = false;
                for(int m = 0; m < courses.size(); m++) {//check course term
                    String tempTerm = courses.get(m).getCourseTerm();
                    String temp = courses.get(m).getCourseID();
                    if (temp == null) {
                        temp = courses.get(m).getLabID();
                    }
                    if (temp == null) {
                        temp = courses.get(m).getTutID();
                    }
                    if (tempTerm.equals(checkTerm) && temp.equals(courseId)) {
                        c = true;
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
                dropCourse();
            }
        });//set button drop click
    }
    private void addCourse(){//add course

        String courseID = editText.getText().toString();
        String courseTitle = "";
        String courseTerm = "";
        for (int i = 0; i < courses.size(); i++) {
            String temp = courses.get(i).getCourseID();
            if (temp == null) {
                temp = courses.get(i).getLabID();
            }
            if (temp == null) {
                temp = courses.get(i).getTutID();
            }
            if (temp.equals(courseID)) {
                course = courses.get(i);
                courseTitle = course.getCourseTitle();
            }
        }
        String courseType = "";
        for (int i = 0; i < courses.size(); i++) {
            String temp = courses.get(i).getCourseID();
            if (temp == null) {
                temp = courses.get(i).getLabID();
            }
            if (temp == null) {
                temp = courses.get(i).getTutID();
            }
            if (temp.equals(courseID)) {
                course = courses.get(i);
                courseType = course.getCourseType();
            }
        }
        for(int i =0; i< courses.size();i++){
            String temp = courses.get(i).getCourseID();
            String tempTerm = courses.get(i).getCourseTerm();

            if (temp == null) {
                temp = courses.get(i).getLabID();
            }
            if (temp == null) {
                temp = courses.get(i).getTutID();
            }
            if (temp.equals(courseID) && tempTerm.equals(FirstFragment.termNumber)) {
                course = courses.get(i);
                courseTerm = course.getCourseTerm();
            }
        }
        String id1 = LoginInterfaceActivity.uid;
        Registration reg = new Registration(courseID, courseTitle, courseType, id1, courseTerm);
        mReference.child(id1).child(courseTerm).child(courseID).setValue(reg);
    }
    private void dropCourse(){//drop class
        String id2 = LoginInterfaceActivity.uid;
        String courseID = editText.getText().toString();
        mRef.child(courseID).removeValue();
    }
}
