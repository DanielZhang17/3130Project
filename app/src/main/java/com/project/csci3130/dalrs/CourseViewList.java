package com.project.csci3130.dalrs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CourseViewList extends AppCompatActivity {
    private TextView test1;
    private TextView test2;
    private DatabaseReference wReference;
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter adapter;
    private List<String> courseCS = new ArrayList<>();
    private List<String> courseSTAT = new ArrayList<>();
    private String[] groupList = new String[]{"Computer Science","Statistic"};
    private static DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Registrations")
            .child(LoginInterfaceActivity.uid).child(SecondFragment.termNumber);
    private DatabaseReference rRef = FirebaseDatabase.getInstance().getReference("Registrations");
    public DatabaseReference mReference= FirebaseDatabase.getInstance().getReference();
    public DatabaseReference courseReference = mReference.child("Courses");
    public DatabaseReference nReference = FirebaseDatabase.getInstance().getReference("Users");
    public final String TAG = "TasksSample";
    private Map<String, List<String>> courseList = new HashMap<>();
    String term = SecondFragment.termNumber;
    DatabaseReference ref;
    Course course;
    String courseID;
    ArrayList<Course> courseData = new ArrayList<Course>();
    User user;
    Registration registration;
    public static String courseTitle;

    ExpandableListView listView;
    ArrayList<Course> courses1 = new ArrayList<Course>();
    ArrayList<Course> coursesLec = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_view_main);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableList);
        ref = FirebaseDatabase.getInstance().getReference("Courses");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    course = ds.getValue(Course.class);
                    if(course.getCourseTerm().equals(term)) {
                        courses1.add(course);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        initData();
        listView();

        adapter = new ExpandableListViewAdapter();
        expandableListView.setAdapter(adapter);

    }
    private void initData() {
        DatabaseReference haha = mReference.child("Courses");

        haha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectCourseTitles((Map<String,Object>) dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void collectCourseTitles(Map<String,Object> courses){//let cs courses in cs list,let stat courses in stat list
        for(Map.Entry<String,Object> entry: courses.entrySet()){

            for(int i=0; i< courses1.size(); i++){
                if(courses1.get(i).getCourseType().contains("ec") && !coursesLec.contains(courses1.get(i))){
                    coursesLec.add(courses1.get(i));
                }
            }
            for(int j=0; j< coursesLec.size(); j++){
                if(coursesLec.get(j).getCourseDep().contains("Computer") && !courseCS.contains(coursesLec.get(j).getCourseTitle())){
                    courseCS.add(coursesLec.get(j).getCourseTitle());
                }
                else if(coursesLec.get(j).getCourseDep().contains("Stat") && !courseSTAT.contains(coursesLec.get(j).getCourseTitle())){
                    courseSTAT.add(coursesLec.get(j).getCourseTitle());
                }
            }
        }
    }
    private void listView(){//put into hash map

        courseList.put(groupList[0],courseCS);
        courseList.put(groupList[1],courseSTAT);

    }
    public class ExpandableListViewAdapter extends BaseExpandableListAdapter{

        @Override
        //get one item from one groupList.
        public Object getChild(int parentPosition, int childPosition){
            return courseList.get(groupList[parentPosition]).get(childPosition);
        }

        @Override
        //Get the number of groups
        public int getGroupCount(){
            return courseList.size();
        }

        @Override
        //Get the number of child elements in the specified group
        public int getChildrenCount(int parentPosition){
            return courseList.get(groupList[parentPosition]).size();
        }

        @Override
        //Get the data in the specified group
        public Object getGroup(int parentPosition){
            return courseList.get(groupList[parentPosition]);
        }

        @Override
        //Get the id of a child of a group
        public long getChildId(int parentPosition, int childPosition){
            return childPosition;
        }

        @Override
        //Get the id of the specified group
        public long getGroupId(int parentPosition){
            return parentPosition;
        }

        @Override
        public boolean hasStableIds(){
            return false;
        }

        @Override
        public View getGroupView(int parentPosition, boolean isExpand,
                                 View convertView, ViewGroup viewGroup){

            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.group_item,null);
            }


            TextView textView = (TextView) convertView.findViewById(R.id.groupView);

            textView.setText(groupList[parentPosition]);

            return convertView;
        }

        @Override
        public View getChildView(final int parentPosition, final int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup viewGroup){

            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_item,null);
            }

            convertView.setTag(R.layout.group_item, parentPosition);

            convertView.setTag(R.layout.child_item, childPosition);

            TextView textView = (TextView) convertView.findViewById(R.id.childView);

            textView.setText(courseList.get(groupList[parentPosition]).get(childPosition));


            convertView.findViewById(R.id.AddBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    courseTitle = courseList.get(groupList[parentPosition]).get(childPosition);

                    addCourse();

                }
            });

            convertView.findViewById(R.id.DropBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    courseTitle = courseList.get(groupList[parentPosition]).get(childPosition);

                    dropClass();

                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int parentPosition, int childPosition){
            return false;  //not in interaction 1
        }
    }
    public void ReadData() {
        //get course information
        courseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(course.getCourseTerm().equals(term)) {
                        courseData.add(course);
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
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
        //get registation information
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                registration = dataSnapshot.getValue(Registration.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void addCourse(){//add course


        boolean c = false;
        for(int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            String tempType = coursesLec.get(m).getCourseType();
            if (tempTerm.equals(term) && temp.equals(courseTitle) && tempType.contains("ec")) {
                c = true;
                courseID = coursesLec.get(m).getCourseID();
            }
        }
        if(c == true) {

            String courseTerm = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();
                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseTitle = course.getCourseTitle();
                }
            }
            String courseType = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseType = course.getCourseType();
                }
            }
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();
                String tempTerm = coursesLec.get(i).getCourseTerm();
                if (temp.equals(courseID) && tempTerm.equals(SecondFragment.termNumber)) {
                    course = coursesLec.get(i);
                    courseTerm = course.getCourseTerm();
                }
            }
            String id1 = LoginInterfaceActivity.uid;
            Registration reg = new Registration(courseID, courseTitle, courseType, id1, courseTerm);
            rRef.child(id1).child(courseTerm).child(courseID).setValue(reg);
        }
    }
    public void dropClass(){
        for(int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            String tempType = coursesLec.get(m).getCourseType();
            if (tempTerm.equals(term) && temp.equals(courseTitle) && tempType.contains("ec")) {
                courseID = coursesLec.get(m).getCourseID();
                Toast.makeText(CourseViewList.this, "Add class successfully", Toast.LENGTH_LONG).show();
            }
        }
        mRef.child(courseID).removeValue();
        Toast.makeText(CourseViewList.this, "Course dropped", Toast.LENGTH_LONG).show();
        //The following code may use for iteration 3
        /*DialogUtil dialogUtil = new DialogUtil();
        dialogUtil.show( "Do you want to drop this course?", new DialogButtonListener() {
            @Override
            public void sure() {
                mRef.child(courseID).removeValue();
            }

            @Override
            public void cancel() {

            }
        });*/
    }
}
