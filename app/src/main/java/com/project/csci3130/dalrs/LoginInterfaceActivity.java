package com.project.csci3130.dalrs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

/**
 * The type Login interface activity.
 */
public class LoginInterfaceActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
        private DatabaseReference Ref;
        private static FirebaseUser user;
        DatabaseHelper myDB=new DatabaseHelper(this);
        DatabaseHelper2 myDB2=new DatabaseHelper2(this);
        DatabaseHelper3 myDB3=new DatabaseHelper3(this);
        private static int i=0;
    static FirebaseAuth auth;
    public static String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_interface);
        setTitle("Home");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        final TextView Email = headerview.findViewById(R.id.UserEmail);
        final TextView name = headerview.findViewById(R.id.UserName);

        navigationView.setCheckedItem(R.id.Home);
        Ref = FirebaseDatabase.getInstance().getReference("Users");
        if (user!=null) {
            DatabaseReference wtf = Ref.child(user.getUid()).child("Email");
            DatabaseReference wtf2 = Ref.child(user.getUid()).child("UserName");
            uid = user.getUid();

            //This EventListener will find the email of user
            wtf.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value1 = dataSnapshot.getValue(String.class);
                    Email.setText("  " + value1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //This EventListener will find the Name of User
            wtf2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value2 = dataSnapshot.getValue(String.class);
                    name.setText("  " + value2);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        
         if (i==0) {
            myDB.addData(null, "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
            myDB.addData("8:05", null, null, null, null, null);
            myDB.addData("8:35", null, null, null, null, null);
            myDB.addData("9:05", null, null, null, null, null);
            myDB.addData("9:35", null, null, null, null, null);
            myDB.addData("10:05", null, null, null, null, null);
            myDB.addData("10:35", null, null, null, null, null);
            myDB.addData("11:05", null, null, null, null, null);
            myDB.addData("11:35", null, null, null, null, null);
            myDB.addData("12:05", null, null, null, null, null);
            myDB.addData("12:35", null, null, null, null, null);
            myDB.addData("13:05", null, null, null, null, null);
            myDB.addData("13:35", null, null, null, null, null);
            myDB.addData("14:05", null, null, null, null, null);
            myDB.addData("14:35", null, null, null, null, null);
            myDB.addData("15:05", null, null, null, null, null);
            myDB.addData("15:35", null, null, null, null, null);
            myDB.addData("16:05", null, null, null, null, null);
            myDB.addData("16:35", null, null, null, null, null);
            myDB.addData("17:05", null, null, null, null, null);
            myDB.addData("17:35", null, null, null, null, null);
            myDB.addData("18:05", null, null, null, null, null);
            myDB.addData("18:35", null, null, null, null, null);
            myDB.addData("19:05", null, null, null, null, null);
            myDB.addData("19:35", null, null, null, null, null);
            myDB.addData("20:05", null, null, null, null, null);
            myDB.addData("20:35", null, null, null, null, null);
            myDB.addData("21:05", null, null, null, null, null);
            myDB.addData("21:35", null, null, null, null, null);
            myDB2.addData(null, "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
            myDB2.addData("8:05", null, null, null, null, null);
            myDB2.addData("8:35", null, null, null, null, null);
            myDB2.addData("9:05", null, null, null, null, null);
            myDB2.addData("9:35", null, null, null, null, null);
            myDB2.addData("10:05", null, null, null, null, null);
            myDB2.addData("10:35", null, null, null, null, null);
            myDB2.addData("11:05", null, null, null, null, null);
            myDB2.addData("11:35", null, null, null, null, null);
            myDB2.addData("12:05", null, null, null, null, null);
            myDB2.addData("12:35", null, null, null, null, null);
            myDB2.addData("13:05", null, null, null, null, null);
            myDB2.addData("13:35", null, null, null, null, null);
            myDB2.addData("14:05", null, null, null, null, null);
            myDB2.addData("14:35", null, null, null, null, null);
            myDB2.addData("15:05", null, null, null, null, null);
            myDB2.addData("15:35", null, null, null, null, null);
            myDB2.addData("16:05", null, null, null, null, null);
            myDB2.addData("16:35", null, null, null, null, null);
            myDB2.addData("17:05", null, null, null, null, null);
            myDB2.addData("17:35", null, null, null, null, null);
            myDB2.addData("18:05", null, null, null, null, null);
            myDB2.addData("18:35", null, null, null, null, null);
            myDB2.addData("19:05", null, null, null, null, null);
            myDB2.addData("19:35", null, null, null, null, null);
            myDB2.addData("20:05", null, null, null, null, null);
            myDB2.addData("20:35", null, null, null, null, null);
            myDB2.addData("21:05", null, null, null, null, null);
            myDB2.addData("21:35", null, null, null, null, null);
            myDB3.addData(null, "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
            myDB3.addData("8:05", null, null, null, null, null);
            myDB3.addData("8:35", null, null, null, null, null);
            myDB3.addData("9:05", null, null, null, null, null);
            myDB3.addData("9:35", null, null, null, null, null);
            myDB3.addData("10:05", null, null, null, null, null);
            myDB3.addData("10:35", null, null, null, null, null);
            myDB3.addData("11:05", null, null, null, null, null);
            myDB3.addData("11:35", null, null, null, null, null);
            myDB3.addData("12:05", null, null, null, null, null);
            myDB3.addData("12:35", null, null, null, null, null);
            myDB3.addData("13:05", null, null, null, null, null);
            myDB3.addData("13:35", null, null, null, null, null);
            myDB3.addData("14:05", null, null, null, null, null);
            myDB3.addData("14:35", null, null, null, null, null);
            myDB3.addData("15:05", null, null, null, null, null);
            myDB3.addData("15:35", null, null, null, null, null);
            myDB3.addData("16:05", null, null, null, null, null);
            myDB3.addData("16:35", null, null, null, null, null);
            myDB3.addData("17:05", null, null, null, null, null);
            myDB3.addData("17:35", null, null, null, null, null);
            myDB3.addData("18:05", null, null, null, null, null);
            myDB3.addData("18:35", null, null, null, null, null);
            myDB3.addData("19:05", null, null, null, null, null);
            myDB3.addData("19:35", null, null, null, null, null);
            myDB3.addData("20:05", null, null, null, null, null);
            myDB3.addData("20:35", null, null, null, null, null);
            myDB3.addData("21:05", null, null, null, null, null);
            myDB3.addData("21:35", null, null, null, null, null);
            i = 1;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //This is the method is used to go back to last page of App or Log out from App
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            if(getTitle().equals("Home")){
                //Show a dialog to comfirm if the user wants to exit the application
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finishAndRemoveTask();
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
            else {
                //return to home if the back is pressed at other fragments
                finish();
                startActivity(getIntent());
            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    //The constructor of navigation drawer
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id==R.id.Home){
            finish();
            startActivity(getIntent());
        }
        if (id == R.id.nav_first_layout) {
            // Handle the camera action
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new FirstFragment())
                    .commit();

        } else if (id == R.id.nav_second_layout) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new SecondFragment())
                    .commit();

        } else if (id == R.id.nav_third_layout) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new ThirdFragment())
                    .commit();


        }else if (id == R.id.nav_fourth_layout) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new FourthFragment())
                    .commit();


        }else if (id == R.id.nav_fifth_layout) {
            auth.signOut();
            startActivity(new Intent(LoginInterfaceActivity.this,LoginActivity.class));
        }else if (id==R.id.nav_sixth_layout){
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new SixthFragment())
                    .commit();
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Set auth.
     *
     * @param a the a
     */
    public static void setAuth(FirebaseAuth a){
        auth = a;
    }

    /**
     * Set user.
     *
     * @param u the u
     */
    public static void setUser(FirebaseUser u){
        user = u;
    }

    /**
     * Get auth firebase auth.
     *
     * @return the firebase auth
     */
    public static FirebaseAuth getAuth(){
        return auth;
    }

    /**
     * Get user firebase user.
     *
     * @return the firebase user
     */
    public static FirebaseUser getUser(){
        return user;
    }
}
