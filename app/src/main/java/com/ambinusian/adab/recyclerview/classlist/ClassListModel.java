package com.ambinusian.adab.recyclerview.classlist;

public class ClassListModel {
    private int classIcon = 0, classId = 0;
    private String classTopic = "",meeting = "",time = "",day = "";

    public ClassListModel(int classIcon, int classId, String classTopic, String meeting, String time, String day) {
        this.classIcon = classIcon;
        this.classId = classId;
        this.classTopic = classTopic;
        this.meeting = meeting;
        this.time = time;
        this.day = day;
    }

    public int getClassIcon() {
        return classIcon;
    }

    public void setClassIcon(int classIcon) {
        this.classIcon = classIcon;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassTopic() {
        return classTopic;
    }

    public void setClassTopic(String classTopic) {
        this.classTopic = classTopic;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
