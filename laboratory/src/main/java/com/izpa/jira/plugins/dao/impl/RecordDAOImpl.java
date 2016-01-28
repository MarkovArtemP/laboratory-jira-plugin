package com.izpa.jira.plugins.dao.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.izpa.jira.plugins.dao.RecordDAO;
import com.izpa.jira.plugins.entity.RecordEntity;
import com.izpa.jira.plugins.logic.Record;
import net.java.ao.Query;

public class RecordDAOImpl implements RecordDAO {
    private final ActiveObjects ao;
    public RecordDAOImpl(ActiveObjects ao) {
        this.ao = ao;
    }

    public RecordEntity addRecord(final Record record) throws Exception {
        return ao.executeInTransaction(new TransactionCallback<RecordEntity>() {
            public RecordEntity doInTransaction() {
                RecordEntity entity = ao.create(RecordEntity.class);
                entity.setText(record.getText());
                entity.setDate(record.getDate());
                entity.save();
                return entity;
            }
        });
    }

    public RecordEntity[] getRecords() throws Exception {
        return ao.executeInTransaction(new TransactionCallback<RecordEntity[]>() {
            public RecordEntity[] doInTransaction() {
                return ao.find(RecordEntity.class);
            }
        });
    }

    public RecordEntity deleteRecord(final long id) throws Exception {
        return ao.executeInTransaction(new TransactionCallback<RecordEntity>() {
            public RecordEntity doInTransaction() {
                RecordEntity entity = ao.find(RecordEntity.class, Query.select().where("ID=?", id))[0];
                ao.delete(entity);
                return entity;
            }
        });
    }

    public RecordEntity updateRecord(final long id, final Record record) throws Exception {
        return ao.executeInTransaction(new TransactionCallback<RecordEntity>() {
            public RecordEntity doInTransaction() {
                RecordEntity entity = ao.find(RecordEntity.class, Query.select().where("ID=?", id))[0];
                if (record.getText() != null) {
                    entity.setText(record.getText());
                }
                if (record.getDate() != null) {
                    entity.setDate(record.getDate());
                }
                entity.save();
                return entity;
            }
        });
    }
    public RecordEntity getRecord(long id) throws Exception{
        return ao.find(RecordEntity.class, Query.select().where("ID=?", id))[0];
    }
}
