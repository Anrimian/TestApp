package com.testapp.database.rx;

import com.testapp.data.Category;
import com.testapp.database.HelperFactory;
import com.testapp.utls.AppExecutors;

import java.sql.SQLException;
import java.util.List;

import rx.Observable;

import static com.testapp.database.HelperFactory.getHelper;

/**
 * Created on 18.01.2017.
 */

public class CategoriesDaoRxWrapper {

    public static void setCategories(List<Category> categories) {
        Observable.create(subscriber -> {
            try {
                getHelper().getCategoriesDAO().setCategories(categories);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).subscribeOn(AppExecutors.DB_SCHEDULER)
                .subscribe();
    }

    public static Observable<List<Category>> getCategories() {
        return getCategoriesObservable().subscribeOn(AppExecutors.DB_SCHEDULER);
    }

    private static Observable<List<Category>> getCategoriesObservable() {
        return Observable.create(subscriber -> {
            try {
                List<Category> categories = HelperFactory.getHelper().getCategoriesDAO().getAllCategories();
                subscriber.onNext(categories);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
