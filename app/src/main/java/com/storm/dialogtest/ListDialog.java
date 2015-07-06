package com.storm.dialogtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by HOME on 04.07.2015.
 */
public class ListDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(int checkedItemPos);
    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            Log.d("MyLogs","CLASS CAST EXCEPTION IN LIST DIALOG!");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

        adb.setTitle("ListDialog");
       // adb.setItems(savedInstanceState.getStringArray("data"),this);
        adb.setSingleChoiceItems(getArguments().getStringArray("data"), 0, this);
        adb.setPositiveButton("OK",this);
        return adb.create();

    }



    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {

        ListView lv = ((AlertDialog) dialog).getListView();
        int pos = lv.getCheckedItemPosition();
        mListener.onDialogPositiveClick(pos);
        }


    }
}
