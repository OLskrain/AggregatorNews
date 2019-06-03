package com.olskrain.aggregatornews.presentation.presenter;

import android.annotation.SuppressLint;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsListUseCase;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.INewsListFragmentPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfaceRecycleListPresenter.INewsListRVPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailFragmentView;
import com.olskrain.aggregatornews.presentation.ui.view.item.INewsListItemView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class NewsListFragmentPresenter implements INewsListFragmentPresenter {
    public class NewsRecycleListRVPresenter implements INewsListRVPresenter {

        private final PublishSubject<INewsListItemView> clickItem = PublishSubject.create();

        @Override
        public PublishSubject<INewsListItemView> getClickOnItem() {
            return clickItem;
        }

        @Override
        public void bindView(final INewsListItemView rowView) {
            String newTitle = newsListLocal.get(rowView.getPos()).getTitle();
            String pubDate = newsListLocal.get(rowView.getPos()).getPubDate();

            rowView.setTitle(newTitle);
            rowView.setLastBuildDate(pubDate);
        }

        @Override
        public int getNewCount() {
            return newsListLocal == null ? 0 : newsListLocal.size();
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void attachView() {
        newsRecycleListPresenter.clickItem.subscribe(iChannelListItemView ->
                channelDetailFragmentView.goToNewsDetailActivity(newsListLocal.get(iChannelListItemView.getCurrentPosition()).getLink()));
    }

    public NewsRecycleListRVPresenter newsRecycleListPresenter;
    private final IChannelDetailFragmentView channelDetailFragmentView;
    private final INewsListUseCase newsListUseCase = FactoryProvider.providerUseCaseFactory().createNewsListUseCase();
    private final Scheduler mainThreadScheduler;
    private final CompositeDisposable compositeDisposable;
    private Disposable disposable;
    private List<ItemNew> newsListLocal;
    private String urlChannelLocal;

    public NewsListFragmentPresenter(final IChannelDetailFragmentView view, final CompositeDisposable compositeDisposable, final Scheduler mainThreadScheduler) {
        this.channelDetailFragmentView = view;
        this.compositeDisposable = compositeDisposable;
        this.mainThreadScheduler = mainThreadScheduler;
        this.newsRecycleListPresenter = new NewsRecycleListRVPresenter();
    }

    @Override
    public void refreshNewsList(final String urlChannel) {
        urlChannelLocal = urlChannel;
        channelDetailFragmentView.showLoading();
        Single<List<ItemNew>> responseRepository = newsListUseCase.refreshNewsList(urlChannel);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(newsList -> {
                    newsListLocal = newsList;
                    channelDetailFragmentView.hideLoading();
                    channelDetailFragmentView.refreshChannelsListRVAdapter();
                }, throwable -> {
                    channelDetailFragmentView.hideLoading();
                    channelDetailFragmentView.showError(Command.REFRESH_NEWS);
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void saveCurrentUrlChannel() {
        newsListUseCase.saveUrlChannel(urlChannelLocal);
    }

    @Override
    public void getUrlChannel() {
        channelDetailFragmentView.showLoading();
        Single<String> responseRepository = newsListUseCase.getUrlChannel();

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(urlChannel -> {
                    urlChannelLocal = urlChannel;
                    refreshNewsList(urlChannel);
                }, throwable -> {
                    channelDetailFragmentView.hideLoading();
                    channelDetailFragmentView.showError(Command.REFRESH_NEWS);
                });
    }
}

