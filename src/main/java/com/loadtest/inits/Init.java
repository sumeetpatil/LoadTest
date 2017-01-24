package com.loadtest.inits;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import com.loadtest.utils.FileUtils;

/**
 * Load Test Init
 * 
 * @author sumeetpatil
 */
public class Init implements InitConstants {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Properties pro = FileUtils.getProperties();
		String protocol = pro.get(PROTOCOL).toString();
		String concurrentUsers = pro.get(CONCURRENTUSERS).toString();
		String totalUsers = pro.get(TOTALUSERS).toString();
		String url = pro.get(URL).toString();

		if (protocol.equals(HTTP_PROTOCOL)) {
			HttpLoadTest httpLoadTest = new HttpLoadTest(Integer.parseInt(totalUsers),
					Integer.parseInt(concurrentUsers), url);
			httpLoadTest.runLoadTest();
		}
	}
}
