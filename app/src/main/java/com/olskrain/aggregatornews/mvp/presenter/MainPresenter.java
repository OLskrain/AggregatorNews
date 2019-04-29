package com.olskrain.aggregatornews.mvp.presenter;

import com.olskrain.aggregatornews.mvp.model.entity.LinkObject;
import com.olskrain.aggregatornews.mvp.model.repository.RssDataRepo;
import com.olskrain.aggregatornews.mvp.presenter.List.IChannelListPresenter;
import com.olskrain.aggregatornews.mvp.view.MainView;
import com.olskrain.aggregatornews.mvp.view.item.IChannelListItemView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainPresenter implements RssDataRepo.ICallback {

    public class ChannelListPresenter implements IChannelListPresenter { //презентер для списка
        @Override
        public void bindView(IChannelListItemView rowView) {
            rowView.setTitle(linkObjectLocal.getLinkList().get(rowView.getPos()));
        }

        @Override
        public int getChannelCount() {
            return linkObjectLocal == null ? 0 : linkObjectLocal.getLinkList().size();
        }
    }

    private MainView mainView;
    private RssDataRepo rssDataRepo;
    private LinkObject linkObjectLocal;
    public ChannelListPresenter channelListPresenter = new ChannelListPresenter();

    public MainPresenter(MainView view) {
        // this.model = new Model();
        this.mainView = view;
        this.rssDataRepo = new RssDataRepo();

        //временно
        linkObjectLocal = new LinkObject();
    }

//    public void addNewChannel(){
//        mainView.addNewChannel();
//    }

    public void loadInfo() {
        mainView.showLoading();
        rssDataRepo.registerCallBack(this);
        rssDataRepo.getRssData();

//        rssDataRepo.getRssData();

    }

    @Override
    public void callingBack(String s) {
        mainView.hideLoading();
        mainView.showText(s);
    }
}
