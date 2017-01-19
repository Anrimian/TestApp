package com.testapp.features.categories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapp.R;
import com.testapp.data.Category;
import com.testapp.tools.views.OnItemClickListener;

import java.util.List;

/**
 * Created on 17.01.2017.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private List<Category> data;

    private OnItemClickListener<Category> onItemClickListener;

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        return new CategoryViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public void setData(List<Category> data) {
        if (data != null) {
            this.data = data;
        }
    }

    public void setOnItemClickListener(OnItemClickListener<Category> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
