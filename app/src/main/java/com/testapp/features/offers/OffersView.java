package com.testapp.features.offers;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.testapp.R;
import com.testapp.tools.views.ViewActions;
import com.testapp.utls.android.ListPosition;

/**
 * Created on 17.01.2017.
 */

public class OffersView {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView tvError;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    private ViewActions viewActions;

    public OffersView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        tvError = (TextView) view.findViewById(R.id.tv_error);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> viewActions.onRefresh());
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    }

    public void bind(ViewActions viewActions, OfferListAdapter listAdapter, ListPosition listPosition) {
        this.viewActions = viewActions;
        recyclerView.setAdapter(listAdapter);
        setCurrentPosition(listPosition);
    }

    public void showProgress() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setEnabled(false);
        }
        if (isListEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void showError() {
        enableSwipeRefreshLayout();
        int errorId = R.string.loading_error;
        if (!isListEmpty()) {
            Toast.makeText(getContext(), errorId, Toast.LENGTH_SHORT).show();
            return;
        }
        recyclerView.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(errorId);
        progressBar.setVisibility(View.GONE);
    }

    public void showList() {
        enableSwipeRefreshLayout();
        recyclerView.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    private void enableSwipeRefreshLayout() {
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setRefreshing(false);
    }

    public ListPosition getCurrentPosition() {
        int index = linearLayoutManager.findFirstVisibleItemPosition();
        View v = linearLayoutManager.getChildAt(0);
        int top = (v == null) ? 0 : v.getTop();
        return new ListPosition(index, top);
    }

    public void setCurrentPosition(ListPosition listPosition) {
        if (listPosition != null) {
            int index = listPosition.getIndex();
            int top = listPosition.getTop();
            linearLayoutManager.scrollToPositionWithOffset(index, top);
        }
    }

    private boolean isListEmpty() {
        return recyclerView.getAdapter().getItemCount() == 0;
    }

    private Context getContext() {
        return recyclerView.getContext();
    }
}
