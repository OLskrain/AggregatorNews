package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.presentation.presenter.FavoriteChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IFavoriteChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IFavoriteChannelsListView;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class FavoriteChannelsListFragment extends Fragment implements IFavoriteChannelsListView {

    public static FavoriteChannelsListFragment getInstance(String arg) {
        FavoriteChannelsListFragment fragment = new FavoriteChannelsListFragment();
        Bundle arguments = new Bundle();
        arguments.putString("arg", arg);
        fragment.setArguments(arguments);
        fragment.setRetainInstance(true);
        return fragment;
    }

    public static final String ARG_FCLF_ID = "favoriteChannelListId";
    private FavoriteChannelsListPresenter favoriteChannelsListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_list_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoriteChannelsListPresenter = FactoryProvider.providerPresenterFactory().createFavoriteChannelsListPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        favoriteChannelsListPresenter.onAttachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        favoriteChannelsListPresenter.onDetachView();
    }
}
