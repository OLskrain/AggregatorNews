package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.presentation.presenter.ChannelDetailFragmentPresenter;
import com.olskrain.aggregatornews.presentation.ui.adapter.NewsListRVAdapter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailFragmentView;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;


public class ChannelDetailFragment extends Fragment implements IChannelDetailFragmentView {

    public static ChannelDetailFragment getInstance(String arg, String urlChannel) {
        ChannelDetailFragment fragment = new ChannelDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString(arg, urlChannel);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static final String ARG_CDF_ID = "channelDetailId";
    private RecyclerView newsListRecyclerView;
    private NewsListRVAdapter newsListRVAdapter;
    private ChannelDetailFragmentPresenter channelDetailFragmentPresenter;
    private ProgressBar loadingProgressBar;
    private String urlChannel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_channel_detail, container, false);

        if (getArguments() != null && getArguments().containsKey(ARG_CDF_ID)) {
            urlChannel = getArguments().getString(ARG_CDF_ID);
            channelDetailFragmentPresenter = new ChannelDetailFragmentPresenter(this, AndroidSchedulers.mainThread());
        }

        initUi(rootView);
        channelDetailFragmentPresenter.refreshNewsList(urlChannel);
        return rootView;
    }

    private void initUi(View view) {
        loadingProgressBar = view.findViewById(R.id.channel_detail_loading_progressBar);

        newsListRecyclerView = view.findViewById(R.id.news_list);
        newsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsListRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        newsListRVAdapter = new NewsListRVAdapter(channelDetailFragmentPresenter.newsListPresenter);
        newsListRecyclerView.setAdapter(newsListRVAdapter);
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
    public void showBottomSheet(ItemNew itemNew) {

    }

    @Override
    public void showError(Command command) {
        Snackbar.make(Objects.requireNonNull(getView()), R.string.error_failed_update, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void refreshChannelsListRVAdapter() {
        newsListRVAdapter.notifyDataSetChanged();
    }
}
