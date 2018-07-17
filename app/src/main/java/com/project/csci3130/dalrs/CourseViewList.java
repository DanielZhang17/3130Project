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
import android.widget.Toolbar;

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


/**
 * The type Course view list.
 */
public class CourseViewList extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter adapter;
    private List<String> courseCS = new ArrayList<>();
    private List<String> courseSTAT = new ArrayList<>();
    private List<String> courseEcon = new ArrayList<>();

    private String[] groupList = new String[]{"Computer Science","Statistic","Economic"};
    private static DatabaseReference mRef;
    //= FirebaseDatabase.getInstance().getReference("Registrations")
            //.child(LoginInterfaceActivity.uid).child(SecondFragment.termNumber);
    private DatabaseReference rRef = FirebaseDatabase.getInstance().getReference("Registrations");
    /**
     * The M reference.
     */
    public DatabaseReference mReference= FirebaseDatabase.getInstance().getReference();
    /**
     * The Course reference.
     */
    public DatabaseReference courseReference = mReference.child("Courses");
    /**
     * The N reference.
     */
    public DatabaseReference nReference = FirebaseDatabase.getInstance().getReference("Users");
    /**
     * The Tag.
     */
    public final String TAG = "TasksSample";
    private Map<String, List<String>> courseList = new HashMap<>();
    /**
     * The Term.
     */
    String term = SecondFragment.termNumber;
    /**
     * The Ref.
     */
    DatabaseReference ref;
    /**
     * The Course.
     */
    Course course;
    /**
     * The Course id.
     */
    String courseID;
    /**
     * The Course data.
     */
    ArrayList<Course> courseData = new ArrayList<Course>();
    /**
     * The User.
     */
    User user;
    /**
     * The Registration.
     */
    Registration registration;
    /**
     * The constant courseTitle.
     */
    public static String courseTitle;

    /**
     * The List view.
     */
    ExpandableListView listView;
    /**
     * The Courses 1.
     */
    ArrayList<Course> courses1 = new ArrayList<Course>();
    /**
     * The Courses lec.
     */
    ArrayList<Course> coursesLec = new ArrayList<>();

    int currSopt;
    String registFee;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_view_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
                else if(coursesLec.get(j).getCourseDep().contains("Econ") && !courseEcon.contains(coursesLec.get(j).getCourseTitle())){
                    courseEcon.add(coursesLec.get(j).getCourseTitle());
                }
            }
        }
    }
    private void listView(){//put into hash map

        courseList.put(groupList[0],courseCS);
        courseList.put(groupList[1],courseSTAT);
        courseList.put(groupList[2],courseEcon);


    }

    /**
     * The type Expandable list view adapter.
     */
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
            convertView.findViewById(R.id.detail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    courseTitle = courseList.get(groupList[parentPosition]).get(childPosition);

                    chechDetail();
                    Intent intent = new Intent(CourseViewList.this,CourseDetail.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int parentPosition, int childPosition){
            return false;  //not in interaction 1
        }
    }

    /**
     * Read data.
     */
    public void ReadData() {
        //get course information
        courseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (course.getCourseTerm().equals(term)) {
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
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    user = ds.getValue(User.class);
                    Log.i(TAG, "userID = " + user.UID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //get registation information
        if (FirebaseDatabase.getInstance().getReference("Registrations")
                .child(LoginInterfaceActivity.uid).child(SecondFragment.termNumber) != null){
            mRef = FirebaseDatabase.getInstance().getReference("Registrations")
                .child(LoginInterfaceActivity.uid).child(SecondFragment.termNumber);
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
    }

    /**
     * Add course.
     */
    public void addCourse(){//add course

        boolean c = false;
        for (int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            String tempType = coursesLec.get(m).getCourseType();
            if (tempTerm.equals(term) && temp.equals(courseTitle) && tempType.contains("ec")) {
                c = true;
                courseID = coursesLec.get(m).getCourseID();
                currSopt = Integer.parseInt(coursesLec.get(m).getAvailableSpot());
            }
        }
        if(currSopt > 0) {
            if (c == true) {

                String courseTerm = "";
                for (int i = 0; i < coursesLec.size(); i++) {
                    String temp = coursesLec.get(i).getCourseID();
                    if (temp.equals(courseID)) {
                        course = coursesLec.get(i);
                        courseTitle = course.getCourseTitle();
                        registFee = course.getCourseFee();
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
                Registration reg = new Registration(courseID, courseTitle, courseType, id1, courseTerm, registFee);
                rRef.child(id1).child(courseTerm).child(courseID).setValue(reg);
                courseReference.child(courseID).child("AvailableSpot").setValue(Integer.toString(currSopt - 1));
                Toast.makeText(CourseViewList.this, "Add class successfully", Toast.LENGTH_LONG).show();

            }
        }
        else{
            Toast.makeText(CourseViewList.this, "No available seat for this course", Toast.LENGTH_LONG).show();

        }
    }

    /**
     * Drop class.
     */
    public void dropClass(){
        for(int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            String tempType = coursesLec.get(m).getCourseType();
            if (tempTerm.equals(term) && temp.equals(courseTitle) && tempType.contains("ec")) {
                courseID = coursesLec.get(m).getCourseID();
            }
        }
        mRef = FirebaseDatabase.getInstance().getReference("Registrations")
                .child(LoginInterfaceActivity.uid).child(SecondFragment.termNumber);
        mRef.child(courseID).removeValue();
        courseReference.child(courseID).child("AvailableSpot").setValue(Integer.toString(currSopt+1));
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
    public void chechDetail(){
        boolean c = false;
        for (int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            String tempType = coursesLec.get(m).getCourseType();
            if (tempTerm.equals(term) && temp.equals(courseTitle) && tempType.contains("ec")) {
                c = true;
                courseID = coursesLec.get(m).getCourseID();
            }
        }
        if (c == true) {
            String courseTerm = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();
                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseTitle = course.getCourseTitle();
                    registFee = course.getCourseFee();
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
            String courseName = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseName = course.getCourseName();
                }
            }
            String courseDayTime = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseDayTime = course.getCourseDayTime();
                }
            }
            String courseDep = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseDep = course.getCourseDep();
                }
            }
            String courseInfo = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseInfo = course.getCourseInformation();
                }
            }
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();
                String tempTerm = coursesLec.get(i).getCourseTerm();

                if (temp.equals(courseID) && tempTerm.equals(FirstFragment.termNumber)) {
                    course = coursesLec.get(i);
                    courseTerm = course.getCourseTerm();
                }
            }
            String courseTime = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseTime = course.getCourseTime();
                }
            }
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseType = course.getCourseType();
                }
            }
            String courseLocation = "";
            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();

                if (temp.equals(courseID)) {
                    course = coursesLec.get(i);
                    courseLocation = course.getLocation();
                }
            }
            Course course2 = new Course(courseID,courseTitle,courseDayTime,courseDep,courseInfo,courseName,
                    courseTerm,courseTime,courseType,null,null,null,null,
                    null,courseLocation,null,null,null,null,
                    null,null,null);
            CourseDetail.setCourse(course2);

        }

    }
}
