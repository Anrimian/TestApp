package com.testapp.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created on 17.01.2017.
 */

@Root(name = "category")
@DatabaseTable(tableName = "food_categories")
public class Category {

    @DatabaseField(generatedId = true)
    private int id;

    @Attribute(name = "id")
    @DatabaseField
    private int categoryId;

    @Text
    @DatabaseField
    private String name;

    public int getId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        return categoryId == category.categoryId;

    }

    @Override
    public int hashCode() {
        return categoryId;
    }
}
