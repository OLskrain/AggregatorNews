package com.olskrain.aggregatornews.presentationn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.FavoriteChannelsListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.IFavoriteChannelsListView;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class FavoriteChannelsListFragment extends Fragment implements IFavoriteChannelsListView {

    public static FavoriteChannelsListFragment getInstance(String arg) {
        FavoriteChannelsListFragment fragment = new FavoriteChannelsListFragment();
        Bundle arguments = new Bundle();
        arguments.putString("arg", arg);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static final String ARG_FCLF_ID = "favoriteChannelListId";
    private FavoriteChannelsListPresenter favoriteChannelsListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, null);

        favoriteChannelsListPresenter = new FavoriteChannelsListPresenter();
        return view;
    }
}
