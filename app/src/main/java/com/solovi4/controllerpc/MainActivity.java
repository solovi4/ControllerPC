package com.solovi4.controllerpc;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.Console;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView textView;

    private Commander commander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commander = new Commander("http://192.168.1.38:49001/");
        setContentView(R.layout.activity_main);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        textView = findViewById(R.id.textView);
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
        //textView.setText(String.valueOf(progress));
        progress = progress * 10;
        commander.SetVolume(progress);
        //soapCall.execute();
        textView.setText(String.valueOf(progress));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }




}
