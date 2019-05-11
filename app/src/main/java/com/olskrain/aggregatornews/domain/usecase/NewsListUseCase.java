package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.INewsListRepository;
import com.olskrain.aggregatornews.data.repository.NewsListRepository;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public class NewsListUseCase implements NewsListRepository.IResponseDBCallback {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);
        void sendNewsListCallingBack(List<ItemNew> newsList);
    }

    private IResponseDBCallback callback;
    private INewsListRepository iNewsListRepository;
    private List<ItemNew> newList;

    public NewsListUseCase(int channelPosition) {
        this.iNewsListRepository = new NewsListRepository(channelPosition);
        ((NewsListRepository) iNewsListRepository).registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    public void refreshNewsList() {
        iNewsListRepository.getChannelsList();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {

    }

    @Override
    public void sendNewsListCallingBack(List<ItemNew> newsList) {
        this.newList = newsList;
        callback.sendNewsListCallingBack(newsList);
    }
}
