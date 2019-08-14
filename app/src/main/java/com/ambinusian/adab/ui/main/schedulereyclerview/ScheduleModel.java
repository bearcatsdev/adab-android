package com.ambinusian.adab.ui.main.schedulereyclerview;

public class ScheduleModel {
    //information needed = date, classType, classTitle, courseCode, classRoom, classTime
    private String date,classType,classTitle,courseCode, classRoom, classTime;

    public ScheduleModel(String date, String classType, String classTitle, String courseCode, String classRoom, String classTime) {
        this.date = date;
        this.classType = classType;
        this.classTitle = classTitle;
        this.courseCode = courseCode;
        this.classRoom = classRoom;
        this.classTime = classTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
