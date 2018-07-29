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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    private String[] groupList = new String[]{"Computer Science","Statistics","Economics"};
    private static DatabaseReference mRef;
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

    int currSpot;
    String registFee;
    DatabaseReference cRef;
    Course courseLec;

    ArrayList<Course> coursesAll = new ArrayList<>();
    public static ArrayList<Course> registedCourse = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<Course>();
    public static String tempID;
    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    ArrayList<Registration> registed = new ArrayList<>();
    DatabaseReference qRef;

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
        qRef = FirebaseDatabase.getInstance().getReference("Registrations").child(LoginInterfaceActivity.uid).child(SecondFragment.termNumber);
        cRef = FirebaseDatabase.getInstance().getReference("Courses");

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
        cRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    courseLec = ds.getValue(Course.class);
                    //if(coursesAll.size()<29) {
                    coursesAll.add(courseLec);
                    //}
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
        qRef.addValueEventListener(new ValueEventListener() {//read data from firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                registed = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    registration = ds.getValue(Registration.class);
                    registed.add(registration);
                }
                registedCourse = new ArrayList<>();
                for(int i=0;i<registed.size();i++){
                    for(int j=0;j<coursesAll.size();j++) {
                        if(coursesAll.get(j).getCourseID()!=null) {
                            if (coursesAll.get(j).getCourseID().equals(registed.get(i).getRegistCourseID())) {
                                registedCourse.add(coursesAll.get(j));

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
                    if(courses1.get(i).getCourseDep().contains("Computer") && !courseCS.contains(courses1.get(i).getCourseTitle())){
                        courseCS.add(courses1.get(i).getCourseTitle());
                    }
                    else if(courses1.get(i).getCourseDep().contains("Stat") && !courseSTAT.contains(courses1.get(i).getCourseTitle())){
                        courseSTAT.add(courses1.get(i).getCourseTitle());
                    }
                    else if(courses1.get(i).getCourseDep().contains("Econ") && !courseEcon.contains(courses1.get(i).getCourseTitle())){
                        courseEcon.add(courses1.get(i).getCourseTitle());
                    }
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

                    try {
                        checkConflict();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
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

                    checkDetail();

                    Intent intent = new Intent(CourseViewList.this,CourseDetail.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int parentPosition, int childPosition){
            return false;
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
        boolean flag = false;
        for (int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            String tempType = coursesLec.get(m).getCourseType();
            if (tempTerm.equals(term) && temp.equals(courseTitle)) {
                c = true;
                courseID = coursesLec.get(m).getCourseID();
                currSpot = Integer.parseInt(coursesLec.get(m).getAvailableSpot());
            }
        }
        for(int i = 0; i < registed.size(); i++){
            if(registed.get(i).getRegistCourseID().equals(courseID)){
                flag = true;
                //Toast.makeText(CourseViewList.this, "You already registered this course.", Toast.LENGTH_LONG).show();

            }
        }
        if(currSpot > 0 && flag == false) {
            if (c == true) {

                String courseTerm = "";
                String courseType = "";
                for (int i = 0; i < coursesLec.size(); i++) {
                    String temp = coursesLec.get(i).getCourseID();
                    if (temp.equals(courseID)) {
                        course = coursesLec.get(i);
                        courseTitle = course.getCourseTitle();
                        registFee = course.getCourseFee();
                        courseType = course.getCourseType();
                    }
                    String tempTerm = coursesLec.get(i).getCourseTerm();
                    if (temp.equals(courseID) && tempTerm.equals(SecondFragment.termNumber)) {
                        course = coursesLec.get(i);
                        courseTerm = course.getCourseTerm();
                    }
                }
                String id1 = LoginInterfaceActivity.uid;
                Registration reg = new Registration(courseID, courseTitle, courseType, id1, courseTerm, registFee);
                rRef.child(id1).child(courseTerm).child(courseID).setValue(reg);
                String spot = Integer.toString(currSpot - 1);
                course.setAvailableSpot(spot);
                courseReference = FirebaseDatabase.getInstance().getReference("Courses");
                courseReference.child(courseID).setValue(course);
                Toast.makeText(CourseViewList.this, "Add class successfully", Toast.LENGTH_LONG).show();

            }
        }
        else{
            Toast.makeText(CourseViewList.this, "You cannot add this course.", Toast.LENGTH_LONG).show();

        }
    }

    /**
     * Drop class.
     */
    public void dropClass(){
        int maxSpot = 0;
        boolean flag = false;
        for(int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            String tempType = coursesLec.get(m).getCourseType();
            if (tempTerm.equals(term) && temp.equals(courseTitle)) {
                courseID = coursesLec.get(m).getCourseID();
                currSpot = Integer.parseInt(coursesLec.get(m).getAvailableSpot());
                course = coursesLec.get(m);
                maxSpot = Integer.parseInt(coursesLec.get(m).getSpotMax().toString());

            }
        }
        for(int i = 0; i < registed.size(); i++){
            if(registed.get(i).getRegistCourseID().equals(courseID)){
                flag = true;
            }
        }
        if(currSpot < maxSpot && flag == true) {
            mRef = FirebaseDatabase.getInstance().getReference("Registrations")
                    .child(LoginInterfaceActivity.uid).child(SecondFragment.termNumber);
            mRef.child(courseID).removeValue();
            String spot = Integer.toString(currSpot + 1);
            course.setAvailableSpot(spot);
            courseReference = FirebaseDatabase.getInstance().getReference("Courses");
            courseReference.child(courseID).setValue(course);
            Toast.makeText(CourseViewList.this, "Course dropped", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(CourseViewList.this, "You cannot drop this course", Toast.LENGTH_LONG).show();
        }

    }
    public void checkDetail(){
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
            String courseType = "";
            String courseName = "";
            String courseDayTime = "";
            String courseDep = "";
            String courseInfo = "";
            String courseTime = "";
            String courseLocation = "";


            for (int i = 0; i < coursesLec.size(); i++) {
                String temp = coursesLec.get(i).getCourseID();
                course = coursesLec.get(i);
                if (temp.equals(courseID)) {
                    courseTitle = course.getCourseTitle();
                    registFee = course.getCourseFee();
                    courseType = course.getCourseType();
                    courseName = course.getCourseName();
                    courseDayTime = course.getCourseDayTime();
                    courseDep = course.getCourseDep();
                    courseInfo = course.getCourseInformation();
                    courseTime = course.getCourseTime();
                    courseLocation = course.getLocation();


                }
                String tempTerm = coursesLec.get(i).getCourseTerm();
                if (temp.equals(courseID) && tempTerm.equals(SecondFragment.termNumber)) {
                    courseTerm = course.getCourseTerm();
                }
            }

            Course course2 = new Course(courseID,courseTitle,courseDayTime,courseDep,courseInfo,courseName,
                    courseTerm,courseTime,courseType,null, null ,courseLocation,
                    null, null, null, null);
            CourseDetail.setCourse(course2);

        }

    }
    public static boolean testConflict(String leftStart, String leftEnd, String rightStart, String rightEnd) throws ParseException {
        Date leftStartTime=null, leftEndTime=null, rightStartTime=null, rightEndTime=null;
        try{
            leftStartTime = format.parse(leftStart);
            leftEndTime = format.parse(leftEnd);
            rightStartTime = format.parse(rightStart);
            rightEndTime = format.parse(rightEnd);
        }catch (ParseException e) {
            return false;
        }
        return
                ((leftStartTime.getTime() >= rightStartTime.getTime())
                        && (leftStartTime.getTime() < rightEndTime.getTime()))
                        || ((leftStartTime.getTime() > rightStartTime.getTime())
                        && (leftStartTime.getTime() <= rightEndTime.getTime()))
                        || ((rightStartTime.getTime() >= leftStartTime.getTime())
                        && (rightStartTime.getTime() < leftEndTime.getTime()))
                        || ((rightStartTime.getTime() > leftStartTime.getTime())
                        && (rightStartTime.getTime() <= leftEndTime.getTime())) ;
    }
    public void checkConflict() throws ParseException {
        boolean flag = false;

        //String tempDayTime;//the day time of registering course.
        String courseDayTime;//the day time of registered course.
        String time1 = null;
        String time2 = null;
        ArrayList<String> day = new ArrayList<>();
        //ArrayList registeredDay = new ArrayList<>();
        //String tempRegistedID = null;
        for (int m = 0; m < coursesLec.size(); m++) {
            String tempTerm = coursesLec.get(m).getCourseTerm();
            String temp = coursesLec.get(m).getCourseTitle();
            if (tempTerm.equals(term) && temp.equals(courseTitle)) {
                courseID = coursesLec.get(m).getCourseID();
            }
        }
        String tempRegistID = courseID;
        for (int i = 0; i < coursesAll.size(); i++) {
            String temp = coursesAll.get(i).getCourseID();
            //tempRegistID = editText.getText().toString();
            if (temp.equals(courseID)) {
                courseDayTime = coursesAll.get(i).getCourseDayTime();
                time1 = coursesAll.get(i).getCourseTime();
                String[] courseday = courseDayTime.split("");
                for (int k = 1; k < courseday.length; k++) {
                    if (courseday[k] != "") {
                        day.add(courseday[k]);
                    }
                }
            }
        }

        for (int i = 0; i < registedCourse.size(); i++) {
            String tempDayTime = registedCourse.get(i).getCourseDayTime();
            String tempRegistedID = registedCourse.get(i).getCourseID();
            ArrayList registeredDay = new ArrayList<>();
            time2 = registedCourse.get(i).getCourseTime();
            //System.out.print(registedCourse.get(i));
            String[] stringArray = tempDayTime.split("");
            for (int k = 1; k < stringArray.length; k++) {
                if (stringArray[k] != "") {
                    registeredDay.add(stringArray[k]);
                }
            }
            if (tempRegistID.equals(tempRegistedID)) {
                flag = false;
            }
            else {
                for (int m = 0; m < registeredDay.size(); m++) {
                    if (day.contains(registeredDay.get(m))) {
                        String[] stringArray1 = time1.split("-");
                        String[] stringArray2 = time2.split("-");
                        flag = testConflict(stringArray1[0], stringArray1[1], stringArray2[0], stringArray2[1]);
                        if (flag == true) {
                            break;
                        }
                    }
                }
            }
        }

        if(flag == false){

            Toast.makeText(CourseViewList.this, "Add course success!", Toast.LENGTH_LONG).show();
            addCourse();

            //time not conflict
        }
        else{
            Toast.makeText(CourseViewList.this, "Can not add course, time conflict !!!!", Toast.LENGTH_LONG).show();
            //time conflict
        }


    }


}
