package com.solovi4.controllerpc;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TouchPadController implements Button.OnTouchListener {
    private Handler mHandler = new Handler();
    private Commander commander;
    private float xPrv = 0;
    private float yPrv = 0;
    private float sensitivity = 1.0f;
    private boolean beginMove = true;

    public TouchPadController(Commander commander) {
        this.commander = commander;
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionevent) {
        int action = motionevent.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            beginMove = true;
        } else if (action == MotionEvent.ACTION_MOVE) {
            float x = motionevent.getX();
            float y = motionevent.getY();
            if(!beginMove) {
                int dx = (int) ((x - xPrv) * sensitivity);
                int dy = (int) ((y - yPrv) * sensitivity);
                commander.MoveCursor(dx, dy);
            }
            xPrv = x;
            yPrv = y;
            beginMove = false;
        } else if (action == MotionEvent.ACTION_UP) {

        }
        return false;
    }

}
