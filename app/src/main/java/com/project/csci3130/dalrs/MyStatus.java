package com.project.csci3130.dalrs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.google.firebase.database.*;
import android.widget.AdapterView;
import com.google.firebase.auth.*;

import com.google.firebase.database.FirebaseDatabase;

public class MyStatus extends AppCompatActivity {
    private DatabaseReference Ref;
    private DatabaseReference Registrations;
    private static FirebaseUser user = LoginInterfaceActivity.getUser();
    private static FirebaseAuth auth = LoginInterfaceActivity.getAuth();
    private TextView UserName;
    private TextView UserID;
    private TextView TotalCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_status);
        Ref = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference wtf = Ref.child(user.getUid()).child("UserID");
        DatabaseReference wtf2 = Ref.child(user.getUid()).child("UserName");
        Registrations = FirebaseDatabase.getInstance().getReference("Registrations").child(user.getUid());

        UserID = findViewById(R.id.SName);
        UserName = findViewById(R.id.SID);
        TotalCredit = findViewById(R.id.totalcreait);

        wtf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.getValue(String.class);
                UserID.setText("Student Name:"+value1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        wtf2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.getValue(String.class);
                UserName.setText("Student Name:"+value1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Registrations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long sum;
                sum = (dataSnapshot.getChildrenCount())*3;
                TotalCredit.setText("Total Credit: "+sum);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
