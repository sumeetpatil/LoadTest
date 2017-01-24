package com.loadtest.workers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Callable;

import com.loadtest.status.HttpLoadTestStatus;

/**
 * Http Worker
 * 
 * @author sumeetpatil
 */
public class HttpWorker implements Callable<HttpLoadTestStatus>, WorkerConstants {
	String url;
	HttpLoadTestStatus httpLoadStatus;

	/**
	 * @param url
	 */
	public HttpWorker(String url, HttpLoadTestStatus httpLoadStatus) {
		this.url = url;
		this.httpLoadStatus = httpLoadStatus;
	}

	@Override
	public HttpLoadTestStatus call() throws Exception {
		sendGet(url);
		return httpLoadStatus;
	}

	/**
	 * Send Get Request
	 * 
	 * @param url
	 * @return
	 */
	private synchronized HttpLoadTestStatus sendGet(String url) {
		String inputLine;
		StringBuffer response = new StringBuffer();
		URL obj = null;
		HttpURLConnection con = null;
		BufferedReader in = null;
		int responseCode = 0;

		try {
			obj = new URL(url);
		} catch (MalformedURLException e) {
			httpLoadStatus.setException(e);
			return httpLoadStatus;
		}

		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e) {
			httpLoadStatus.setException(e);
			return httpLoadStatus;
		}

		try {
			con.setRequestMethod(METHOD_GET);
		} catch (ProtocolException e) {
			httpLoadStatus.setException(e);
			return httpLoadStatus;
		}

		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			httpLoadStatus.setException(e);
			return httpLoadStatus;
		}

		httpLoadStatus.setResponseCode(responseCode);

		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			httpLoadStatus.setException(e);
			return httpLoadStatus;
		}

		try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			httpLoadStatus.setException(e);
			return httpLoadStatus;
		}

		try {
			in.close();
		} catch (IOException e) {
			httpLoadStatus.setException(e);
			return httpLoadStatus;
		}

		return httpLoadStatus;
	}
}
