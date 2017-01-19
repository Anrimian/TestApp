package com.testapp.data;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created on 17.01.2017.
 */

public class Info {

    @ElementList(name = "categories")
    private List<Category> categories;

    @ElementList(name = "offers")
    private List<Offer> offers;

    public List<Category> getCategories() {
        return categories;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    @Override
    public String toString() {
        return "Info{" +
                "categories=" + categories +
                ", offers=" + offers +
                '}';
    }
}
