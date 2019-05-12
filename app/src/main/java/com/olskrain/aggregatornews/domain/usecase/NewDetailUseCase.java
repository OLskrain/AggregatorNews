package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.INewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.NewDetailRepository;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailUseCase implements INewDetailUseCase, NewDetailRepository.IResponseServerCallback {

    public interface IResponseServerCallback {
        void onMessageStatus(String message);
        void onWebPage(String webPage);
    }

    private IResponseServerCallback callback;
    private INewsDetailRepository newsDetailRepository;

    public NewDetailUseCase(String urlNew) {
        this.newsDetailRepository = new NewDetailRepository(urlNew);
        ((NewDetailRepository) this.newsDetailRepository).registerCallBack(this);
    }

    @Override
    public void registerCallBack(IResponseServerCallback callback) {
        this.callback = callback;
    }

    @Override
    public void getWebPage() {
        newsDetailRepository.getWebPage();
    }

    @Override
    public void onMessageStatus(String message) {
        callback.onMessageStatus(message);
    }

    @Override
    public void onWebPage(String webPage) {
        callback.onWebPage(webPage);
    }
}
