package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.myObserver.ICustomPublisher;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.presentation.presenter.CustomBottomSheetPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;

/**
 * Created by Andrey Ievlev on 18,Май,2019
 */

public class CustomBottomSheetFragment extends BottomSheetDialogFragment implements ICustomBottomSheetView {

    public static final String CHANNEL_POSITION = "channel position";
    private TextView channelTitle;
    private TextView lastBuildDate;
    private TextView addFavorite;
    private TextView shareChannel;
    private TextView deleteChannel;
    private ImageView addFavoriteImage;
    private ImageView shareChannelImage;
    private ImageView deleteChannelImage;
    private Feed channel;

    private CustomBottomSheetPresenter customBottomSheetPresenter;
    private ICustomPublisher.IActionAboveList publisher;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = App.getInstance().getPublisherActionAboveList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        customBottomSheetPresenter = FactoryProvider.providerPresenterFactory().createCustomBottomSheetPresenter(this);

        if (getArguments() != null) {
            channel = getArguments().getParcelable(CHANNEL_POSITION);
        }

        initUi(view);
        initOnClick();

        customBottomSheetPresenter.setChannelCard(channel);
    }

    private void initUi(final View view) {
        channelTitle = view.findViewById(R.id.channel_title_bs);
        lastBuildDate = view.findViewById(R.id.last_build_date_bs);
        addFavorite = view.findViewById(R.id.add_favorite);
        shareChannel = view.findViewById(R.id.share_channel);
        deleteChannel = view.findViewById(R.id.delete_channel);

        addFavoriteImage = view.findViewById(R.id.add_favorite_image);
        shareChannelImage = view.findViewById(R.id.share_channel_image);
        deleteChannelImage = view.findViewById(R.id.delete_channel_image);
    }

    private void initOnClick() {
        addFavorite.setOnClickListener(view -> {
            customBottomSheetPresenter.addFavorite(Command.ADD_FAVORITE);
        });
        shareChannel.setOnClickListener(view -> {
            customBottomSheetPresenter.shareChannel(Command.SHARE_CHANNEL);

        });
        deleteChannel.setOnClickListener(view -> {
            customBottomSheetPresenter.deleteChannel(Command.DELETE_CHANNEL);
        });
        addFavoriteImage.setOnClickListener(view -> {
            customBottomSheetPresenter.addFavorite(Command.ADD_FAVORITE);
        });
        shareChannelImage.setOnClickListener(view -> {
            customBottomSheetPresenter.shareChannel(Command.SHARE_CHANNEL);
        });
        deleteChannelImage.setOnClickListener(view -> {
            customBottomSheetPresenter.deleteChannel(Command.DELETE_CHANNEL);
        });
    }

    @Override
    public void setChannelCard(final String title, final String lastBuild) {
        channelTitle.setText(title);
        lastBuildDate.setText(lastBuild);
    }

    @Override
    public void addFavorite(final Command command) {
        publisher.notify(command);
    }

    @Override
    public void shareChannel(final Command command) {
        publisher.notify(command);
    }

    @Override
    public void deleteChannel(final Command command) {
        publisher.notify(command);
    }

    @Override
    public void closeBottomSheet() {
        dismiss();
    }

}
