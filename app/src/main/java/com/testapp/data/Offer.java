package com.testapp.data;

import android.support.annotation.Nullable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 17.01.2017.
 */

@Root(name = "offer")
@DatabaseTable(tableName = "offers")
public class Offer implements Serializable{

    public static final String CATEGORY_ID = "category_id";

    @DatabaseField(generatedId = true)
    private int dbId;

    @Attribute(name = "id")
    @DatabaseField
    private int id;

    @Element(name = "url")
    @DatabaseField
    private String url;

    @Element(name = "name")
    @DatabaseField
    private String name;

    @Element(name = "price")
    @DatabaseField
    private String price;

    @Element(name = "description", required = false)
    @DatabaseField
    private String description;

    @Element(name = "picture", required = false)
    @DatabaseField
    private String picture;

    @Element(name = "categoryId")
    @DatabaseField(columnName = CATEGORY_ID)
    private int categoryId;

    @ElementList(inline = true, required = false)
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<FoodParam> foodParamList;

    public int getCategoryId() {
        return categoryId;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public Collection<FoodParam> getFoodParamList() {
        return foodParamList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public String getPicture() {
        return picture;
    }

    public String getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "categoryId=" + categoryId +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", foodParamList=" + foodParamList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        return id == offer.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
