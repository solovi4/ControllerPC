package com.solovi4.controllerpc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Commander commander;
    private PreferencesManager preferencesManager;
    private Button moveCursorButton;
    private EditText editText;
    private CursorSensorController cursorSensorController;
    private MySeekBarListener mySeekBarListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_main);
        preferencesManager = PreferencesManager.GetInstance(this.getApplicationContext());
        commander = new Commander(preferencesManager.GetURL());

        cursorSensorController = new CursorSensorController(commander, this.getApplicationContext());
        moveCursorButton = findViewById(R.id.moveCursorButton);
        moveCursorButton.setOnTouchListener(cursorSensorController);

        mySeekBarListener = new MySeekBarListener(commander);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(mySeekBarListener);

        CursorButtonController cursorButtonControllerLeft = new CursorButtonController(commander, -5, 0);
        Button moveCursoreLeftButton = findViewById(R.id.buttonCursorLeft);
        moveCursoreLeftButton.setOnTouchListener(cursorButtonControllerLeft);

        CursorButtonController cursorButtonControllerRight = new CursorButtonController(commander, 5, 0);
        Button moveCursoreRightButton = findViewById(R.id.buttonCursorRight);
        moveCursoreRightButton.setOnTouchListener(cursorButtonControllerRight);

        CursorButtonController cursorButtonControllerUp = new CursorButtonController(commander, 0, -5);
        Button moveCursoreUpButton = findViewById(R.id.buttonCursorUp);
        moveCursoreUpButton.setOnTouchListener(cursorButtonControllerUp);

        CursorButtonController cursorButtonControllerDown = new CursorButtonController(commander, 0, 5);
        Button moveCursoreDownButton = findViewById(R.id.buttonCursorDown);
        moveCursoreDownButton.setOnTouchListener(cursorButtonControllerDown);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
    }


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

    public void buttonCancel_Click(View view) {
        commander.CancelShutdown();
    }

    public void buttonShutdown_Click(View view) {
        commander.Shutdownn();
    }

    public void buttonMouseLeft_click(View view) {
        commander.MouseLeftClick();
    }

    public void buttonMouseRight_Click(View view) {
        commander.MouseRightClick();
    }

    public void buttonSendText_click(View view) {
        String text = editText.getText().toString();
        commander.SendText(text);
    }

    public void buttonDel_click(View view) {
        commander.SendText("{BACKSPACE}");
    }
}
