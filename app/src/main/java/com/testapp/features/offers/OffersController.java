package com.testapp.features.offers;

import com.testapp.tools.views.StateObserver;
import com.testapp.tools.views.ViewActions;

/**
 * Created on 17.01.2017.
 */

public class OffersController implements StateObserver, ViewActions {

    private OfferListAdapter listAdapter;
    private OffersView offersView;
    private OffersModel offersModel;

    public OffersController(OffersModel offersModel, OffersView offersView, OfferListAdapter listAdapter) {
        this.offersModel = offersModel;
        this.offersView = offersView;
        this.listAdapter = listAdapter;
    }

    public void bind() {
        listAdapter.setData(offersModel.getOffers());
        offersView.bind(this, listAdapter, offersModel.getCurrentPosition());
        offersModel.registerStateObserver(this);
    }

    public void unbind() {
        offersModel.setCurrentPosition(offersView.getCurrentPosition());
        offersModel.unregisterStateObserver(this);
    }

    @Override
    public void onComplete() {
        offersView.showList();
    }

    @Override
    public void onProgress() {
        offersView.showProgress();
    }

    @Override
    public void onError() {
        offersView.showError();
    }

    @Override
    public void updateList() {
        listAdapter.setData(offersModel.getOffers());
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        offersModel.startLoading();
    }
}
