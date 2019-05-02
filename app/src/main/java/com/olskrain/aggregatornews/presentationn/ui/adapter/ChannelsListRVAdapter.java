package com.olskrain.aggregatornews.presentationn.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.item.IChannelListItemView;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class ChannelsListRVAdapter extends RecyclerView.Adapter<ChannelsListRVAdapter.ViewHolder> {

    private IChannelListPresenter presenter;

    public ChannelsListRVAdapter(IChannelListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ChannelsListRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_channel, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelsListRVAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        viewHolder.position = position;
        presenter.bindView(viewHolder);
    }

    @Override
    public int getItemCount() {
        return presenter.getChannelCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements IChannelListItemView {

        int position = 0;
        TextView channel;

        ViewHolder(View itemView) {
            super(itemView);
            channel = itemView.findViewById(R.id.channel_name);

        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setTitle(String title) {
            channel.setText(title);
        }
    }
}
