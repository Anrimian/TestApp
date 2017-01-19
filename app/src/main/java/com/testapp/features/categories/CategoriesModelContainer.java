package com.testapp.features.categories;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created on 17.01.2017.
 */

public class CategoriesModelContainer  extends Fragment {

    private static final String CATEGORIES_MODEL_TAG = "categories_model_tag";

    private CategoriesModel model;

    public static CategoriesModel getModel(Context ctx) {
        FragmentManager fm = ((FragmentActivity) ctx).getSupportFragmentManager();
        CategoriesModelContainer container = (CategoriesModelContainer) fm.findFragmentByTag(CATEGORIES_MODEL_TAG);
        if (container == null) {
            container = new CategoriesModelContainer();
            fm.beginTransaction().add(container, CATEGORIES_MODEL_TAG).commit();
        }
        if (container.model == null) {
            container.model = new CategoriesModel();
        }
        return container.model;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}