package com.storm.dialogtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by HOME on 04.07.2015.
 */
public class Dialog2 extends DialogFragment implements DialogInterface.OnClickListener {

    final String LOG_TAG = "myLogs";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Dialog 2 Title!");
        adb.setPositiveButton(R.string.yes, this);
        adb.setNegativeButton(R.string.no, this);
        adb.setNeutralButton(R.string.maybe, this);
        adb.setMessage(R.string.message_text);

        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                i = R.string.yes;
                break;
            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no;
                break;
            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe;
                break;
            default:break;
        }
        if (i > 0) {
            Log.d(LOG_TAG,"Dialog 2: " + getResources().getString(i));
            Toast.makeText(getActivity(),"Pressed: " + getResources().getString(i),Toast.LENGTH_SHORT).show();
        }

    }
}
