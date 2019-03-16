package com.solovi4.controllerpc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class PreferencesManager {
    private static PreferencesManager instance;

    private final String prefName = "com.Solovi4.controllerPC";
    private final String pcNameKey = "pcName";
    private final String portKey = "port";
    private String ipAddress;
    private int port;
    private Context context;

    private PreferencesManager(Context context) {
        this.context = context;
        loadPreferences();
    }



    private void loadPreferences() {
        SharedPreferences settings = context.getSharedPreferences(prefName, 0);
        ipAddress = settings.getString(pcNameKey, "192.168.1.38");
        port = settings.getInt(portKey, 49001);
    }

    public String GetIPAddress() {
        return ipAddress;
    }

    public int GetPort() {
        return port;
    }

    public static PreferencesManager GetInstance(Context context) {
        if (instance == null)
            instance = new PreferencesManager(context);

        return instance;
    }

    public void SavePreferences (String ipAddress, int port) {
        SharedPreferences settings = context.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(pcNameKey, ipAddress);
        this.ipAddress = ipAddress;
        editor.putInt(portKey, port);
        this.port = port;
        editor.commit();
    }

    public String GetURL() {
        String url = String.format("http://%s:%d/", ipAddress, port);
        return url;
    }

}
