package com.izpa.jira.plugins.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class XmlRecords {
    @XmlElement
    public List<XmlRecord> records;

    public XmlRecords(){};

    public XmlRecords(List<XmlRecord> records) {
        this.records = records;
    }

}
