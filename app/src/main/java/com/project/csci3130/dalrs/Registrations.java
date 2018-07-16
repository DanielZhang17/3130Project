package com.project.csci3130.dalrs;

/**
 * The type Registrations.
 */
public class Registrations {
    private String RegistCourseID;
    private String RegistCourseName;
    private String RegistLabID;

    /**
     * Instantiates a new Registrations.
     */
    public Registrations() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Instantiates a new Registrations.
     *
     * @param RegistCourseID   the regist course id
     * @param RegistCourseName the regist course name
     * @param RegistLabID      the regist lab id
     */
    public Registrations(String RegistCourseID,String RegistCourseName,String RegistLabID){
        this.RegistCourseID = RegistCourseID;
        this.RegistCourseName = RegistCourseName;
        this.RegistLabID = RegistLabID;

    }

    /**
     * Sets regist course id.
     *
     * @param registCourseID the regist course id
     */
    public void setRegistCourseID(String registCourseID) {
        RegistCourseID = registCourseID;
    }

    /**
     * Sets regist course name.
     *
     * @param registCourseName the regist course name
     */
    public void setRegistCourseName(String registCourseName) {
        RegistCourseName = registCourseName;
    }

    /**
     * Sets regist lab id.
     *
     * @param registLabID the regist lab id
     */
    public void setRegistLabID(String registLabID) {
        RegistLabID = registLabID;
    }

    /**
     * Get regist course id string.
     *
     * @return the string
     */
    public String getRegistCourseID(){
        return RegistCourseID;
    }

    /**
     * Get regist course name string.
     *
     * @return the string
     */
    public String getRegistCourseName(){
        return RegistCourseName;
    }

    /**
     * Get regist lab id string.
     *
     * @return the string
     */
    public String getRegistLabID(){
        return RegistLabID;
    }

}
