package com.project.csci3130.dalrs;

public class Registrations {
    private String RegistCourseID;
    private String RegistCourseName;
    private String RegistLabID;

    public Registrations() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Registrations(String RegistCourseID,String RegistCourseName,String RegistLabID){
        this.RegistCourseID = RegistCourseID;
        this.RegistCourseName = RegistCourseName;
        this.RegistLabID = RegistLabID;

    }

    public void setRegistCourseID(String registCourseID) {
        RegistCourseID = registCourseID;
    }

    public void setRegistCourseName(String registCourseName) {
        RegistCourseName = registCourseName;
    }

    public void setRegistLabID(String registLabID) {
        RegistLabID = registLabID;
    }

    public String getRegistCourseID(){
        return RegistCourseID;
    }

    public String getRegistCourseName(){
        return RegistCourseName;
    }

    public String getRegistLabID(){
        return RegistLabID;
    }

}