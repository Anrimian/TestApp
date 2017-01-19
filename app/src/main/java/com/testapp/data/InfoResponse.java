package com.testapp.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created on 16.01.2017.
 */

public class InfoResponse {

    @Attribute(name = "date")
    private String date;

    @Element(name = "shop")
    private Info info;

    public String getDate() {
        return date;
    }

    public Info getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "date='" + date + '\'' +
                ", info=" + info +
                '}';
    }
}
