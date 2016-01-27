package com.izpa.jira.plugins.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlRecord {
    public XmlRecord(){}
    @XmlElement
    public long id;
    @XmlElement
    public String text;
    @XmlElement
    public String date;

}
