package com.project.csci3130.dalrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.firebase.database.*;

import com.google.firebase.auth.*;

import com.google.firebase.auth.FirebaseUser;

public class MyStatus extends AppCompatActivity {
    private DatabaseReference Ref;
    private static FirebaseUser user = LoginInterfaceActivity.getUser();
    private static FirebaseAuth auth = LoginInterfaceActivity.getAuth();
    private TextView UserName;
    private TextView UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_status);
        Ref = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference wtf = Ref.child(user.getUid()).child("UserID");
        DatabaseReference wtf2 = Ref.child(user.getUid()).child("UserName");

        UserID = findViewById(R.id.SName);
        UserName = findViewById(R.id.SID);

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
    }
}
