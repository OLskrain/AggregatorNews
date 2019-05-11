package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.INewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.NewDetailRepository;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */
public class NewDetailUseCase implements NewDetailRepository.IResponseServerCallback {

    public interface IResponseServerCallback {
        void sendMessageStatusCallingBack(String message);
        void sendWebPageCallingBack(String webPage);
    }

    private IResponseServerCallback callback;
    private INewsDetailRepository newsDetailRepository;

    public NewDetailUseCase(String urlNew) {
        this.newsDetailRepository = new NewDetailRepository(urlNew);
        ((NewDetailRepository) this.newsDetailRepository).registerCallBack(this);
    }

    public void registerCallBack(IResponseServerCallback callback) {
        this.callback = callback;
    }

    public void getWebPage() {
        newsDetailRepository.getWebPage();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {
        callback.sendMessageStatusCallingBack(message);
    }

    @Override
    public void sendWebPageCallingBack(String webPage) {
        callback.sendWebPageCallingBack(webPage);
    }
}
