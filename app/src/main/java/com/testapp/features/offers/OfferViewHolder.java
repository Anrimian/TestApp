package com.testapp.features.offers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapp.R;
import com.testapp.data.FoodParam;
import com.testapp.data.Offer;
import com.testapp.tools.image.MyGlideImageLoader;
import com.testapp.tools.views.OnItemClickListener;

import java.util.Collection;

/**
 * Created on 17.01.2017.
 */

public class OfferViewHolder extends RecyclerView.ViewHolder {

    private Offer offer;

    private TextView tvName;
    private ImageView imageView;
    private TextView tvPrice;
    private TextView tvWeight;

    public OfferViewHolder(View itemView, OnItemClickListener<Offer> itemClickListener) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        tvWeight = (TextView) itemView.findViewById(R.id.tv_weight);
        itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(offer);
            }
        });
    }

    public void bindView(Offer offer) {
        this.offer = offer;
        tvName.setText(offer.getName());
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
