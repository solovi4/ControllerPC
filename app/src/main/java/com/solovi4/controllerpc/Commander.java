package com.solovi4.controllerpc;

public class Commander {
    private String address;
    public Commander(String address) {
        this.address = address;
    }

    public void SetVolume(int volume) {
        SoapCall soapCall = new SoapCall(address);;
        soapCall.SetMethodName("SetVolume");
        soapCall.AddProperty("level", volume);
        soapCall.execute();
    }

}
