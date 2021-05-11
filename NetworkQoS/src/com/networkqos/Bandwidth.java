/**
 * 
 */
package com.networkqos;

import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author santhosh.t
 *
 */
public class Bandwidth {
	/*
	 * variable declaration
	 */
	float bandwidth;
	long contentLength;
	long startTime,endTime;
	
	/*
	 *bandwidth calculator
	 */
	public String getBandwidth(String url) {
		try {			
			startTime = System.currentTimeMillis();						
			HttpGet httpRequest = new HttpGet(new URL(url).toURI());
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpClient.execute(httpRequest);				
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity;
			bufHttpEntity = new BufferedHttpEntity(entity);
			endTime = System.currentTimeMillis();			
			contentLength = bufHttpEntity.getContentLength();
			bandwidth = (contentLength / (endTime-startTime));
			return String.valueOf((int)bandwidth);
			}
			catch(Exception e){
				return "url not found";
			}
		
	}
	
}
