package com.ambinusian.adab.recyclerview.schedule;

public class ScheduleModel {
    //information needed = date, classType, classTitle, courseCode, classRoom, classTime
    private String classDate,classType,classTitle,course, courseCode, classRoom, classTime;

    public ScheduleModel(String classDate, String classType, String classTitle,String course, String courseCode, String classRoom, String classTime) {
        this.classDate = classDate;
        this.classType = classType;
        this.classTitle = classTitle;
        this.course = course;
        this.courseCode = courseCode;
        this.classRoom = classRoom;
        this.classTime = classTime;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
}
