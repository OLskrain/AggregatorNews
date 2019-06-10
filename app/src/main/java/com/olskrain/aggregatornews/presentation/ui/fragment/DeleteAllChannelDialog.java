package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.myObserver.ICustomPublisher;
import com.olskrain.aggregatornews.R;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 10,Июнь,2019
 */

public class DeleteAllChannelDialog extends DialogFragment {
    private ICustomPublisher publisher;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        publisher = App.getInstance().getPublisher();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.delete_all_channel_dialog)
                .setPositiveButton(R.string.button_action_delete_channels, (dialogInterface, i) -> publisher.notify(Command.DELETE_ALL_CHANNELS))
                .setNegativeButton(R.string.button_action_cancel, (dialogInterface, i) -> Timber.d(""));
        return builder.create();
    }
}
