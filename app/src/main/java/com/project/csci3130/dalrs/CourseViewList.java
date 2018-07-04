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
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

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
    private DatabaseReference mReference;
    private DatabaseReference wReference;
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter adapter;
    private List<String> courseCS = new ArrayList<>();
    private List<String> courseSTAT = new ArrayList<>();
    private String[] groupList = new String[]{"Computer Science","Statistic"};
    private Map<String, List<String>> courseList = new HashMap<>();
    DatabaseReference ref;
    Course course;
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
                    courses1.add(course);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //iniUI();
        initData();
        listView();
        //courseList.toString();
        //CourseViewList.listView();
        Intent intent = getIntent();

        adapter = new ExpandableListViewAdapter();
        expandableListView.setAdapter(adapter);

    }
    private void initData() {
        mReference = FirebaseDatabase.getInstance().getReference();
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
    private void collectCourseTitles(Map<String,Object> courses){
        String[] list =null;
        for(Map.Entry<String,Object> entry: courses.entrySet()){

            Map course = (Map) entry.getValue();
            boolean flag = false;
/*
            String title = (String) course.get("CourseTitle");
            String dep = (String) course.get("CourseDep");
            String type = (String) course.get("CourseType");

            if(title != null && dep.equals("Computer Science") && !courseCS.contains(title) && type.equals("Lec")){
                courseCS.add(title);
            }
            if(title !=null && dep.contains("Statistics") && !courseSTAT.contains(title) && type.equals("Lec")){
                courseSTAT.add(title);
            }*/
            for(int i=0; i< courses.size(); i++){
                if(courses1.get(i).getCourseType().contains("ec")){
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
    private void listView(){

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
            convertView.setTag(R.layout.group_item, parentPosition);

            convertView.setTag(R.layout.child_item, -1);

            TextView textView = (TextView) convertView.findViewById(R.id.groupView);

            textView.setText(groupList[parentPosition]);

            return convertView;
        }

        @Override
        public View getChildView(int parentPosition, int childPosition, boolean isLastChild,
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

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int parentPosition, int childPosition){
            return false;  //not in interaction 1
        }
    }

}
