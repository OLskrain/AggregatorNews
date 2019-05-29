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

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.myObserver.CustomPublisher;
import com.olskrain.aggregatornews.Common.myObserver.ICustomPublishGetter;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.presentation.presenter.CustomBottomSheetPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Single;
import timber.log.Timber;

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
    private CustomPublisher publisher;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = ((ICustomPublishGetter) context).getPublisher(); // получим обработчика подписок
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        customBottomSheetPresenter = new CustomBottomSheetPresenter(this);

        if (getArguments() != null) {
            channel = getArguments().getParcelable(CHANNEL_POSITION);
        }

        initUi(view);
        initOnClick();

        customBottomSheetPresenter.setChannelCard(channel);
        return view;
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

        });
        shareChannel.setOnClickListener(view -> {


        });
        deleteChannel.setOnClickListener(view -> {
            customBottomSheetPresenter.deleteChannel(Command.DELETE_CHANNEL);
        });
        addFavoriteImage.setOnClickListener(view -> {

        });
        shareChannelImage.setOnClickListener(view -> {


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
