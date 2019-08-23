package com.ambinusian.adab.ui.lecturer.main.classlistrecyclerview;

public class ClassListModel {
    private int classIcon, classId;
    private String classTopic,meeting,time;


    public ClassListModel(int classIcon, int classId, String classTopic, String meeting, String time) {
        this.classIcon = classIcon;
        this.classId = classId;
        this.classTopic = classTopic;
        this.meeting = meeting;
        this.time = time;
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
}
