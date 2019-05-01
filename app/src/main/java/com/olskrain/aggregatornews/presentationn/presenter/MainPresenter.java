package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.interactor.MainInteractor;
import com.olskrain.aggregatornews.presentationn.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.IMainView;
import com.olskrain.aggregatornews.presentationn.ui.view.item.IChannelListItemView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainPresenter implements MainInteractor.ICallback{
    public class ChannelListPresenter implements IChannelListPresenter { //презентер для списка
        @Override
        public void bindView(IChannelListItemView rowView) {
           // rowView.setTitle(channelListLocal.getChannelsList().get(rowView.getPos()).getFeed().getLink());
        }

        @Override
        public int getChannelCount() {
            return 0;
           // return channelListLocal == null ? 0 : channelListLocal.getChannelsList().size();
        }
    }

    public ChannelListPresenter channelListPresenter;
    private IMainView mainView;
    private MainInteractor mainInteractor;
    //private Channel channelLocal;

    public MainPresenter(IMainView view) {
        this.mainView = view;
        this.mainInteractor = new MainInteractor();
        mainInteractor.registerCallBack(this);
        this.channelListPresenter = new ChannelListPresenter();
    }

   public void actionsChannelsList(Command command, String url){
        mainView.displayMessages(mainInteractor.actionsChannelsList(command, url));
   }
    
    public void loadInfo() { //Todo: переименовать
    }

    @Override
    public void callingBackSendMessage(String message) {
        mainView.displayMessages(message);
    }
}
