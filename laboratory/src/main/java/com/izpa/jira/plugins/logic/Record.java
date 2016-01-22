package com.izpa.jira.plugins.logic;

import java.util.Date;

public interface Record {
    public void setText(String text);
    public String getText();
    public void setDate(Date date);
    public Date getDate();
}
