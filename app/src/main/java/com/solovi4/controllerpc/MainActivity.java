package com.solovi4.controllerpc;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener, Button.OnTouchListener {

    private TextView textView;

    private Commander commander;
    private PreferencesManager preferencesManager;
    private Button moveCursorButton;
    private Handler mHandler = new Handler();
    private MySensorListener mySensorListener;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_main);
        preferencesManager = PreferencesManager.GetInstance(this.getApplicationContext());
        commander = new Commander(preferencesManager.GetURL());
        SeekBar seekBar = findViewById(R.id.seekBar);
        moveCursorButton = findViewById(R.id.moveCursorButton);
        moveCursorButton.setOnTouchListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        textView = findViewById(R.id.textView);

        mySensorListener = new MySensorListener();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(mySensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }




    @Override
    public boolean onTouch(View view, MotionEvent motionevent) {
        int action = motionevent.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mHandler.removeCallbacks(mUpdateTaskup);
            mHandler.postAtTime(mUpdateTaskup, SystemClock.uptimeMillis() + 50);
            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            mHandler.removeCallbacks(mUpdateTaskup);
        }
        return false;
    }

    private Runnable mUpdateTaskup = new Runnable() {
        public void run() {
            float dx = mySensorListener.GetX();
            float dz = mySensorListener.GetZ();
            int dxInt = (int)(dx * 10);
            int dzInt = -(int)(dz * 10);
            commander.MoveCursor(dxInt, dzInt);
            mHandler.postAtTime(this, SystemClock.uptimeMillis() + 100);
        }//end run
    };// end runnable

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void settingsItemClick(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        boolean changed = data.getBooleanExtra(SettingsActivity.Changed, false);
        if(changed)
            commander = new Commander(preferencesManager.GetURL());
    }

    public void Alert(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progress = progress * 10;
        commander.SetVolume(progress);
        //textView.setText(String.valueOf(progress));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    public void moveCursorButton(View view) {
        commander.MoveCursor(100, 100);
    }
}
