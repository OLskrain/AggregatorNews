package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.domain.usecase.NewsListUseCase;
import com.olskrain.aggregatornews.presentationn.presenter.list.INewsListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.IChannelDetailFragmentView;
import com.olskrain.aggregatornews.presentationn.ui.view.item.INewsListItemView;

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
    private IChannelDetailFragmentView iChannelDetailFragmentView;
    private NewsListUseCase newsListUseCase;
    private List<ItemNew> newsListLocal;

    public ChannelDetailFragmentPresenter(IChannelDetailFragmentView view, int channelPosition) {
        this.iChannelDetailFragmentView = view;
        this.newsListPresenter = new NewsListPresenter();
        this.newsListUseCase = new NewsListUseCase(channelPosition);
        this.newsListUseCase.registerCallBack(this);
    }

    public void refreshNewsList() {
        iChannelDetailFragmentView.showLoading();
        newsListUseCase.refreshNewsList();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {

    }

    @Override
    public void sendNewsListCallingBack(List<ItemNew> newsList) {
        newsListLocal = newsList;
        iChannelDetailFragmentView.refreshChannelsListRVAdapter();
        iChannelDetailFragmentView.hideLoading();
    }
}

