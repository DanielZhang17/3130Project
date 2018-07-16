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
    private String LabDayTime;
    private String LabID;
    private String LabTime;
    private String Location;
    private String SpotMax;
    private String StartDate;
    private String TutID;
    private String LabLocation;
    private String TutLocation;
    private String AvailableSpot;
    private String CourseFee;

    /**
     * Gets lab location.
     *
     * @return the lab location
     */
    public String getLabLocation() {
        return LabLocation;
    }

    /**
     * Sets lab location.
     *
     * @param labLocation the lab location
     */
    public void setLabLocation(String labLocation) {
        LabLocation = labLocation;
    }

    /**
     * Gets tut location.
     *
     * @return the tut location
     */
    public String getTutLocation() {
        return TutLocation;
    }

    /**
     * Sets tut location.
     *
     * @param tutLocation the tut location
     */
    public void setTutLocation(String tutLocation) {
        TutLocation = tutLocation;
    }

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
     * @param labDayTime        the lab day time
     * @param labID             the lab id
     * @param labTime           the lab time
     * @param location          the location
     * @param spotMax           the spot max
     * @param startDate         the start date
     * @param TutID             the tut id
     * @param labLocation       the lab location
     * @param tutLocation       the tut location
     */
    public Course(String courseID, String courseTitle, String courseDayTime,
                  String courseDep, String courseInformation, String courseName,
                  String courseTerm, String courseTime, String courseType,
                  String credit, String endDate, String labDayTime, String labID,
                  String labTime, String location, String spotMax, String startDate,
                  String TutID, String labLocation,String tutLocation,String AvailableSpot,
                  String CourseFee) {
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
        this.AvailableSpot = AvailableSpot;
        this.CourseFee = CourseFee;
    }

    /**
     * Gets tut id.
     *
     * @return the tut id
     */
    public String getTutID() {
        return TutID;
    }

    /**
     * Sets tut id.
     *
     * @param tutID the tut id
     */
    public void setTutID(String tutID) {
        TutID = tutID;
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
     * Gets lab day time.
     *
     * @return the lab day time
     */
    public String getLabDayTime() {
        return LabDayTime;
    }

    /**
     * Sets lab day time.
     *
     * @param labDayTime the lab day time
     */
    public void setLabDayTime(String labDayTime) {
        LabDayTime = labDayTime;
    }

    /**
     * Gets lab id.
     *
     * @return the lab id
     */
    public String getLabID() {
        return LabID;
    }

    /**
     * Sets lab id.
     *
     * @param labID the lab id
     */
    public void setLabID(String labID) {
        LabID = labID;
    }

    /**
     * Gets lab time.
     *
     * @return the lab time
     */
    public String getLabTime() {
        return LabTime;
    }

    /**
     * Sets lab time.
     *
     * @param labTime the lab time
     */
    public void setLabTime(String labTime) {
        LabTime = labTime;
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
