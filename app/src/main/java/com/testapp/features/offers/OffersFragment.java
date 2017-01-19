package com.testapp.features.offers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapp.R;
import com.testapp.data.Category;
import com.testapp.data.Offer;
import com.testapp.features.offers.fulloffers.FullOfferFragment;
import com.testapp.tools.fragments.BackStackFragment;

/**
 * Created on 17.01.2017.
 */

public class OffersFragment extends BackStackFragment {

    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";

    private OffersController offersController;
    private OffersView offersView;
    private OffersModel offersModel;
    private OfferListAdapter listAdapter;

    public static OffersFragment newInstance(Category category) {
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID, category.getId());
        args.putString(CATEGORY_NAME, category.getName());
        OffersFragment fragment = new OffersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.custom_recycler_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getCategoryName());
        offersView = new OffersView(view);

        listAdapter = new OfferListAdapter();
        listAdapter.setOnItemClickListener(this::startFullOfferFragment);
        offersModel = OffersModelContainer.getModel(getContext(), getCategoryId());

        offersController = new OffersController(offersModel, offersView, listAdapter);
        offersController.bind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        offersController.unbind();
    }

    private int getCategoryId() {
        return getArguments().getInt(CATEGORY_ID);
    }

    private String getCategoryName() {
        return getArguments().getString(CATEGORY_NAME);
    }

    private void startFullOfferFragment(Offer offer) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FullOfferFragment.newInstance(offer))
                .addToBackStack(FullOfferFragment.class.getName())
                .commit();
    }
}
