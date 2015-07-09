package com.storm.dialogtest;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends Activity implements ListDialog.NoticeDialogListener {
    DialogFragment dlg1;
    DialogFragment dlg2;
    DialogFragment listDlg;
    TimePickerDialog tpDlg;
    TextView animTextView;

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
        animTextView = (TextView) findViewById(R.id.textViewAnim);
        registerForContextMenu(animTextView);

        Button btn = (Button) findViewById(R.id.btnListDlg);
        btn.setBackgroundResource(R.drawable.step_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) btn.getBackground();
        animationDrawable.start();

        Button timePicker = (Button) findViewById(R.id.btnTimeDlg);
        timePicker.setBackgroundResource(R.drawable.state_list_test);

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

    final int MENU_ALPHA_ID = 1;
    final int MENU_SCALE_ID = 2;
    final int MENU_TRANSLATE_ID = 3;
    final int MENU_SHAKE_ID = 4;
    final int MENU_COMBO_ID = 5;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.textViewAnim:
                menu.add(0,MENU_ALPHA_ID,0,"Alpha TEST");
                menu.add(0,MENU_SCALE_ID,0,"Scale TEST");
                menu.add(0,MENU_TRANSLATE_ID,0,"Translate TEST");
                menu.add(0, MENU_SHAKE_ID, 0, "SHAKE TEST");

        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation anim = null;
        switch (item.getItemId()) {
            case MENU_ALPHA_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.alpha_test);
                anim.setRepeatMode(Animation.RESTART);
                anim.setRepeatCount(Animation.INFINITE);
                break;
            case MENU_SCALE_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.scale_test);
                break;

            case MENU_TRANSLATE_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.trans_test);
                break;
            case MENU_SHAKE_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.shake);
                //anim.setInterpolator(new AccelerateDecelerateInterpolator());
                break;

            default: return true;
        }

        animTextView.startAnimation(anim);
        return true;
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
    public void onDialogPositiveClick(int[] checkedItemPos) {
        String checkedString = "Checked:";
        for (int i: checkedItemPos) {
            checkedString += i + ",";

        }
        Toast.makeText(this, checkedString, Toast.LENGTH_SHORT).show();
    }
}
