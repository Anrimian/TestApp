package com.testapp.features.offers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created on 17.01.2017.
 */

public class OffersModelContainer extends Fragment {

    private static final String OFFERS_MODEL_TAG = "offers_model_tag";

    private int categoryId;
    private OffersModel model;

    public static OffersModel getModel(Context ctx, int categoryId) {
        FragmentManager fm = ((FragmentActivity) ctx).getSupportFragmentManager();
        OffersModelContainer container = (OffersModelContainer) fm.findFragmentByTag(OFFERS_MODEL_TAG);
        if (container == null) {
            container = new OffersModelContainer();
            fm.beginTransaction().add(container, OFFERS_MODEL_TAG).commit();
        }
        return container.getModel(categoryId);
    }

    private OffersModel getModel(int categoryId) {
        if (model == null || this.categoryId != categoryId) {
            this.categoryId = categoryId;
            model = new OffersModel(categoryId);
        }
        return model;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}