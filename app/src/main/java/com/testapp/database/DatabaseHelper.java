package com.testapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.testapp.data.Category;
import com.testapp.data.Offer;
import com.testapp.database.dao.CategoriesDAO;
import com.testapp.database.dao.OffersDAO;

import java.sql.SQLException;

/**
 * Created on 18.01.2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME ="testapp.db";

    private static final int DATABASE_VERSION = 9;

    private CategoriesDAO categoriesDAO;
    private OffersDAO offersDAO;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Offer.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer, int newVer){
        try {
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, Offer.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver " + oldVer);
            throw new RuntimeException(e);
        }
    }

    public CategoriesDAO getCategoriesDAO() throws SQLException {
        if (categoriesDAO == null){
            categoriesDAO = new CategoriesDAO(getConnectionSource(), Category.class);
        }
        return categoriesDAO;
    }

    public OffersDAO getOffersDao() throws SQLException {
        if (offersDAO == null) {
            offersDAO = new OffersDAO(getConnectionSource(), Offer.class);
        }
        return offersDAO;
    }

    @Override
    public void close(){
        super.close();
        categoriesDAO = null;
        offersDAO = null;
    }
}

