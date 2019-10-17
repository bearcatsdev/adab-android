package com.ambinusian.adab.recyclerview.nextorlatestclass;

public class NextOrLatestClassModel {
    private int classIcon = 0, classId = 0;
    private String classTopic = "",session = "",room = "",time = "", date=" ";

    public NextOrLatestClassModel(int classIcon, int classId, String classTopic, String session, String room, String time, String date) {
        this.classIcon = classIcon;
        this.classId = classId;
        this.classTopic = classTopic;
        this.session = session;
        this.room = room;
        this.time = time;
        this.date = date;
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

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
