package com.testapp.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.testapp.data.Offer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 18.01.2017.
 */

public class OffersDAO extends BaseDaoImpl<Offer, Integer> {

    public OffersDAO(ConnectionSource connectionSource, Class<Offer> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public void setOffers(List<Offer> offers) {
        try {
            TableUtils.clearTable(getConnectionSource(), Offer.class);
            for (Offer offer : offers) {
                create(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Offer> getOffers(int categoryId) throws SQLException {
        QueryBuilder<Offer, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq(Offer.CATEGORY_ID, categoryId);
        PreparedQuery<Offer> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }
}
