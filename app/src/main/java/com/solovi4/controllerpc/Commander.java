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

    public void MouseLeftClick() {
        SoapCall soapCall = new SoapCall(address);;
        soapCall.SetMethodName("MouseLeftClick");
        soapCall.execute();
    }

    public void MouseRightClick() {
        SoapCall soapCall = new SoapCall(address);;
        soapCall.SetMethodName("MouseRightClick");
        soapCall.execute();
    }

    public void CancelShutdown() {
        SoapCall soapCall = new SoapCall(address);;
        soapCall.SetMethodName("CancelShutDown");
        soapCall.execute();
    }

    public void Shutdownn() {
        SoapCall soapCall = new SoapCall(address);;
        soapCall.SetMethodName("ShutDown");
        soapCall.execute();
    }

    public void SendText(String text) {
        SoapCall soapCall = new SoapCall(address);;
        soapCall.SetMethodName("SendText");
        soapCall.AddProperty("text", text);
        soapCall.execute();
    }

}
