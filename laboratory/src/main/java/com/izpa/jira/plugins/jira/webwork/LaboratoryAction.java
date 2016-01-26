package com.izpa.jira.plugins.jira.webwork;

import com.izpa.jira.plugins.dao.DAOFactory;
import com.izpa.jira.plugins.entity.RecordEntity;
import com.izpa.jira.plugins.logic.Record;
import com.izpa.jira.plugins.logic.impl.RecordImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import webwork.action.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LaboratoryAction extends JiraWebActionSupport {
    private static final Logger log = LoggerFactory.getLogger(LaboratoryAction.class);
    private DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
    private RecordEntity[] records;
    @Override
    public String execute() throws Exception {
        records = DAOFactory.getInstance().getRecordDAO().getRecords();
        return super.execute();
    }
    public String doAdd() throws Exception {
        HttpServletRequest request = getHttpRequest();
        String text = request.getParameterValues("text")[0];
        String strDate = request.getParameterValues("date")[0];
        Date date = format.parse(strDate);
        Record record = new RecordImpl(text, date);
        DAOFactory.getInstance().getRecordDAO().addRecord(record);
        ServletActionContext.getResponse().sendRedirect("/secure/LaboratoryAction.jspa");
        return NONE;
    }

    public RecordEntity[] getStudents() {
        return records;
    }
}
