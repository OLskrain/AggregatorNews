package com.olskrain.aggregatornews.presentationn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.OtherPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.IOtherView;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class OtherFragment extends Fragment implements IOtherView {

    public static OtherFragment getInstance(String arg) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString("arg", arg);
        fragment.setArguments(args);
        return fragment;
    }

    private OtherPresenter otherPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, null);

        otherPresenter = new OtherPresenter();
        return view;
    }
}
