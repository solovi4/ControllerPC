package com.solovi4.controllerpc;

import android.os.AsyncTask;
import org.json.JSONObject;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoapCall extends AsyncTask<String, Object, String> {
    public String response;
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String URL = "http://192.168.1.38:49001/ICommandExecutorService";
    public static final String SOAP_ACTION = "http://tempuri.org/ICommandExecutorService/VolumeIncrease";
    public static final String METHOD_NAME = "VolumeIncrease";
    @Override
    protected String doInBackground(String... strings) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //request.addProperty("level", 100);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        Element[] header = new Element[1];
        header[0] = new Element().createElement("http://www.w3.org/2005/08/addressing","Action");
        header[0].setAttribute(null, "mustUnderstand","1");
        //header[0].setAttribute(null, "xmlns", "http://schemas.microsoft.com/ws/2005/05/addressing/none");
        header[0].addChild(Node.TEXT, "http://tempuri.org/ICommandExecutorService/VolumeIncrease");
        envelope.headerOut = header;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        //envelope.setOutputSoapObject(request);
        HttpTransportSE transportSE = new HttpTransportSE(URL, 30000000);
        try{
            transportSE.call(SOAP_ACTION, envelope);
            response = (String)envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


}
