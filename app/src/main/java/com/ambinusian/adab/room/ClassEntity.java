package com.ambinusian.adab.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ClassEntity {
    // untuk masuk ke live streaming pake session_id
    @PrimaryKey
    int sessionId;
    // course_id -> EEEK2222
    String courseId;
    // course_name -> Introduction to Art of Manipulation
    String courseName;
    // session_th -> session ke berapa
    int sessionTh;
    // session_mode -> LEC, CL, LAB, blablabla
    String sessionMode;
    // class_name -> LA88
    String className;
    // topic_title -> How to manipulate someone's mind with ease
    String topicTitle;
    // topic_description -> deskripsi singkat tentang topik yang bersangkutan
    String topicDescription;
    // session_campus -> Anggrek, Kijang, Syahdan, blablabla
    String sessionCampus;
    // session_room -> 400
    String sessionRoom;
    // lecturer_id -> D8888
    String lecturerId;
    // lectuer_name -> Felik Orange
    String lecturerName;
    // session_startdate -> waktu mulai
    String sessionStartDate;
    // session_enddate -> waktu selesai
    String sessionEndDate;

    public ClassEntity(int sessionId, String courseId, String courseName, int sessionTh, String sessionMode, String className, String topicTitle, String topicDescription, String sessionCampus, String sessionRoom, String lecturerId, String lecturerName, String sessionStartDate, String sessionEndDate) {
        this.sessionId = sessionId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.sessionTh = sessionTh;
        this.sessionMode = sessionMode;
        this.className = className;
        this.topicTitle = topicTitle;
        this.topicDescription = topicDescription;
        this.sessionCampus = sessionCampus;
        this.sessionRoom = sessionRoom;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSessionTh() {
        return sessionTh;
    }

    public void setSessionTh(int sessionTh) {
        this.sessionTh = sessionTh;
    }

    public String getSessionMode() {
        return sessionMode;
    }

    public void setSessionMode(String sessionMode) {
        this.sessionMode = sessionMode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getSessionCampus() {
        return sessionCampus;
    }

    public void setSessionCampus(String sessionCampus) {
        this.sessionCampus = sessionCampus;
    }

    public String getSessionRoom() {
        return sessionRoom;
    }

    public void setSessionRoom(String sessionRoom) {
        this.sessionRoom = sessionRoom;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getSessionStartDate() {
        return sessionStartDate;
    }

    public void setSessionStartDate(String sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public String getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(String sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }
}
