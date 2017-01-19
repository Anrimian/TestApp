package com.testapp.features.categories;

import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.testapp.R;
import com.testapp.data.Category;
import com.testapp.tools.views.OnItemClickListener;

/**
 * Created on 17.01.2017.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private static final SparseIntArray picturesMap = new SparseIntArray();

    static {
        picturesMap.put(1, R.drawable.clock_icon);
        picturesMap.put(2, R.drawable.gold_bar_icon);
        picturesMap.put(3, R.drawable.hot_icon);
    }

    private Category category;

    private TextView tvName;
    private ImageView imageView;

    public CategoryViewHolder(View itemView, OnItemClickListener<Category> itemClickListener) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
        itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(category);
            }
        });
    }

    public void bindView(Category category) {
        this.category = category;
        tvName.setText(category.getName());
        Glide.with(imageView.getContext())
                .load(getPictureId(category.getId()))
                .into(imageView);
    }

    private int getPictureId(int categoryId) {
        int pictureId = picturesMap.get(categoryId);
        if (pictureId == 0) {
            pictureId = R.mipmap.ic_launcher;
        }
        return pictureId;
    }
}
