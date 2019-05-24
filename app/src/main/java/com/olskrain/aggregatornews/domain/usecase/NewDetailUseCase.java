package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.INewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.NewDetailRepository;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailUseCase implements INewDetailUseCase {

    private INewsDetailRepository newsDetailRepository;

    public NewDetailUseCase() {
        this.newsDetailRepository = new NewDetailRepository();
    }

    @Override
    public Single<String> getWebPage(String urlNews) {
        return newsDetailRepository.getWebPage(urlNews);
    }
}
