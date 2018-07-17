package com.project.csci3130.dalrs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CourseDetail extends AppCompatActivity {
    private static Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        TextView crn,coursetitle,coursedep,coursename,coursetype,
                courseterm,coursedaytime,coursetime,location;
        crn = findViewById(R.id.crn);
        coursetitle = findViewById(R.id.coursetitle);
        coursedep = findViewById(R.id.coursedep);
        coursename = findViewById(R.id.coursename);
        coursetype = findViewById(R.id.coursetype);
        courseterm = findViewById(R.id.courseterm);
        coursedaytime = findViewById(R.id.coursedaytime);
        coursetime = findViewById(R.id.coursetime);
        location = findViewById(R.id.location);
        crn.setText(course.getCourseID());
        coursetitle.setText(course.getCourseTitle());
        coursedep.setText(course.getCourseDep());
        coursename.setText(course.getCourseName());
        coursetype.setText(course.getCourseType());
        courseterm.setText(course.getCourseTerm());
        coursedaytime.setText(course.getCourseDayTime());
        coursetime.setText(course.getCourseTime());
        location.setText(course.getLocation());


    }
    public static void setCourse(Course c){
        course = c;
    }

}
