package com.project.csci3130.dalrs;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Registration implements Serializable{
    public String RegistCourseID;
    public String RegistTitle;
    public String RegistLabID;
    public String RegistType;
    public String userID;
    public String RegistTerm;

    public Registration(){

    }
    public Registration(String registCourseID, String registCourseTitle, String registType,String userID,String registTerm) {
        RegistCourseID = registCourseID;
        RegistTitle = registCourseTitle;
        RegistType = registType;
        this.userID = userID;
        RegistTerm = registTerm;
    }
    @Exclude

    public String getRegistTitle() {
        return RegistTitle;
    }
    @Exclude

    public void setRegistTitle(String registTitle) {
        RegistTitle = registTitle;
    }
    @Exclude

    public String getRegistType() {
        return RegistType;
    }
    @Exclude

    public void setRegistType(String registType) {
        RegistType = registType;
    }

    @Exclude
    public String getRegistCourseID() {
        return RegistCourseID;
    }
    @Exclude
    public void setRegistCourseID(String registCourseID) {
        RegistCourseID = registCourseID;
    }

    @Exclude
    public String getRegistLabID() {
        return RegistLabID;
    }
    @Exclude
    public void setRegistLabID(String registLabID) {
        RegistLabID = registLabID;
    }
    @Exclude

    public String getUserID() {
        return userID;
    }
    @Exclude

    public void setUserID(String userID) {
        this.userID = userID;
    }
    @Exclude

    public String getRegistTerm() {
        return RegistTerm;
    }
    @Exclude

    public void setRegistTerm(String registTerm) {
        RegistTerm = registTerm;
    }
}
