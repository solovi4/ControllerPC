package com.solovi4.controllerpc;

import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class CursorButtonController implements Button.OnTouchListener  {
    private Handler mHandler = new Handler();
    private Commander commander;
    private int dx, dy;
    private int acceleration = 1;
    private int counter = 0;
    public CursorButtonController(Commander commander, int dx, int dy) {
        this.commander = commander;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionevent) {
        int action = motionevent.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            counter = 0;
            mHandler.removeCallbacks(mUpdateTaskup);
            mHandler.postAtTime(mUpdateTaskup, SystemClock.uptimeMillis() + 100);
        } else if (action == MotionEvent.ACTION_UP) {
            mHandler.removeCallbacks(mUpdateTaskup);
            counter = 0;
        }
        return false;
    }

    private Runnable mUpdateTaskup = new Runnable() {

        private void speedControl() {
            switch (counter)
            {
                case 0: acceleration = 1; break;
                case 5: acceleration = 2; break;
                case 10: acceleration = 3; break;
                case 15: acceleration = 4; break;
                case 20: acceleration = 5; break;
                case 25: acceleration = 6; break;
                case 30: acceleration = 7; break;
                case 35: acceleration = 8; break;
                case 40: acceleration = 9; break;
                case 45: acceleration = 10; break;
            }
        }

        public void run() {
            //textView.setText(String.format("dx = %f \n dy = %f \n dz = %f ", dx, dy, dz));
            speedControl();
            commander.MoveCursor(dx * acceleration, dy * acceleration);
            mHandler.postAtTime(this, SystemClock.uptimeMillis() + 50);
            counter++;
        }//end run
    };// end runnable
}
