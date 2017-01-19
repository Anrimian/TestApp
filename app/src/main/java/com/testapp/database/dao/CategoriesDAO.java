package com.testapp.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.testapp.data.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 18.01.2017.
 */

public class CategoriesDAO extends BaseDaoImpl<Category, Integer> {

    public CategoriesDAO(ConnectionSource connectionSource, Class<Category> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Category> getAllCategories() throws SQLException {
        return this.queryForAll();
    }

    public void setCategories(List<Category> categories) {
        try {
            TableUtils.clearTable(getConnectionSource(), Category.class);
            for (Category category : categories) {
                create(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
