package com.networkqos;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class CallAnotherService {
	public static String NAMESPACE = "http://tce.com/";
	public static String url = "http://192.168.43.202/tcecse/tces?WSDL";

	public static String responseService(String uname,String pass,String method) {
		String res="";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		soap.addProperty("username", uname);
		soap.addProperty("password", pass);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(url);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			res = primitive.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return res;
	}
}
