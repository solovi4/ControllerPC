package com.solovi4.controllerpc;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MouseRightButtonListener implements Button.OnTouchListener {
    private Commander commander;

    public MouseRightButtonListener(Commander commander) {
        this.commander = commander;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            commander.MouseRightButtonDown();
        } else if (action == MotionEvent.ACTION_UP) {
            commander.MouseRightButtonUp();
        }
        return false;
    }
}
