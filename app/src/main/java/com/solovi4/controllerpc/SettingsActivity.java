package com.solovi4.controllerpc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText pcEditText;
    private EditText portEditText;
    private PreferencesManager preferencesManager;
    private final int minPortValue = 0;
    private final int maxPortValue = 65535;
    public final static String Changed = "changed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        pcEditText = findViewById(R.id.PCNameEditText);
        portEditText = findViewById(R.id.PortEditText);
        preferencesManager = PreferencesManager.GetInstance(this.getApplicationContext());
        loadPreferences();
    }

    private void loadPreferences() {
        String ipAddress = preferencesManager.GetIPAddress();
        int port = preferencesManager.GetPort();
        pcEditText.setText(ipAddress);
        portEditText.setText(String.valueOf(port));
    }

    public void cancelButtonClick(View button) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void Alert(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void saveButtonClick(View view) {

        String ipAddress = pcEditText.getText().toString();

        if(!Patterns.IP_ADDRESS.matcher(ipAddress).matches()) {
            Alert("Введите правильно ip адрес компьютера");
            return;
        }
        String portStr = portEditText.getText().toString();
        int port = -1;
        if(portStr.length() == 0) {
            Alert("Введите порт");
            return;
        }

        port = Integer.parseInt(portStr);
        if(port < minPortValue || port > maxPortValue) {
            Alert(String.format("Порт должен в диапазоне от %d до %d", minPortValue, maxPortValue));
            return;
        }

        preferencesManager.SavePreferences(ipAddress, port);
        Intent intent = new Intent();
        intent.putExtra(Changed, true);
        setResult(RESULT_OK, intent);
        finish();
    }
}
