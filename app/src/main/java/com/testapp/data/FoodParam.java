package com.testapp.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.io.Serializable;

/**
 * Created on 17.01.2017.
 */
@Root(name = "param")
public class FoodParam implements Serializable {

    @Attribute(name = "name")
    private String name;

    @Text
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FoodParam{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
