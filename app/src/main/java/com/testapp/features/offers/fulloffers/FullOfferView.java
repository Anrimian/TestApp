package com.testapp.features.offers.fulloffers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapp.R;
import com.testapp.data.FoodParam;
import com.testapp.data.Offer;
import com.testapp.tools.image.MyGlideImageLoader;

import java.util.Collection;

/**
 * Created on 18.01.2017.
 */

public class FullOfferView {

    private Offer offer;

    private ImageView imageView;
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvWeight;

    public FullOfferView(View view) {
        imageView = (ImageView) view.findViewById(R.id.image_view);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvDescription = (TextView) view.findViewById(R.id.tv_description);
        tvPrice = (TextView) view.findViewById(R.id.tv_price);
        tvWeight = (TextView) view.findViewById(R.id.tv_weight);
    }

    public void bindView(Offer offer) {
        this.offer = offer;
        tvName.setText(offer.getName());
        tvDescription.setText(offer.getDescription());
        MyGlideImageLoader.displayImage(imageView, offer.getPicture());
        tvPrice.setText(offer.getPrice());
        showWeight();
    }

    private void showWeight() {
        Collection<FoodParam> foodParams = offer.getFoodParamList();
        String weight = null;
        if (foodParams != null) {
            for (FoodParam foodParam : foodParams) {
                String name = foodParam.getName();
                if (name.equals("Вес")) {
                    weight = foodParam.getValue();
                }
            }
        }
        if (weight != null) {
            tvWeight.setText(weight);
            tvWeight.setVisibility(View.VISIBLE);
        } else {
            tvWeight.setVisibility(View.GONE);
        }
    }
}
