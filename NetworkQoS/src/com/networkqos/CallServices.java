/**
 * 
 */
package com.networkqos;

import java.io.IOException;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class CallServices {
	private static String NAMESPACE="http://networkqos.com/";
	private static String URL="http://192.168.43.202:8080/NetworkQoSService/NewWebService?WSDL";
	
	/**
	 * login service
	 * @param username
	 * @param password
	 * @param method
	 * @return String
	 */
	public static String loginService(String username,String password,String method) {
		String restex=null;
		SoapObject soap=new SoapObject(NAMESPACE, method);
		soap.addProperty("username",username);
		soap.addProperty("password",password);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try { 
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
			restex=primitive.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return "error";
			
		}
		
		return restex;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param method
	 * @return
	 */
	public static String registerService(String username,String password,String email,String method) {
		String restex=null;
		SoapObject soap=new SoapObject(NAMESPACE, method);
		soap.addProperty("username",username);
		soap.addProperty("password",password);
		soap.addProperty("email",email);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
			restex=primitive.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return "error";
			
		}
		
		return restex;
	}
	/**
	 * 
	 * @param bandwidth
	 * @param phonetype
	 * @param networkname
	 * @param simstate
	 * @param networktype
	 * @param osversion
	 * @param username
	 * @param method
	 * @return
	 */
	public static String getVideosFromMobileInfo(String bandwidth,String phonetype,String networkname,String simstate,String networktype,String osversion,String username,String method) {
		String list = null;
		SoapObject soap=new SoapObject(NAMESPACE,method);
		soap.addProperty("bandwidth",bandwidth);
		soap.addProperty("networkname",networkname);
		soap.addProperty("phonetype",phonetype);		
		soap.addProperty("simstate",simstate);
		soap.addProperty("networktype",networktype);
		soap.addProperty("osversion",osversion);
		soap.addProperty("username",username);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive pri=(SoapPrimitive) envelope.getResponse();
			list=pri.toString();
		} catch (IOException e) {			
			e.printStackTrace();
			return list;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return list;
		}

		return list;
		
	}
	/**
	 * 
	 * @param filename
	 * @param username
	 * @param method
	 * @return
	 */
	public static String treansCoder(String filename,String username,String method) {
		String list = null;
		SoapObject soap=new SoapObject(NAMESPACE,method);		
		soap.addProperty("filename",filename);		
		soap.addProperty("username",username);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive pri=(SoapPrimitive) envelope.getResponse();
			list=pri.toString();
		} catch (IOException e) {			
			e.printStackTrace();
			return list;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return list;
		}

		return list;
		
	}

}
