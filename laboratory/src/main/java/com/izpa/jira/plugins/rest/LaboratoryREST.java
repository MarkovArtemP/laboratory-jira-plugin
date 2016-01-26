package com.izpa.jira.plugins.rest;

import com.atlassian.jira.rest.v1.util.CacheControl;
import com.atlassian.jira.util.json.JSONException;
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
@Path("records")
public class LaboratoryREST {
    private DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    @GET
    @AnonymousAllowed
    @Produces({"application/json"})
    public Response getRecords() throws Exception {

        List<XmlRecord> xmlRecords = Lists.newArrayList();

        for (RecordEntity record : DAOFactory.getInstance().getRecordDAO().getRecords()) {
            xmlRecords.add(Mapper.toXmlRecord(record));
        }

        return Response.ok(new XmlRecords(xmlRecords)).cacheControl(CacheControl.NO_CACHE).build();
    }


    @GET
    @Path("{id}")
    @AnonymousAllowed
    @Produces({"application/json"})
    public Response getRecord(@PathParam("id") String idStr) throws Exception {
        long id = Long.parseLong(idStr);

        return Response.ok(Mapper.toXmlRecord(DAOFactory.getInstance().getRecordDAO().getRecord(id))).cacheControl(CacheControl.NO_CACHE).build();
    }

    @POST
    @AnonymousAllowed
    @Produces({"application/json"})
    public Response addRecord(String request) throws Exception {

        JSONObject json = new JSONObject(request);
        String text = json.getString("text");
        String date = json.getString("date");
        Record record = new RecordImpl(text,format.parse(date));

        RecordEntity recordEntity = DAOFactory.getInstance().getRecordDAO().addRecord(record);

        return Response.ok(Mapper.toXmlRecord(recordEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response deleteRule(@PathParam("id") String idStr) throws Exception {

        long id = Long.parseLong(idStr);

        RecordEntity recordEntity = DAOFactory.getInstance().getRecordDAO().deleteRecord(id);

        return Response.ok(Mapper.toXmlRecord(recordEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @PUT
    @Path("{id}")
    @Produces({"application/json"})
    public Response updateRule(@PathParam("id") String idStr, String request) throws Exception {

        long id = Long.parseLong(idStr);

        JSONObject json = new JSONObject(request);

        String text;
        try {
            text = json.getString("text");
        } catch (JSONException ex) {
            text = null;
        }

        String date;
        try {
            date = json.getString("date");
        } catch (JSONException ex) {
            date = null;
        }
        Record record = new RecordImpl(text, format.parse(date));

        RecordEntity recordEntity = DAOFactory.getInstance().getRecordDAO().updateRecord(id, record);

        return Response.ok(Mapper.toXmlRecord(recordEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }
}