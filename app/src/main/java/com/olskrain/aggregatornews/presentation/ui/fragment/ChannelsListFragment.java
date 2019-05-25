package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.myObserver.ICustomObserver;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.presentation.presenter.ChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.ui.activity.ChannelDetailActivity;
import com.olskrain.aggregatornews.presentation.ui.adapter.ChannelsListRVAdapter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;

import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class ChannelsListFragment extends Fragment implements IChannelsListView, ICustomObserver {

    public static ChannelsListFragment getInstance(String arg) {
        ChannelsListFragment fragment = new ChannelsListFragment();
        Bundle arguments = new Bundle();
        arguments.putString("arg", arg);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static final String CHANNEL_POSITION = "channel position";
    public static final String ARG_ACLF_ID = "allChannelListId";
    public static final String CHANNEL_ONE = "https://news.yandex.ru/Khanty-Mansiysk/index.rss";
    public static final String CHANNEL_TWO = "https://news.yandex.ru/business.rss";
    public static final String CHANNEL_THREE = "https://news.yandex.ru/world.rss";

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button addChannelOne;
    private Button addChannelTwo;
    private Button addChannelThree;
    private Button deleteAllChannels;
    private CustomBottomSheetFragment customBottomSheetFragment;
    private CompositeDisposable compositeDisposable;
    View.OnClickListener snackbarOnCliclListener;

    private ChannelsListRVAdapter allChannelsListRVAdapter;
    private ChannelsListPresenter channelsListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel_list, container, false);

        customBottomSheetFragment = new CustomBottomSheetFragment();
        compositeDisposable = new CompositeDisposable();
        channelsListPresenter = new ChannelsListPresenter(this, compositeDisposable, AndroidSchedulers.mainThread());
        channelsListPresenter.attachView();

        initUi(view);
        initOnClick();

        channelsListPresenter.getUrlsChannelList();

        return view;
    }

    public void initUi(View view) {
        loadingProgressBar = view.findViewById(R.id.all_list_loading_progressBar);

        RecyclerView allChannelsListRecyclerView = view.findViewById(R.id.all_channels_list);
        allChannelsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allChannelsListRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
        allChannelsListRVAdapter = new ChannelsListRVAdapter(channelsListPresenter.channelListPresenter);
        allChannelsListRecyclerView.setAdapter(allChannelsListRVAdapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refreshLayout_channels);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        addChannelOne = view.findViewById(R.id.add_channel_one);
        addChannelTwo = view.findViewById(R.id.add_channel_two);
        addChannelThree = view.findViewById(R.id.add_channel_three);

        deleteAllChannels = view.findViewById(R.id.delete_all_channels);
    }

    public void initOnClick() {
        addChannelOne.setOnClickListener(view -> channelsListPresenter.checkDuplicate(CHANNEL_ONE));
        addChannelTwo.setOnClickListener(view -> channelsListPresenter.checkDuplicate(CHANNEL_TWO));
        addChannelThree.setOnClickListener(view -> channelsListPresenter.checkDuplicate(CHANNEL_THREE));
        deleteAllChannels.setOnClickListener(view -> channelsListPresenter.deleteAllChannels());

        swipeRefreshLayout.setOnRefreshListener(() -> channelsListPresenter.refreshChannelsList());
        snackbarOnCliclListener = view -> Timber.d("rty WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
    }

    @Override
    public void showLoading() {

        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void goToChannelDetailFragment(String urlChannel) {
        Intent intent = new Intent(getContext(), ChannelDetailActivity.class);
        intent.putExtra(ChannelDetailFragment.ARG_CDF_ID, urlChannel);
        Objects.requireNonNull(getContext()).startActivity(intent);
    }

    @Override
    public void showBottomSheet(Feed feed) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CHANNEL_POSITION, feed);
        customBottomSheetFragment.setArguments(bundle);
        if (getFragmentManager() != null) {
            customBottomSheetFragment.show(getFragmentManager(), customBottomSheetFragment.getTag());
        }
    }

    @Override
    public Completable showWarning(Command command) {
        return Completable.create(emitter -> {
            switch (command) {
                case DELETE_CHANNEL:
                    Snackbar.make(Objects.requireNonNull(getView()), R.string.warning_delete_channel, Snackbar.LENGTH_LONG)
                            .setAction(R.string.button_action_cancel, snackbarOnCliclListener).addCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT){
                                emitter.onComplete();
                            } else if (event == Snackbar.Callback.DISMISS_EVENT_ACTION) {
                                emitter.onError(new RuntimeException());
                            }
                        }
                    }).show();
                    break;
                case DELETE_ALL_CHANNELS:
                    emitter.onComplete();
                    break;
                default:
                    emitter.onError(new RuntimeException());
                    break;
            }
        });
    }

    @Override
    public void showError(Command command) {
        switch (command) {
            case ADD_CHANNEL:
                Snackbar.make(Objects.requireNonNull(getView()), R.string.error_connection, Snackbar.LENGTH_SHORT).show();
                break;
            case REFRESH_URL:
                Snackbar.make(Objects.requireNonNull(getView()), R.string.error_no_channels, Snackbar.LENGTH_SHORT).show();
                break;
            case REFRESH_CHANNELS:
                Snackbar.make(Objects.requireNonNull(getView()), R.string.error_failed_update, Snackbar.LENGTH_SHORT).show();
                break;
            case ERROR_DIFFERENT:
                Snackbar.make(Objects.requireNonNull(getView()), R.string.error_defferent, Snackbar.LENGTH_SHORT).show();
                break;
            case ERROR_CHECK_DUPLICATE:
                Snackbar.make(Objects.requireNonNull(getView()), R.string.check_duplicate, Snackbar.LENGTH_SHORT).show();
                break;
            default:
                Snackbar.make(Objects.requireNonNull(getView()), R.string.error_defferent, Snackbar.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void refreshChannelsListRVAdapter() {
        allChannelsListRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void actionAboveChannelsList(Command command) {
        switch (command) {
            case ADD_FAVORITE:
                break;
            case SHARE_CHANNEL:
                break;
            case DELETE_CHANNEL:
                //TODO : возникает баг при перевороте и попытке вызвать метод
                channelsListPresenter.deleteChannel(command);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        channelsListPresenter.putUrlsChannelList();
        compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
