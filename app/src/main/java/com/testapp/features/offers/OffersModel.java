package com.testapp.features.offers;

import android.util.Log;

import com.testapp.data.Info;
import com.testapp.data.InfoResponse;
import com.testapp.data.Offer;
import com.testapp.database.rx.CategoriesDaoRxWrapper;
import com.testapp.database.rx.OffersDaoRxWrapper;
import com.testapp.tools.views.State;
import com.testapp.tools.views.StateObservable;
import com.testapp.tools.views.StateObserver;
import com.testapp.utls.android.ListPosition;
import com.testapp.utls.network.Utils;
import com.testapp.utls.network.api.FarforApiWrapper;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 17.01.2017.
 */

public class OffersModel {
    private List<Offer> offers = new ArrayList<>();

    private ListPosition currentPosition;

    private State state = State.COMPLETE;
    private StateObservable stateObservable = new StateObservable();

    private int categoryId;

    public OffersModel(int categoryId) {
        this.categoryId = categoryId;
        init();
    }

    private void init() {
        showState(State.PROGRESS);
        OffersDaoRxWrapper.getOffers(categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newOffers -> {
                    if (newOffers.isEmpty()) {
                        Log.d(getClass().getSimpleName(), "offers is empty, start loading");
                        startLoading();
                    } else {
                        offers = newOffers;
                        stateObservable.updateList();
                        showState(State.COMPLETE);
                    }
                });
    }

    public void startLoading() {
        showState(State.PROGRESS);
        FarforApiWrapper.getFarforApi()
                .getData(Utils.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(InfoResponse::getInfo)
                .doOnNext(info -> CategoriesDaoRxWrapper.setCategories(info.getCategories()))
                .map(Info::getOffers)
                .doOnNext(OffersDaoRxWrapper::setOffers)
                .map(this::filterOffers)
                .subscribe(newOffers -> {
                    offers = newOffers;
                    stateObservable.updateList();
                    showState(State.COMPLETE);
                }, throwable -> {
                    showState(State.ERROR);
                });
    }

    private List<Offer> filterOffers(List<Offer> offers) {
        List<Offer> filtered = new ArrayList<>();
        for (Offer offer : offers) {
            if (offer.getCategoryId() == categoryId) {
                filtered.add(offer);
            }
        }
        return filtered;
    }

    public void registerStateObserver(StateObserver stateObserver) {
        state.showState(stateObserver);
        stateObservable.registerObserver(stateObserver);
    }

    public void unregisterStateObserver(StateObserver stateObserver) {
        stateObservable.unregisterObserver(stateObserver);
    }

    private void showState(State state) {
        this.state = state;
        stateObservable.showState(state);
    }

    public ListPosition getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(ListPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
