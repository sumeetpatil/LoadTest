package com.loadtest.inits;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.loadtest.workers.HttpWorker;

/**
 * Load Test for http protocol
 * @author sumeetpatil
 */
public class HttpLoadTest {
	int totalUsers;
	int concurrentUsers;
	String url;
	
	
	/**
	 * @param totalUsers
	 * @param concurrentUsers
	 * @param url 
	 */
	HttpLoadTest(int totalUsers, int concurrentUsers, String url){
		this.concurrentUsers = concurrentUsers;
		this.totalUsers = totalUsers;
		this.url = url;
	}

	/**
	 * Run Http Load Test
	 */
	protected void runLoadTest(){
		ExecutorService ex = Executors.newFixedThreadPool(concurrentUsers);
		for (int i = 0; i < totalUsers; i++) {
			ex.submit(new HttpWorker(url));
		}
		
		ex.shutdown();
	}
}
