package com.izpa.jira.plugins.rest;

import com.atlassian.jira.rest.v1.util.CacheControl;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.izpa.jira.plugins.dao.DAOFactory;
import com.izpa.jira.plugins.entity.RecordEntity;
import com.izpa.jira.plugins.logic.Record;
import com.izpa.jira.plugins.logic.impl.RecordImpl;
import com.izpa.jira.plugins.rest.xml.Mapper;
import com.izpa.jira.plugins.rest.xml.XmlRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A resource of message.
 */
@Path("records")
@Consumes ({ MediaType.APPLICATION_JSON })
@Produces ({ MediaType.APPLICATION_JSON })
public class LaboratoryREST {
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @GET
    @AnonymousAllowed
    public Response getRecords() throws Exception {
        RecordEntity[] records = DAOFactory.getInstance().getRecordDAO().getRecords();
        List<XmlRecord> result = new ArrayList<XmlRecord>();
        for (RecordEntity i : records){
            result.add(Mapper.toXmlRecord(i));
        }
        return Response.ok(result).cacheControl(CacheControl.NO_CACHE).build();
    }


    @GET
    @Path("{id}")
    @AnonymousAllowed
    public Response getRecord(@PathParam("id") final String idStr) throws Exception {
        long id = Long.parseLong(idStr);
        return Response.ok(Mapper.toXmlRecord(DAOFactory.getInstance().getRecordDAO().getRecord(id))).cacheControl(CacheControl.NO_CACHE).build();
    }

    @POST
    @AnonymousAllowed
    public Response addRecord(final XmlRecord xmlRecord) throws Exception {
        String text = xmlRecord.text;
        String date = xmlRecord.date;
        Record record = new RecordImpl(text,format.parse(date));
        RecordEntity recordEntity = DAOFactory.getInstance().getRecordDAO().addRecord(record);
        return Response.ok(Mapper.toXmlRecord(recordEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @DELETE
    @Path("{id}")
    @AnonymousAllowed
    public Response deleteRecord(@PathParam("id") final String idStr) throws Exception {
        long id = Long.parseLong(idStr);
        RecordEntity recordEntity = DAOFactory.getInstance().getRecordDAO().deleteRecord(id);
        return Response.ok(Mapper.toXmlRecord(recordEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @PUT
    @Path("{id}")
    @AnonymousAllowed
    public Response updateRecord(@PathParam("id") final String idStr, final XmlRecord xmlRecord) throws Exception {
        long id = Long.parseLong(idStr);
        Date date = xmlRecord.date==null?null:format.parse(xmlRecord.date);
        Record record = new RecordImpl(xmlRecord.text, date);
        RecordEntity recordEntity = DAOFactory.getInstance().getRecordDAO().updateRecord(id, record);
        return Response.ok(Mapper.toXmlRecord(recordEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }
}