package com.ambinusian.adab.MainActivity.CourseRecyclerView;


public class CourseModel {
        private String classTime;
        private String classTitle;
        private String course;
        private String classMeeting;
        private String courseCode;
        private String classCode;
        private String classType;
        private int classIcon;

    public CourseModel(int classIcon, String classTime, String classTitle, String course, String classMeeting, String courseCode, String classCode,String classType) {
        this.classIcon = classIcon;
        this.classTime = classTime;
        this.classTitle = classTitle;
        this.course = course;
        this.classMeeting = classMeeting;
        this.courseCode = courseCode;
        this.classCode = classCode;
        this.classType = classType;
    }

    public int getClassIcon() {
        return classIcon;
    }

    public void setClassIcon(int classIcon) {
        this.classIcon = classIcon;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime( String classTime) {
        this.classTime = classTime;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


    public String getClassMeeting() {
        return classMeeting;
    }

    public void setClassMeeting(String courseMeeting) {
        this.classMeeting = courseMeeting;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}