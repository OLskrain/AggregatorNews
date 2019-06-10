package com.olskrain.aggregatornews.presentation.ui.view;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public interface INewsListFragmentView {
    void showLoading();

    void hideLoading();

    void goToNewsDetailActivity(String urlNews);

    void showBottomSheet(ItemNew itemNew);

    void setRandomNews(String titleNews, String pubDateRandomNews);

    void showError(Command command);

    void refreshChannelsListRVAdapter();
}
