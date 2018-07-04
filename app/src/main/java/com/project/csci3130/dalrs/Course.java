package com.project.csci3130.dalrs;

public class Course {

    private String CourseID;
    private String CourseTitle;
    private String CourseDayTime;
    private String CourseDep;
    private String CourseInformation;
    private String CourseName;
    private String CourseTerm;
    private String CourseTime;
    private String CourseType;
    private String Credit;
    private String EndDate;
    private String LabDayTime;
    private String LabID;
    private String LabTime;
    private String Location;
    private String SpotMax;
    private String StartDate;
    private String TutID;
    private String LabLocation;
    private String TutLocation;

    public String getLabLocation() {
        return LabLocation;
    }

    public void setLabLocation(String labLocation) {
        LabLocation = labLocation;
    }

    public String getTutLocation() {
        return TutLocation;
    }

    public void setTutLocation(String tutLocation) {
        TutLocation = tutLocation;
    }

    public Course(){

    }

    public Course(String courseID, String courseTitle, String courseDayTime,
                  String courseDep, String courseInformation, String courseName,
                  String courseTerm, String courseTime, String courseType,
                  String credit, String endDate, String labDayTime, String labID,
                  String labTime, String location, String spotMax, String startDate, String TutID, String labLocation,String tutLocation) {
        CourseID = courseID;
        CourseTitle = courseTitle;
        CourseDayTime = courseDayTime;
        CourseDep = courseDep;
        CourseInformation = courseInformation;
        CourseName = courseName;
        CourseTerm = courseTerm;
        CourseTime = courseTime;
        CourseType = courseType;
        Credit = credit;
        EndDate = endDate;
        LabDayTime = labDayTime;
        LabID = labID;
        LabTime = labTime;
        Location = location;
        SpotMax = spotMax;
        StartDate = startDate;
        this.TutID = TutID;
    }

    public String getTutID() {
        return TutID;
    }

    public void setTutID(String tutID) {
        TutID = tutID;
    }

    public String getCourseDayTime() {
        return CourseDayTime;
    }

    public void setCourseDayTime(String courseDayTime) {
        CourseDayTime = courseDayTime;
    }

    public String getCourseDep() {
        return CourseDep;
    }

    public void setCourseDep(String courseDep) {
        CourseDep = courseDep;
    }

    public String getCourseInformation() {
        return CourseInformation;
    }

    public void setCourseInformation(String courseInformation) {
        CourseInformation = courseInformation;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseTerm() {
        return CourseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        CourseTerm = courseTerm;
    }

    public String getCourseTime() {
        return CourseTime;
    }

    public void setCourseTime(String courseTime) {
        CourseTime = courseTime;
    }

    public String getCourseType() {
        return CourseType;
    }

    public void setCourseType(String courseType) {
        CourseType = courseType;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getLabDayTime() {
        return LabDayTime;
    }

    public void setLabDayTime(String labDayTime) {
        LabDayTime = labDayTime;
    }

    public String getLabID() {
        return LabID;
    }

    public void setLabID(String labID) {
        LabID = labID;
    }

    public String getLabTime() {
        return LabTime;
    }

    public void setLabTime(String labTime) {
        LabTime = labTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSpotMax() {
        return SpotMax;
    }

    public void setSpotMax(String spotMax) {
        SpotMax = spotMax;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }
}
