package com.olskrain.aggregatornews.presentation.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.LocaleHelper;
import com.olskrain.aggregatornews.Common.myObserver.ICustomPublisher;
import com.olskrain.aggregatornews.R;

/**
 * Created by Andrey Ievlev on 10,Июнь,2019
 */

public class LanguageSelectionDialog extends DialogFragment {
    private ICustomPublisher.IActionAppParameters publisher;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        publisher = App.getInstance().getPublisherActionAppParameters();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.title_language_dialog)
                .setItems(R.array.language, (dialogInterface, position) -> {
                    switch (position) {
                        case 1:
                            publisher.notify(Command.SELECTION_EN);
                            break;
                        case 2:
                            publisher.notify(Command.SELECTION_RU);
                            break;
                        default:
                            publisher.notify(Command.SELECTION_EN);
                            break;
                    }
                });
        return builder.create();
    }
}
