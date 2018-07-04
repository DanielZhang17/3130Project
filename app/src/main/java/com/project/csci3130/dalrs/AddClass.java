package com.project.csci3130.dalrs;
/*


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


        if(!registData.get(user.getUid()).contains(id)) {
            //add id into registration
            registReference.child(user.getUid()).child(id).child("RegistCourseID").setValue(id);
            //add title and name
            registReference.child(user.getUid()).child(id).child("RegistCourseName")
                    .setValue(courseReference.child(id).child("CourseTitle").toString()
                            +" "+courseReference.child(id).child("CourseName").toString());
            //add lab
            if(courseReference.child(id).child("LabID")!=null){
                registReference.child(user.getUid()).child(id).child("RegistCourseLabID")
                        .setValue(courseReference.child(id).child("LabID").toString());
            }
            //add tut
            if(courseReference.child(id).child("TutID")!=null){
                registReference.child(user.getUid()).child(id).child("RegistCourseTutID")
                        .setValue(courseReference.child(id).child("TutID").toString());
            }

        }


    }
    public void dropClass(final String id){
        //make sure user want to drop
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
*/