package com.testapp.features.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapp.R;
import com.testapp.features.offers.OffersFragment;
import com.testapp.tools.fragments.BackStackRootFragment;
import com.testapp.data.Category;

/**
 * Created on 17.01.2017.
 */

public class CategoriesFragment extends BackStackRootFragment {

    private CategoriesController categoriesController;
    private CategoriesView categoriesView;
    private CategoriesModel categoriesModel;
    private CategoryListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.custom_recycler_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.categories);
        categoriesView = new CategoriesView(view);

        listAdapter = new CategoryListAdapter();
        listAdapter.setOnItemClickListener(this::startOffersFragment);
        categoriesModel = CategoriesModelContainer.getModel(getContext());

        categoriesController = new CategoriesController(categoriesModel, categoriesView, listAdapter);
        categoriesController.bind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoriesController.unbind();
    }

    private void startOffersFragment(Category category) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, OffersFragment.newInstance(category))
                .addToBackStack(OffersFragment.class.getName())
                .commit();
    }
}
