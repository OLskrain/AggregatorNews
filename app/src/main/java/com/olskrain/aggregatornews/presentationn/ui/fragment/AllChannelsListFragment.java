package com.olskrain.aggregatornews.presentationn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.AllChannelsListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.adapter.ChannelsListRVAdapter;
import com.olskrain.aggregatornews.presentationn.ui.view.IAllChannelsListView;

import java.util.Objects;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class AllChannelsListFragment extends Fragment implements IAllChannelsListView {

    public static AllChannelsListFragment getInstance(String arg) {
        AllChannelsListFragment fragment = new AllChannelsListFragment();
        Bundle args = new Bundle();
        args.putString("arg", arg);
        fragment.setArguments(args);
        return fragment;
    }

    public static final String CHANNEL_ONE = "https://news.yandex.ru/auto.rss";
    public static final String CHANNEL_TWO = "https://news.yandex.ru/business.rss";
    public static final String CHANNEL_THREE = "https://news.yandex.ru/world.rss";

    private Toolbar allChannelsListToolbar;
    private ProgressBar loadingProgressBar;
    private FloatingActionButton addNewChannel;
    private RecyclerView allChannelsListRecyclerView;

    private Button addChannelOne;
    private Button addChannelTwo;
    private Button addChannelThree;
    private Button deleteChannel;
    private Button deleteAllChannels;

    private ChannelsListRVAdapter allChannelsListRVAdapter;
    private AllChannelsListPresenter allChannelsListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_list, container, false);

        allChannelsListPresenter = new AllChannelsListPresenter(this);

        initUi(view);
        initOnClick();

        allChannelsListPresenter.refreshChannelsList();
        return view;
    }

    private void initUi(View view) {
        allChannelsListToolbar = view.findViewById(R.id.all_list_toolbar);
        if (allChannelsListToolbar != null) {
            ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(allChannelsListToolbar);
        }

        loadingProgressBar = view.findViewById(R.id.all_list_loading_progressBar);
        addNewChannel = view.findViewById(R.id.add_new_channel_fab);

        allChannelsListRecyclerView = view.findViewById(R.id.all_channels_list);
        allChannelsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allChannelsListRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        allChannelsListRVAdapter = new ChannelsListRVAdapter(allChannelsListPresenter.ChannelListPresenter);
        allChannelsListRecyclerView.setAdapter(allChannelsListRVAdapter);

        addChannelOne = view.findViewById(R.id.add_channel_one);
        addChannelTwo = view.findViewById(R.id.add_channel_two);
        addChannelThree = view.findViewById(R.id.add_channel_three);

        deleteChannel = view.findViewById(R.id.delete_channel_one);
        deleteAllChannels = view.findViewById(R.id.delete_all_channels);
    }

    private void initOnClick() {
        //addNewChannel.setOnClickListener(view -> allChannelsListPresenter.addNewChannel(CHANNEL_FOUR));

        addChannelOne.setOnClickListener(view -> allChannelsListPresenter.addNewChannel(CHANNEL_ONE));
        addChannelTwo.setOnClickListener(view -> allChannelsListPresenter.addNewChannel(CHANNEL_TWO));
        addChannelThree.setOnClickListener(view -> allChannelsListPresenter.addNewChannel(CHANNEL_THREE));

        deleteChannel.setOnClickListener(view -> allChannelsListPresenter.deleteChannel(0));
        deleteAllChannels.setOnClickListener(view -> allChannelsListPresenter.deleteAllChannels());
    }

    @Override
    public void onStop() {
        super.onStop();
        allChannelsListPresenter.putChannelsList();
    }

    @Override
    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayMessages(String message) {
        Timber.d("rty " + message);
    }

    @Override
    public void refreshChannelsListRVAdapter() {
        allChannelsListRVAdapter.notifyDataSetChanged();
    }
}
