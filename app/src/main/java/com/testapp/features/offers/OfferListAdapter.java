package com.testapp.features.offers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapp.R;
import com.testapp.data.Offer;
import com.testapp.tools.views.OnItemClickListener;

import java.util.List;

/**
 * Created on 17.01.2017.
 */

public class OfferListAdapter extends RecyclerView.Adapter<OfferViewHolder> {
    private List<Offer> data;

    private OnItemClickListener<Offer> onItemClickListener;

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_view, parent, false);
        return new OfferViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public void setData(List<Offer> data) {
        if (data != null) {
            this.data = data;
        }
    }

    public void setOnItemClickListener(OnItemClickListener<Offer> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
