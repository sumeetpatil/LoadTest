package com.loadtest.workers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Http Worker
 * @author sumeetpatil
 */
public class HttpWorker implements Callable<Exception>, WorkerConstants{
	String url;
	
	/**
	 * @param url
	 */
	public HttpWorker(String url){
		this.url = url;
	}

	@Override
	public Exception call() throws Exception {
		sendGet(url);
		return null;
	}
	
	/**
	 * Send Get Request
	 * @param url
	 * @throws IOException
	 */
	private void sendGet(String url) throws IOException{

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod(METHOD_GET);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());
	}
}
