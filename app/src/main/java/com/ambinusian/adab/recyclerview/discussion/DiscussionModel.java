package com.ambinusian.adab.recyclerview.discussion;

public class DiscussionModel {
    private String time, content, name, discussion_class;

    public DiscussionModel(String time, String content, String name, String discussion_class) {
        this.time = time;
        this.content = content;
        this.name = name;
        this.discussion_class = discussion_class;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscussion_class() {
        return discussion_class;
    }

    public void setDiscussion_class(String discussion_class) {
        this.discussion_class = discussion_class;
    }
}
