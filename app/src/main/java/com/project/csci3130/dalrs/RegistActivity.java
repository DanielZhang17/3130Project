package com.project.csci3130.dalrs;

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
    ListView listView;
    Button buttonAdd;
    Button buttonDrop;
    EditText editText;
    Course course;
    Registration registration;
    User user;
    List<String> users;
    private FirebaseListAdapter<Registration> adapter;
    ArrayList<Course> courses = new ArrayList<Course>();
    private static final String TAG = "TasksSample";
    public static Course selected;
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected =  courses.get((int)(listView.getSelectedItemId()-1));
                startActivity(new Intent(RegistActivity.this,detailed_courseview.class));
            }
        });
        ref = FirebaseDatabase.getInstance().getReference("Courses");
        mReference = FirebaseDatabase.getInstance().getReference("Registrations");
        mRef = FirebaseDatabase.getInstance().getReference("Registrations").child(LoginInterfaceActivity.getAuth().getCurrentUser().getUid());
        nReference = FirebaseDatabase.getInstance().getReference("Users");
        users = new ArrayList<String>();
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    registration = ds.getValue(Registration.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.addValueEventListener(new ValueEventListener() {
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
        nReference.addValueEventListener(new ValueEventListener() {
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
        adapter = new FirebaseListAdapter<Registration>(this, Registration.class, android.R.layout.simple_list_item_1, mRef) {
            @Override
            protected void populateView(View v, Registration model, int position) {
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText("CRN: "+model.getRegistCourseID()+ "\nCourse Title: "+model.getRegistTitle() + "   Course Type: "+model.getRegistType());
                Log.d(TAG,"courseTitle = " + textView);


            }

        };

        listView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourse();
            }
        });
        buttonDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropCourse();
            }
        });
    }
    private void addCourse(){
        String courseID = editText.getText().toString();
        String courseTitle = "";
        for(int i=0; i<courses.size();i++){
            String temp = courses.get(i).getCourseID();
            if(temp == null){
                temp = courses.get(i).getLabID();
            }
            if(temp == null){
                temp = courses.get(i).getTutID();
            }
            if(temp.equals(courseID)){
                course = courses.get(i);
                courseTitle = course.getCourseTitle();
            }

        }
        String courseType = "";
        for(int i=0; i<courses.size();i++){
            String temp = courses.get(i).getCourseID();
            if(temp == null){
                temp = courses.get(i).getLabID();
            }
            if(temp == null){
                temp = courses.get(i).getTutID();
            }
            if(temp.equals(courseID)){
                course = courses.get(i);
                courseType = course.getCourseType();
            }

        }
        String id1 = LoginInterfaceActivity.uid;
        Registration reg = new Registration(courseID,courseTitle,courseType,id1);
        mReference.child(id1).child(courseID).setValue(reg);
    }
    private void dropCourse(){
        String id2 = LoginInterfaceActivity.uid;
        String courseID = editText.getText().toString();
        mReference.child(id2).child(courseID).removeValue();
    }

}
