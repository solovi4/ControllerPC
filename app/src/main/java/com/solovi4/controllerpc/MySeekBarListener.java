package com.solovi4.controllerpc;

import android.widget.SeekBar;

public class MySeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private Commander commander;

    public MySeekBarListener(Commander commander) {
        this.commander = commander;
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
}
