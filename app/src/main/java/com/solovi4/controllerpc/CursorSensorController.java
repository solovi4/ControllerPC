package com.solovi4.controllerpc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class CursorSensorController implements Button.OnTouchListener {

    private MySensorListener mySensorListener;
    private SensorManager sensorManager;
    private Sensor sensor;
    private Handler mHandler = new Handler();
    private Commander commander;

    public CursorSensorController(Commander commander, Context context) {
        this.commander = commander;
        mySensorListener = new MySensorListener();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionevent) {
        int action = motionevent.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            sensorManager.registerListener(mySensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            mHandler.removeCallbacks(mUpdateTaskup);
            mHandler.postAtTime(mUpdateTaskup, SystemClock.uptimeMillis() + 50);
        } else if (action == MotionEvent.ACTION_UP) {
            mHandler.removeCallbacks(mUpdateTaskup);
            sensorManager.unregisterListener(mySensorListener);
        }
        return false;
    }

    private Runnable mUpdateTaskup = new Runnable() {
        public void run() {
            int multiplier = 5;
            float dx = mySensorListener.GetX();
            float dy = mySensorListener.GetY();
            float dz = mySensorListener.GetZ();
            int dxInt = -Math.round(dx * multiplier);
            int dyInt = -Math.round(dy * multiplier);
            int dzInt = -Math.round(dz * multiplier);
            //textView.setText(String.format("dx = %f \n dy = %f \n dz = %f ", dx, dy, dz));

            if(Math.abs(dzInt) > 0 || Math.abs(dxInt) > 0)
                commander.MoveCursor(dxInt, dyInt);
            mHandler.postAtTime(this, SystemClock.uptimeMillis() + 100);
        }//end run
    };// end runnable
}
