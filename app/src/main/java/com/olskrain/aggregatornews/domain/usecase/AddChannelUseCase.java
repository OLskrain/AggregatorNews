package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IAddChannelUseCase;

/**
 * Created by Andrey Ievlev on 28,Май,2019
 */

public class AddChannelUseCase implements IAddChannelUseCase {

    @Override
    public boolean checkError(String urlChannel) {
        if (urlChannel.length() > 0) {
            if (urlChannel.length() < 12) {
                return true;
            }

            return !urlChannel.contains("http://") && !urlChannel.contains("https://");
        } else return false;

    }
}
