package com.izpa.jira.plugins.rest.xml;

import com.izpa.jira.plugins.entity.RecordEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Mapper {
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static XmlRecord toXmlRecord(RecordEntity entity) {
        XmlRecord record = new XmlRecord();
        record.id = entity.getID();
        record.text = entity.getText();
        record.date = entity.getDate()==null?null:format.format(entity.getDate());
        return record;
    }
}
