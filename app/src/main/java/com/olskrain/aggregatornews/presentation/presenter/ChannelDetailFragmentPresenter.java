package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.domain.usecase.NewsListUseCase;
import com.olskrain.aggregatornews.presentation.presenter.list.INewsListPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailFragmentView;
import com.olskrain.aggregatornews.presentation.ui.view.item.INewsListItemView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class ChannelDetailFragmentPresenter {

    public class NewsListPresenter implements INewsListPresenter {
        @Override
        public void bindView(INewsListItemView rowView) {
            String newTitle = newsListLocal.get(rowView.getPos()).getTitle();
            String pubDate = newsListLocal.get(rowView.getPos()).getPubDate();
            String description = newsListLocal.get(rowView.getPos()).getDescription();
            rowView.setTitle(newTitle);
            rowView.setLastBuildDate(pubDate);
            rowView.setDescription(description);
        }
        @Override
        public int getNewCount() {
            return newsListLocal == null ? 0 : newsListLocal.size();
        }
    }

    public NewsListPresenter newsListPresenter;
    private IChannelDetailFragmentView channelDetailFragmentView;
    private NewsListUseCase newsListUseCase;
    private Scheduler mainThreadScheduler;
    private Disposable disposable;
    private List<ItemNew> newsListLocal;

    public ChannelDetailFragmentPresenter(IChannelDetailFragmentView view, Scheduler mainThreadScheduler) {
        this.channelDetailFragmentView = view;
        this.mainThreadScheduler = mainThreadScheduler;
        this.newsListPresenter = new NewsListPresenter();
        this.newsListUseCase = new NewsListUseCase();
    }

    public void refreshNewsList(String urlChannel) {
        channelDetailFragmentView.showLoading();
        Single<List<ItemNew>> responseRepository = newsListUseCase.refreshNewsList(urlChannel);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(itemNews -> {
                    newsListLocal = itemNews;
                    channelDetailFragmentView.hideLoading();
                    channelDetailFragmentView.refreshChannelsListRVAdapter();
                }, throwable -> {
                    channelDetailFragmentView.hideLoading();
                    channelDetailFragmentView.showError(Command.REFRESH_NEWS);
                });

       App.getInstance().getCompositeDisposable().add(disposable);
    }
}

