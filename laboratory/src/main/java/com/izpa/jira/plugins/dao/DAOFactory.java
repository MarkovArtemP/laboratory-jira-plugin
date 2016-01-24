package com.izpa.jira.plugins.dao;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.izpa.jira.plugins.dao.impl.RecordDAOImpl;
import com.izpa.jira.plugins.logic.impl.RecordImpl;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            DAOFactory.getInstance().getRecordDAO().addRecord(new RecordImpl("qwe",format.parse("11.11.1213")));
            DAOFactory.getInstance().getRecordDAO().addRecord(new RecordImpl("abc",format.parse("12.12.1312")));
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
