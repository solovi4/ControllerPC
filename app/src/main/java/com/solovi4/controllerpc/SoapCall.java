package com.solovi4.controllerpc;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.util.ArrayList;

public class SoapCall extends AsyncTask<String, Object, String> {
    public String response;
    private String namespace = "http://tempuri.org/";
    private String url;     //"http://192.168.1.38:49001/ICommandExecutorService"
    private String address; //"http://192.168.1.38:49001/"
    private String contractName = "ICommandExecutorService";
    private String soapActionBase = "http://tempuri.org/ICommandExecutorService/";
    private String headerTextBase = "http://tempuri.org/ICommandExecutorService/";
    private String headerText;
    private String methodName;
    private String soapAction;

    public SoapCall(String address) {
        propertyInfos = new ArrayList<>();
        SetAddress(address);
    }

    private void SetAddress(String address) {
        this.address = address;
        this.url = address + contractName;
    }

    ArrayList<PropertyInfo> propertyInfos;
    public void AddProperty(String propertyName, Object value) {
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName(propertyName);
        propertyInfo.setValue(value);
        propertyInfos.add(propertyInfo);
    }

    public void SetMethodName(String methodName) {
        this.methodName = methodName;
        this.soapAction = soapActionBase + methodName;
        this.headerText = headerTextBase + methodName;
    }

    @Override
    protected String doInBackground(String... strings) {
        SoapObject request = new SoapObject(namespace, methodName);
        for (PropertyInfo propertyInfo : propertyInfos) {
            request.addProperty(propertyInfo);
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        Element[] header = new Element[1];
        header[0] = new Element().createElement("http://www.w3.org/2005/08/addressing","Action");
        header[0].setAttribute(null, "mustUnderstand","1");
        //header[0].setAttribute(null, "xmlns", "http://schemas.microsoft.com/ws/2005/05/addressing/none");
        header[0].addChild(Node.TEXT, headerText);
        envelope.headerOut = header;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        //envelope.setOutputSoapObject(request);
        HttpTransportSE transportSE = new HttpTransportSE(url, 5000);
        try{
            transportSE.call(soapAction, envelope);
            response = (String)envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


}
