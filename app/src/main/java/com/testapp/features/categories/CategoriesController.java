package com.testapp.features.categories;

import com.testapp.tools.views.StateObserver;
import com.testapp.tools.views.ViewActions;

/**
 * Created on 17.01.2017.
 */

public class CategoriesController implements StateObserver, ViewActions {

    private CategoryListAdapter listAdapter;
    private CategoriesView categoriesView;
    private CategoriesModel categoriesModel;

    public CategoriesController(CategoriesModel categoriesModel, CategoriesView categoriesView, CategoryListAdapter listAdapter) {
        this.categoriesModel = categoriesModel;
        this.categoriesView = categoriesView;
        this.listAdapter = listAdapter;
    }

    public void bind() {
        listAdapter.setData(categoriesModel.getCategories());
        categoriesView.bind(this, listAdapter, categoriesModel.getCurrentPosition());
        categoriesModel.registerStateObserver(this);
    }

    public void unbind() {
        categoriesModel.setCurrentPosition(categoriesView.getCurrentPosition());
        categoriesModel.unregisterStateObserver(this);
    }

    @Override
    public void onComplete() {
        categoriesView.showList();
    }

    @Override
    public void onProgress() {
        categoriesView.showProgress();
    }

    @Override
    public void onError() {
        categoriesView.showError();
    }

    @Override
    public void updateList() {
        listAdapter.setData(categoriesModel.getCategories());
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        categoriesModel.startLoading();
    }
}
