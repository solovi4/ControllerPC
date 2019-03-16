package com.solovi4.controllerpc;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class MySensorListener implements SensorEventListener {

    private float epsilon = 0.01f;
    private float axisX, axisY, axisZ;

    public MySensorListener() {

    }

    public float GetX() {
        return axisX;
    }

    public float GetY() {
        return axisY;
    }

    public float GetZ() {
        return axisZ;
    }

    private float normalize(float value) {
        if(Math.abs(value) <= epsilon)
            return 0;

        return value;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        axisX = normalize(event.values[0]);
        axisY = normalize(event.values[1]);
        axisZ = normalize(event.values[2]);
        // More code goes here
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
