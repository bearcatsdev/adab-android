package com.ambinusian.adab.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ClassEntity {
    public ClassEntity(int transaction_id, String course_code, String course_name, String language, String class_code, String class_type, int class_icon, String session, String topic, String transaction_date, String transaction_time, int is_live, int is_done) {
        this.transaction_id = transaction_id;
        this.course_code = course_code;
        this.course_name = course_name;
        this.language = language;
        this.class_code = class_code;
        this.class_type = class_type;
        this.class_icon = class_icon;
        this.session = session;
        this.topic = topic;
        this.transaction_date = transaction_date;
        this.transaction_time = transaction_time;
        this.is_live = is_live;
        this.is_done = is_done;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClass_type() {
        return class_type;
    }

    public void setClass_type(String class_type) {
        this.class_type = class_type;
    }

    public int getClass_icon() {
        return class_icon;
    }

    public void setClass_icon(int class_icon) {
        this.class_icon = class_icon;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public int getIs_live() {
        return is_live;
    }

    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int is_done) {
        this.is_done = is_done;
    }

    @PrimaryKey
    private int transaction_id;

    @ColumnInfo(name="course_code")
    private String course_code;

    @ColumnInfo(name="course_name")
    private String course_name;

    @ColumnInfo(name="language")
    private String language;

    @ColumnInfo(name="class_code")
    private String class_code;

    @ColumnInfo(name="class_type")
    private String class_type;

    @ColumnInfo(name="class_icon")
    private int class_icon;

    @ColumnInfo(name="session")
    private String session;

    @ColumnInfo(name="topic")
    private String topic;

    @ColumnInfo(name="transaction_date")
    private String transaction_date;

    @ColumnInfo(name="transaction_time")
    private String transaction_time;

    @ColumnInfo(name="is_live")
    private int is_live;

    @ColumnInfo(name="is_done")
    private int is_done;


}
