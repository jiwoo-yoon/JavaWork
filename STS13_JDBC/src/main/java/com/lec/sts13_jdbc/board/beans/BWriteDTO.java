package com.lec.sts13_jdbc.board.beans;

import java.sql.Timestamp;

public class BWriteDTO {
    private int uid;
    private String subject;
    private String content;
    private String name;
    private int viewCnt;
    private Timestamp regDate;

    public BWriteDTO() {
    }

    public BWriteDTO(int uid, String subject, String content, String name, int viewCnt, Timestamp regDate) {
        this.uid = uid;
        this.subject = subject;
        this.content = content;
        this.name = name;
        this.viewCnt = viewCnt;
        this.regDate = regDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
}
