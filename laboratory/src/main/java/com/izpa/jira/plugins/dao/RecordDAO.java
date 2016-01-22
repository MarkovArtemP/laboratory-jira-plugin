package com.izpa.jira.plugins.dao;

import com.izpa.jira.plugins.entity.RecordEntity;
import com.izpa.jira.plugins.logic.Record;

public interface RecordDAO {
    public RecordEntity addRecord(Record record) throws Exception;
    public RecordEntity[] getRecords() throws Exception;
}
