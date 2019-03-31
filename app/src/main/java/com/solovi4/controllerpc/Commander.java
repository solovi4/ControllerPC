package com.solovi4.controllerpc;

public class Commander {
    private String address;
    public Commander(String address) {
        this.address = address;
    }

    public void SetVolume(int volume) {
        SoapCall soapCall = new SoapCall(address);
        soapCall.SetMethodName("SetVolume");
        soapCall.AddProperty("level", volume);
        soapCall.execute();
    }

    public void MoveCursor(int dx, int dy) {
        SoapCall soapCall = new SoapCall(address);
        soapCall.SetMethodName("MoveCursor");
        soapCall.AddProperty("dx", dx);
        soapCall.AddProperty("dy", dy);
        soapCall.execute();
    }

    private void callMethodWithoutParametrs(String methodName) {
        SoapCall soapCall = new SoapCall(address);
        soapCall.SetMethodName(methodName);
        soapCall.execute();
    }

    public void MouseLeftButtonDown() {
        callMethodWithoutParametrs("MouseLeftButtonDown");
    }

    public void MouseLeftButtonUp() {
        callMethodWithoutParametrs("MouseLeftButtonUp");
    }

    public void MouseRightButtonDown() {
        callMethodWithoutParametrs("MouseRightButtonDown");
    }

    public void MouseRightButtonUp() {
        callMethodWithoutParametrs("MouseRightButtonUp");
    }

    public void CancelShutdown() {
        callMethodWithoutParametrs("CancelShutDown");
    }

    public void Shutdown() {
        callMethodWithoutParametrs("ShutDown");
    }

    public void SendText(String text) {
        SoapCall soapCall = new SoapCall(address);
        soapCall.SetMethodName("SendText");
        soapCall.AddProperty("text", text);
        soapCall.execute();
    }


}
