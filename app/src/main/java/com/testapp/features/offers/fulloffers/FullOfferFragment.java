package com.testapp.features.offers.fulloffers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapp.R;
import com.testapp.data.Offer;
import com.testapp.tools.fragments.BackStackFragment;

/**
 * Created on 18.01.2017.
 */

public class FullOfferFragment extends BackStackFragment {

    private static final String OFFER = "offer";

    public static FullOfferFragment newInstance(Offer offer) {
        Bundle args = new Bundle();
        args.putSerializable(OFFER, offer);
        FullOfferFragment fragment = new FullOfferFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.full_offer_layout, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("");

        FullOfferView offerView = new FullOfferView(view);
        Offer offer = (Offer) getArguments().getSerializable(OFFER);
        offerView.bindView(offer);
    }
}
