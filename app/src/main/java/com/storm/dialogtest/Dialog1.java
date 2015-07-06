package com.storm.dialogtest;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by HOME on 04.07.2015.
 */
public class Dialog1 extends DialogFragment implements View.OnClickListener {
    final String LOG_TAG = "MyLogs";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Dialog 1 Title!");
        View v =  inflater.inflate(R.layout.dialog1,null);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);
        v.findViewById(R.id.btnMaybe).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
        dismiss();

    }
}
