package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.INewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.NewDetailRepository;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailUseCase implements INewDetailUseCase {

    private INewsDetailRepository newsDetailRepository;

    public NewDetailUseCase(String urlNew) {
        this.newsDetailRepository = new NewDetailRepository(urlNew);
    }

    @Override
    public void getWebPage() {
        newsDetailRepository.getWebPage();
    }


}
