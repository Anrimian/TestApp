package com.testapp.features.categories;

import com.testapp.data.Category;
import com.testapp.data.Info;
import com.testapp.data.InfoResponse;
import com.testapp.database.rx.CategoriesDaoRxWrapper;
import com.testapp.database.rx.OffersDaoRxWrapper;
import com.testapp.tools.views.State;
import com.testapp.tools.views.StateObservable;
import com.testapp.tools.views.StateObserver;
import com.testapp.utls.android.ListPosition;
import com.testapp.utls.network.Utils;
import com.testapp.utls.network.api.FarforApiWrapper;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 17.01.2017.
 */

public class CategoriesModel {
    private List<Category> categories;

    private ListPosition currentPosition;

    private State state = State.COMPLETE;
    private StateObservable stateObservable = new StateObservable();

    public CategoriesModel() {
        init();
    }

    private void init() {
        showState(State.PROGRESS);
        CategoriesDaoRxWrapper.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newCategories -> {
                    if (newCategories.isEmpty()) {
                        startLoading();
                    } else {
                        categories = newCategories;
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
                .map(InfoResponse::getInfo)
                .doOnNext(info -> OffersDaoRxWrapper.setOffers(info.getOffers()))
                .map(Info::getCategories)
                .doOnNext(CategoriesDaoRxWrapper::setCategories)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newCategories -> {
                    categories = newCategories;
                    stateObservable.updateList();
                    showState(State.COMPLETE);
                }, throwable -> {
                    showState(State.ERROR);
                });
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

    public List<Category> getCategories() {
        return categories;
    }
}
