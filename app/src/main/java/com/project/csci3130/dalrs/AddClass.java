package com.project.csci3130.dalrs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.project.csci3130.dalrs.LoginInterfaceActivity.auth;

public class AddClass extends AppCompatActivity {
    Button addBtn;
    Button dropBtn;
    Course course;
    ArrayList<Course> courses = new ArrayList<Course>();

    private DatabaseReference Reference= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference courseReference = Reference.child("Courses");
    private DatabaseReference registReference = Reference.child("Registrations");
    FirebaseUser user = auth.getCurrentUser();
    private Map<String, List<String>> registData = new HashMap<>();
    @SuppressLint("WrongViewCast")
    EditText editText1 = findViewById(R.id.childView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_item);
        ReadData();
        addBtn = (Button) findViewById(R.id.AddBtn);
        dropBtn = (Button) findViewById(R.id.DropBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass();

            }
        });
        dropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dropClass();
            }
        });
    }
    public void ReadData() {

        //get course information
        courseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                course = dataSnapshot.getValue(Course.class);
                courses.add(course);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //get registation information
        registReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                registData = (Map<String, List<String>>) dataSnapshot.getValue();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void addClass(){

        @SuppressLint("WrongViewCast")
        EditText editText1 = findViewById(R.id.childView);
        String title = editText1.getText().toString();
        String courseID = "";
        for(int i=0; i<courses.size();i++){
            String temp = courses.get(i).getCourseTitle();
            if(temp == null){
                temp = courses.get(i).getLabID();
            }
            if(temp == null){
                temp = courses.get(i).getTutID();
            }
            if(temp.equals(title)){
                course = courses.get(i);
                courseID = course.getCourseID();
            }

        }
        String courseType = "";
        for(int i=0; i<courses.size();i++){
            String temp = courses.get(i).getCourseTitle();
            if(temp == null){
                temp = courses.get(i).getLabID();
            }
            if(temp == null){
                temp = courses.get(i).getTutID();
            }
            if(temp.equals(title)){
                course = courses.get(i);
                courseType = course.getCourseType();
            }

        }
        String term = FirstFragment.termNumber;
        String id1 = LoginInterfaceActivity.uid;
        Registration reg = new Registration(courseID,title,courseType,id1,term);
        Reference.child(id1).child(courseID).setValue(reg);


    }
    public void dropClass(){
        //make sure user want to drop
        DialogUtil dialogUtil = new DialogUtil();
        dialogUtil.show( "Do you want to drop this course?", new DialogButtonListener() {
            @Override
            public void sure() {
                String id2 = LoginInterfaceActivity.uid;
                String courseID = editText1.getText().toString();
                Reference.child(id2).child(courseID).removeValue();
            }

            @Override
            public void cancel() {

            }
        });

    }
}
