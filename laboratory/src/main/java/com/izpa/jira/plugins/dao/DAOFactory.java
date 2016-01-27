package com.izpa.jira.plugins.dao;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.izpa.jira.plugins.dao.impl.RecordDAOImpl;

import static com.google.common.base.Preconditions.checkNotNull;

public class DAOFactory {
    private static RecordDAO recordDAO = null;
    private static DAOFactory instance = null;
    private static ActiveObjects ao;
    private DAOFactory(ActiveObjects ao) {
        DAOFactory.ao=checkNotNull(ao);
    }
    public static synchronized DAOFactory getInstance() throws Exception {
        if (instance == null) {
            instance = new DAOFactory(checkNotNull(ao));
        }
        return instance;
    }
    public RecordDAO getRecordDAO() {
        if (recordDAO == null) {
            recordDAO = new RecordDAOImpl(ao);
        }
        return recordDAO;
    }
}
