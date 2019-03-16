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

    public void MoveCursor(int dx, int dy) {
        SoapCall soapCall = new SoapCall(address);;
        soapCall.SetMethodName("MoveCursor");
        soapCall.AddProperty("dx", dx);
        soapCall.AddProperty("dy", dy);
        soapCall.execute();
    }

}
