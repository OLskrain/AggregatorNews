package com.olskrain.aggregatornews.presentation.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentation.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentation.ui.activity.ChannelDetailActivity;
import com.olskrain.aggregatornews.presentation.ui.fragment.ChannelDetailFragment;
import com.olskrain.aggregatornews.presentation.ui.view.item.IChannelListItemView;

import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class ChannelsListRVAdapter extends RecyclerView.Adapter<ChannelsListRVAdapter.ChannelsListViewHolder> {

    private IChannelListPresenter presenter;

    public ChannelsListRVAdapter(IChannelListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ChannelsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ChannelsListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_channel, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelsListViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        RxView.clicks(viewHolder.contextItemMenu).map(obj -> {
            viewHolder.currentPosition = position;
            return viewHolder;
        }).subscribe(presenter.getClickOnMenu());

        RxView.clicks(viewHolder.itemView).map(obj -> {
            viewHolder.currentPosition = position;
            return viewHolder;
        }).subscribe(presenter.getClickOnItem());

        viewHolder.position = position;
        presenter.bindView(viewHolder);
    }

    @Override
    public int getItemCount() {
        return presenter.getChannelCount();
    }

    public class ChannelsListViewHolder extends RecyclerView.ViewHolder implements IChannelListItemView {

        private int position = 0;
        private int currentPosition = 0;
        private final TextView channelTitle;
        private final TextView lastBuildDate;
        private final ImageView contextItemMenu;
        private final ImageView channelImage;

        ChannelsListViewHolder(View itemView) {
            super(itemView);
            channelTitle = itemView.findViewById(R.id.channel_title);
            lastBuildDate = itemView.findViewById(R.id.last_build_date);
            contextItemMenu = itemView.findViewById(R.id.context_item_menu);
            channelImage = itemView.findViewById(R.id.channel_imageView);
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
        public void setTitle(String title) {
            this.channelTitle.setText(title);
        }

        @Override
        public void setLastBuildDate(String lastBuildDate) {
            this.lastBuildDate.setText(lastBuildDate);
        }

    }
}
