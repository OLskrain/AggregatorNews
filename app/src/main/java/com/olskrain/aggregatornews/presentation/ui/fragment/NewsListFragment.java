package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.presentation.presenter.NewsListFragmentPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.INewsListFragmentPresenter;
import com.olskrain.aggregatornews.presentation.ui.activity.NewsDetailActivity;
import com.olskrain.aggregatornews.presentation.ui.adapter.NewsListRVAdapter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailFragmentView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


public class NewsListFragment extends Fragment implements IChannelDetailFragmentView {

    public static NewsListFragment getInstance(String arg, String urlChannel) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle arguments = new Bundle();
        arguments.putString(arg, urlChannel);
        fragment.setArguments(arguments);
        fragment.setRetainInstance(true);
        return fragment;
    }

    private static final String EXTRA_URL_NEW_KEY = "urlNews";
    public static final String ARG_CDF_ID = "channelDetailId";
    private RecyclerView newsListRecyclerView;
    private NewsListRVAdapter newsListRVAdapter;
    private NewsListFragmentPresenter newsListFragmentPresenter;
    private CompositeDisposable compositeDisposable;
    private ProgressBar loadingProgressBar;
    private String urlChannel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        compositeDisposable = new CompositeDisposable();
        newsListFragmentPresenter = FactoryProvider.providerPresenterFactory().createNewsListFragmentPresenter(this, compositeDisposable, AndroidSchedulers.mainThread());
        newsListFragmentPresenter.attachView();

        initUi(view);
        if (getArguments() != null && getArguments().containsKey(ARG_CDF_ID)) {
            urlChannel = getArguments().getString(ARG_CDF_ID);
        }
        if (urlChannel != null) {
            newsListFragmentPresenter.refreshNewsList(urlChannel);
        } else newsListFragmentPresenter.getUrlChannel();
    }

    private void initUi(final View view) {
        loadingProgressBar = view.findViewById(R.id.channel_detail_loading_progressBar);

        newsListRecyclerView = view.findViewById(R.id.news_list);
        newsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsListRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
        newsListRVAdapter = new NewsListRVAdapter(newsListFragmentPresenter.newsRecycleListPresenter);
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
    public void goToNewsDetailActivity(String urlNews) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra(EXTRA_URL_NEW_KEY, urlNews);
        Objects.requireNonNull(getContext()).startActivity(intent);
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

    @Override
    public void onStop() {
        super.onStop();
        newsListFragmentPresenter.saveCurrentUrlChannel();
        compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
