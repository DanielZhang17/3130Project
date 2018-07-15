package com.project.csci3130.dalrs;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

/**
 * The type Registration.
 */
public class Registration implements Serializable{
    /**
     * The Regist course id.
     */
    public String RegistCourseID;
    /**
     * The Regist title.
     */
    public String RegistTitle;
    /**
     * The Regist lab id.
     */
    public String RegistLabID;
    /**
     * The Regist type.
     */
    public String RegistType;
    /**
     * The User id.
     */
    public String userID;
    /**
     * The Regist term.
     */
    public String RegistTerm;
    /**
     * The Regist Fee
     */
    public String RegistFee;

    /**
     * Instantiates a new Registration.
     */
    public Registration(){

    }

    /**
     * Instantiates a new Registration.
     *
     * @param registCourseID    the regist course id
     * @param registCourseTitle the regist course title
     * @param registType        the regist type
     * @param userID            the user id
     * @param registTerm        the regist term
     */
    public Registration(String registCourseID, String registCourseTitle, String registType,
                        String userID,String registTerm,String registFee) {
        RegistCourseID = registCourseID;
        RegistTitle = registCourseTitle;
        RegistType = registType;
        this.userID = userID;
        RegistTerm = registTerm;
        RegistFee = registFee;
    }

    /**
     * Gets regist title.
     *
     * @return the regist title
     */
    @Exclude

    public String getRegistTitle() {
        return RegistTitle;
    }

    /**
     * Sets regist title.
     *
     * @param registTitle the regist title
     */
    @Exclude

    public void setRegistTitle(String registTitle) {
        RegistTitle = registTitle;
    }

    /**
     * Gets regist type.
     *
     * @return the regist type
     */
    @Exclude

    public String getRegistType() {
        return RegistType;
    }

    /**
     * Sets regist type.
     *
     * @param registType the regist type
     */
    @Exclude

    public void setRegistType(String registType) {
        RegistType = registType;
    }

    /**
     * Gets regist course id.
     *
     * @return the regist course id
     */
    @Exclude
    public String getRegistCourseID() {
        return RegistCourseID;
    }

    /**
     * Sets regist course id.
     *
     * @param registCourseID the regist course id
     */
    @Exclude
    public void setRegistCourseID(String registCourseID) {
        RegistCourseID = registCourseID;
    }

    /**
     * Gets regist lab id.
     *
     * @return the regist lab id
     */
    @Exclude
    public String getRegistLabID() {
        return RegistLabID;
    }

    /**
     * Sets regist lab id.
     *
     * @param registLabID the regist lab id
     */
    @Exclude
    public void setRegistLabID(String registLabID) {
        RegistLabID = registLabID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    @Exclude

    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    @Exclude

    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets regist term.
     *
     * @return the regist term
     */
    @Exclude

    public String getRegistTerm() {
        return RegistTerm;
    }

    /**
     * Sets regist term.
     *
     * @param registTerm the regist term
     */
    @Exclude

    public void setRegistTerm(String registTerm) {
        RegistTerm = registTerm;
    }

    public String getRegistFee() { return RegistFee; }

    public void setRegistFee(String registFee) { RegistFee = registFee; }
}
