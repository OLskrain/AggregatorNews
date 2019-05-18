package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.domain.usecase.NewsListUseCase;
import com.olskrain.aggregatornews.presentation.presenter.list.INewsListPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailFragmentView;
import com.olskrain.aggregatornews.presentation.ui.view.item.INewsListItemView;

import java.util.List;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class ChannelDetailFragmentPresenter implements NewsListUseCase.IResponseDBCallback {

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
    private List<ItemNew> newsListLocal;

    public ChannelDetailFragmentPresenter(IChannelDetailFragmentView view, int channelPosition) {
        this.channelDetailFragmentView = view;
        this.newsListPresenter = new NewsListPresenter();
        this.newsListUseCase = new NewsListUseCase(channelPosition);
        this.newsListUseCase.registerCallBack(this);
    }

    public void refreshNewsList() {
        channelDetailFragmentView.showLoading();
        newsListUseCase.refreshNewsList();
    }

    @Override
    public void onMessageStatus(String message) {

    }

    @Override
    public void onNewsList(List<ItemNew> newsList) {
        newsListLocal = newsList;
        channelDetailFragmentView.refreshChannelsListRVAdapter();
        channelDetailFragmentView.hideLoading();
    }
}

