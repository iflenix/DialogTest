package com.storm.dialogtest;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends Activity implements ListDialog.NoticeDialogListener {
    DialogFragment dlg1;
    DialogFragment dlg2;
    DialogFragment listDlg;
    TimePickerDialog tpDlg;

    String[] data = {"one", "two", "three", "four"};
    NotificationManager notifManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        dlg1 = new Dialog1();
        dlg2 = new Dialog2();
        tpDlg = new TimePickerDialog(this, timeSetListener, 0, 0, true);
        listDlg = new ListDialog();
        notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.startNotification:
                Notification.Builder builder = new Notification.Builder(this);
                builder.setLights(Color.RED, 0, 1)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                        .setNumber(5)
                        .setTicker("Ticker")
                        .setProgress(100, 25, false)
                        //.setOngoing(true)
                                //.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                        .setContentText("Шо нибудь")
                        .setContentTitle("Title")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(android.R.drawable.ic_dialog_info);


                Notification nf = builder.build();
                notifManager.notify(1, nf);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(12000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
                builder.setProgress(100,75,false);
                notifManager.notify(1,builder.build());


        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDlg1:
                dlg1.show(getFragmentManager(), "dlg1");
                break;
            case R.id.btnDlg2:
                dlg2.show(getFragmentManager(), "dlg2");
                break;
            case R.id.btnTimeDlg:
                tpDlg.show();
                break;
            case R.id.btnListDlg:
                Bundle bundle = new Bundle();
                bundle.putStringArray("data", data);
                listDlg.setArguments(bundle);
                listDlg.show(getFragmentManager(), "ListDlg");
                break;


        }
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Toast.makeText(view.getContext(), String.valueOf(hourOfDay) + " : " + String.valueOf(minute), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDialogPositiveClick(int checkedItemPos) {
        Toast.makeText(this, "Checked:" + String.valueOf(checkedItemPos), Toast.LENGTH_SHORT).show();
    }
}
