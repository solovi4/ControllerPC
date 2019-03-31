package com.solovi4.controllerpc;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MouseLeftButtonListener implements Button.OnTouchListener {
    private Commander commander;

    public MouseLeftButtonListener(Commander commander) {
        this.commander = commander;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            commander.MouseLeftButtonDown();
        } else if (action == MotionEvent.ACTION_UP) {
            commander.MouseLeftButtonUp();
        }
        return false;
    }
}
