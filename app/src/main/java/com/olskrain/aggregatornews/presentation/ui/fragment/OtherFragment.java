package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentation.presenter.OtherPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IOtherView;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class OtherFragment extends Fragment implements IOtherView {

    public static OtherFragment getInstance(String arg) {
        OtherFragment fragment = new OtherFragment();
        Bundle arguments = new Bundle();
        arguments.putString("arg", arg);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static final String ARG_OF_ID = "otherId";
    private OtherPresenter otherPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, null);

        otherPresenter = new OtherPresenter();
        return view;
    }
}
