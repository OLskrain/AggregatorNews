package com.olskrain.aggregatornews.presentation.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentation.presenter.intefaceRecycleList.INewsListPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.item.INewsListItemView;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class NewsListRVAdapter extends RecyclerView.Adapter<NewsListRVAdapter.NewListViewHolder> {

    private final INewsListPresenter presenter;

    public NewsListRVAdapter(final INewsListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public NewListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new NewListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_new, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewListViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        RxView.clicks(viewHolder.itemView).map(obj -> {
            viewHolder.currentPosition = position;
            return viewHolder;
        }).subscribe(presenter.getClickOnItem());
        viewHolder.position = position;
        presenter.bindView(viewHolder);
    }

    @Override
    public int getItemCount() {
        return presenter.getNewCount();
    }

    public class NewListViewHolder extends RecyclerView.ViewHolder implements INewsListItemView {

        private int position = 0;
        private final TextView newTitle;
        private final TextView pubDate;
        private int currentPosition = 0;

        NewListViewHolder(View itemView) {
            super(itemView);

            newTitle = itemView.findViewById(R.id.new_title);
            pubDate = itemView.findViewById(R.id.new_pubDate);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public int getCurrentPosition() {
            return currentPosition;
        }

        @Override
        public void setTitle(final String title) {
            newTitle.setText(title);
        }

        @Override
        public void setLastBuildDate(final String lastBuildDate) {
            pubDate.setText(lastBuildDate);
        }

    }
}
