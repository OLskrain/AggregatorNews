package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.INewsListRepository;
import com.olskrain.aggregatornews.data.repository.NewsListRepository;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public class NewsListUseCase implements INewsListUseCase, NewsListRepository.IResponseDBCallback {

    public interface IResponseDBCallback {
        void onMessageStatus(String message);
        void onNewsList(List<ItemNew> newsList);
    }

    private IResponseDBCallback callback;
    private INewsListRepository newsListRepository;
    private List<ItemNew> newList;

    public NewsListUseCase(int channelPosition) {
        this.newsListRepository = new NewsListRepository(channelPosition);
        ((NewsListRepository) newsListRepository).registerCallBack(this);
    }

    @Override
    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void refreshNewsList() {
        newsListRepository.getChannelsList();
    }


    @Override
    public void onMessageStatus(String message) {

    }

    @Override
    public void onNewsList(List<ItemNew> newsList) {
        this.newList = newsList;
        callback.onNewsList(newsList);
    }
}
