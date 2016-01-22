package com.izpa.jira.plugins.logic.impl;

import com.izpa.jira.plugins.logic.Record;

import java.util.Date;

public class RecordImpl implements Record {
    private String text;
    private Date date;

    private RecordImpl(){}
    public RecordImpl(String text, Date date){
        this.setText(text);
        this.setDate(date);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setDate(Date date) {
        this.date=date;
    }

    public Date getDate() {
        return this.date;
    }
}
