package com.project.csci3130.dalrs;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.project.csci3130.dalrs.LoginInterfaceActivity.auth;

public class AddClass {
    private DatabaseReference mReference= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference courseReference = mReference.child("Courses");
    private DatabaseReference registReference = mReference.child("Registrations");
    FirebaseUser user = auth.getCurrentUser();
    private Map<String, List<String>> courseData = new HashMap<>();
    private Map<String, List<String>> registData = new HashMap<>();



    public void ReadData() {


        //get course information
        courseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseData = (Map<String, List<String>>) dataSnapshot.getValue();

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
    public void addClass(String id){
        String courseTitle = courseReference.child(id).child("CourseTitle").toString();
        String courseName = courseReference.child(id).child("CourseName").toString();

        if(!registData.get(user.getDisplayName()).contains(id)) {


            if(courseReference.child(id).child("LabID")!=null){
                registReference.child(user.getDisplayName()).child(id).setValue(courseTitle+" "+courseName+" "
                        +courseReference.child(id).child("LabID").toString());

            }
            else if(courseReference.child(id).child("LutID")!=null){
                registReference.child(user.getDisplayName()).child(id).setValue(courseTitle+" "+courseName+" "
                        +courseReference.child(id).child("TutID").toString());
            }
            else{
                registReference.child(user.getDisplayName()).child(id).setValue(courseTitle+" "+courseName);
            }

        }


    }
    public void dropClass(final String id){
        //
        DialogUtil dialogUtil = new DialogUtil();
        dialogUtil.show( "Do you want to drop this course?", new DialogButtonListener() {
            @Override
            public void sure() {
                registReference.child(user.getDisplayName()).child(id).removeValue();
            }

            @Override
            public void cancel() {

            }
        });

    }

}
