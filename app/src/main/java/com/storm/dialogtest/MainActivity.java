package com.storm.dialogtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity implements ListDialog.NoticeDialogListener {
    DialogFragment dlg1;
    DialogFragment dlg2;
    DialogFragment listDlg;
    TimePickerDialog tpDlg;

    String[] data = {"one","two","three","four"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        dlg1 = new Dialog1();
        dlg2 = new Dialog2();
        tpDlg = new TimePickerDialog(this,timeSetListener,0,0,true);
        listDlg  = new ListDialog();


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDlg1:
                dlg1.show(getFragmentManager(),"dlg1");
                break;
            case R.id.btnDlg2:
                dlg2.show(getFragmentManager(),"dlg2");
                break;
            case R.id.btnTimeDlg:
                tpDlg.show();
                break;
            case  R.id.btnListDlg:
                Bundle bundle = new Bundle();
                bundle.putStringArray("data",data);
                listDlg.setArguments(bundle);
                listDlg.show(getFragmentManager(),"ListDlg");
                break;


        }
    }
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Toast.makeText(view.getContext(),String.valueOf(hourOfDay) + " : " + String.valueOf(minute),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDialogPositiveClick(int checkedItemPos) {
        Toast.makeText(this,"Checked:" + String.valueOf(checkedItemPos),Toast.LENGTH_SHORT).show();
    }
}
