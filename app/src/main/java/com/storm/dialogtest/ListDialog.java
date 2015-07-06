package com.storm.dialogtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by HOME on 04.07.2015.
 */
public class ListDialog extends DialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnMultiChoiceClickListener {


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
            Log.d("MyLogs", "CLASS CAST EXCEPTION IN LIST DIALOG!");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

        adb.setTitle("ListDialog");
        // adb.setItems(savedInstanceState.getStringArray("data"),this);
//        adb.setSingleChoiceItems(getArguments().getStringArray("data"), 0, this);
        adb.setMultiChoiceItems(getArguments().getStringArray("data"), null, this);
        adb.setPositiveButton("OK", this);
        return adb.create();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {

            ListView lv = ((AlertDialog) dialog).getListView();
            SparseBooleanArray checkedArray = lv.getCheckedItemPositions();
            String checkedStr = "Checked: ";
            for (int i = 0; i < checkedArray.size();i++) {
                checkedStr+= i+ ", ";

            }
            Toast toast = Toast.makeText(getActivity(),checkedStr,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.show();

        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        String message = which + " is checked";

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }
}
