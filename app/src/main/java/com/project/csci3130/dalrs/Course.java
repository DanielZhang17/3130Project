package com.project.csci3130.dalrs;

/**
 * The type Course.
 */
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
    private String Location;
    private String SpotMax;
    private String StartDate;
    private String AvailableSpot;
    private String CourseFee;


    /**
     * Instantiates a new Course.
     */
    public Course(){

    }

    /**
     * Instantiates a new Course.
     *
     * @param courseID          the course id
     * @param courseTitle       the course title
     * @param courseDayTime     the course day time
     * @param courseDep         the course dep
     * @param courseInformation the course information
     * @param courseName        the course name
     * @param courseTerm        the course term
     * @param courseTime        the course time
     * @param courseType        the course type
     * @param credit            the credit
     * @param endDate           the end date
     * @param location          the location
     * @param spotMax           the spot max
     * @param startDate         the start date
    n
     */
    public Course(String courseID, String courseTitle, String courseDayTime,
                  String courseDep, String courseInformation, String courseName,
                  String courseTerm, String courseTime, String courseType,
                  String credit, String endDate, String location, String spotMax, String startDate,
                  String AvailableSpot, String CourseFee) {
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
        Location = location;
        SpotMax = spotMax;
        StartDate = startDate;
        this.AvailableSpot = AvailableSpot;
        this.CourseFee = CourseFee;
    }


    /**
     * Gets course day time.
     *
     * @return the course day time
     */
    public String getCourseDayTime() {
        return CourseDayTime;
    }

    /**
     * Sets course day time.
     *
     * @param courseDayTime the course day time
     */
    public void setCourseDayTime(String courseDayTime) {
        CourseDayTime = courseDayTime;
    }

    /**
     * Gets course dep.
     *
     * @return the course dep
     */
    public String getCourseDep() {
        return CourseDep;
    }

    /**
     * Sets course dep.
     *
     * @param courseDep the course dep
     */
    public void setCourseDep(String courseDep) {
        CourseDep = courseDep;
    }

    /**
     * Gets course information.
     *
     * @return the course information
     */
    public String getCourseInformation() { return CourseInformation;
    }

    /**
     * Sets course information.
     *
     * @param courseInformation the course information
     */
    public void setCourseInformation(String courseInformation) {
        CourseInformation = courseInformation;
    }

    /**
     * Gets course name.
     *
     * @return the course name
     */
    public String getCourseName() {
        return CourseName;
    }

    /**
     * Sets course name.
     *
     * @param courseName the course name
     */
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    /**
     * Gets course term.
     *
     * @return the course term
     */
    public String getCourseTerm() {
        return CourseTerm;
    }

    /**
     * Sets course term.
     *
     * @param courseTerm the course term
     */
    public void setCourseTerm(String courseTerm) {
        CourseTerm = courseTerm;
    }

    /**
     * Gets course time.
     *
     * @return the course time
     */
    public String getCourseTime() {
        return CourseTime;
    }

    /**
     * Sets course time.
     *
     * @param courseTime the course time
     */
    public void setCourseTime(String courseTime) {
        CourseTime = courseTime;
    }

    /**
     * Gets course type.
     *
     * @return the course type
     */
    public String getCourseType() {
        return CourseType;
    }

    /**
     * Sets course type.
     *
     * @param courseType the course type
     */
    public void setCourseType(String courseType) {
        CourseType = courseType;
    }

    /**
     * Gets credit.
     *
     * @return the credit
     */
    public String getCredit() {
        return Credit;
    }

    /**
     * Sets credit.
     *
     * @param credit the credit
     */
    public void setCredit(String credit) {
        Credit = credit;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEndDate() {
        return EndDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }



    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * Gets spot max.
     *
     * @return the spot max
     */
    public String getSpotMax() {
        return SpotMax;
    }

    /**
     * Sets spot max.
     *
     * @param spotMax the spot max
     */
    public void setSpotMax(String spotMax) {
        SpotMax = spotMax;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public String getStartDate() {
        return StartDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    /**
     * Gets course id.
     *
     * @return the course id
     */
    public String getCourseID() {
        return CourseID;
    }

    /**
     * Sets course id.
     *
     * @param courseID the course id
     */
    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    /**
     * Gets course title.
     *
     * @return the course title
     */
    public String getCourseTitle() {
        return CourseTitle;
    }

    /**
     * Sets course title.
     *
     * @param courseTitle the course title
     */
    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }

    public String getAvailableSpot() {
        return AvailableSpot;
    }

    public void setAvailableSpot(String availableSpot) {
        AvailableSpot = availableSpot;
    }

    public String getCourseFee() {
        return CourseFee;
    }

    public void setCourseFee(String courseFee) {
        CourseFee = courseFee;
    }
}
