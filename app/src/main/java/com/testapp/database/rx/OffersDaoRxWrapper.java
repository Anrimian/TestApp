package com.testapp.database.rx;

import com.testapp.data.Offer;
import com.testapp.database.HelperFactory;
import com.testapp.utls.AppExecutors;

import java.sql.SQLException;
import java.util.List;

import rx.Observable;

import static com.testapp.database.HelperFactory.getHelper;

/**
 * Created on 18.01.2017.
 */

public class OffersDaoRxWrapper {

    public static void setOffers(List<Offer> offers) {
        Observable.create(subscriber -> {
            try {
                getHelper().getOffersDao().setOffers(offers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).subscribeOn(AppExecutors.DB_SCHEDULER)
                .subscribe();
    }

    public static Observable<List<Offer>> getOffers(int categoryId) {
        return getOffersObservable(categoryId).subscribeOn(AppExecutors.DB_SCHEDULER);
    }

    private static Observable<List<Offer>> getOffersObservable(int categoryId) {
        return Observable.create(subscriber -> {
            try {
                List<Offer> categories = HelperFactory.getHelper().getOffersDao().getOffers(categoryId);
                subscriber.onNext(categories);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
