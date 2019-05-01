package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.ChannelsList;
import com.olskrain.aggregatornews.data.repository.ChannelRepository;
import com.olskrain.aggregatornews.data.repository.LinksRepository;
import com.olskrain.aggregatornews.presentationn.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.MainView;
import com.olskrain.aggregatornews.presentationn.ui.view.item.IChannelListItemView;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainPresenter implements ChannelRepository.ICallback {

    public class ChannelListPresenter implements IChannelListPresenter { //презентер для списка
        @Override
        public void bindView(IChannelListItemView rowView) {
            rowView.setTitle(channelListLocal.getChannelsList().get(rowView.getPos()).getFeed().getLink());
        }

        @Override
        public int getChannelCount() {
            return channelListLocal == null ? 0 : channelListLocal.getChannelsList().size();
        }
    }

    private MainView mainView;
    public ChannelListPresenter channelListPresenter;

    private ChannelRepository channelRepo;
    private LinksRepository linksRepo;
    private Channel channelLocal;
    private ChannelsList channelListLocal;


    public MainPresenter(MainView view) {
        // this.model = new Model();
        this.mainView = view;
        this.channelListPresenter = new ChannelListPresenter();
        this.channelRepo = new ChannelRepository();
        this.linksRepo = new LinksRepository();
    }

    public void addNewChannel() {
        mainView.addNewChannel();
    }

    public void loadInfo() { //Todo: переименовать
        mainView.showLoading();
        channelRepo.registerCallBack(this);
        channelListLocal = linksRepo.getData(); //получили список каналов
        channelRepo.getData(channelListLocal);
        /**
         * После запроса на обновление мы идем Pres -> Repo -> BD -> ищем список с каналами
         * BD -> Repo -> Pres -> кидаем список
         *
         */

    }

    @Override
    public void callingBack(String s) {
        mainView.hideLoading();
        mainView.showText(s);
        Timber.d("rty " + s);
    }
}
