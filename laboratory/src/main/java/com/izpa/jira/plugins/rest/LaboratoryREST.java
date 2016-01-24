package com.izpa.jira.plugins.rest;

import com.atlassian.jira.rest.v1.util.CacheControl;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.google.common.collect.Lists;
import com.izpa.jira.plugins.dao.DAOFactory;
import com.izpa.jira.plugins.entity.RecordEntity;
import com.izpa.jira.plugins.logic.Record;
import com.izpa.jira.plugins.logic.impl.RecordImpl;
import com.izpa.jira.plugins.rest.xml.Mapper;
import com.izpa.jira.plugins.rest.xml.XmlRecord;
import com.izpa.jira.plugins.rest.xml.XmlRecords;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * A resource of message.
 */
@Path("/records")
public class LaboratoryREST {
    private DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    @GET
    @AnonymousAllowed
    //@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({"application/json"})
    public Response getRecords(@QueryParam("id") String id) throws Exception {

        List<XmlRecord> xmlRecords = Lists.newArrayList();

        for (RecordEntity record : DAOFactory.getInstance().getRecordDAO().getRecords()) {
            xmlRecords.add(Mapper.toXmlRecord(record));
        }

        return Response.ok(new XmlRecords(xmlRecords.size(), xmlRecords)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @POST
    @AnonymousAllowed
    //@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({"application/json"})
    public Response addRecord(String request) throws Exception {

        JSONObject json = new JSONObject(request);
        String text = json.getString("text");
        String date = json.getString("date");
        Record record = new RecordImpl(text,format.parse(date));

        RecordEntity recordEntity = DAOFactory.getInstance().getRecordDAO().addRecord(record);

        return Response.ok(Mapper.toXmlRecord(recordEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }
}