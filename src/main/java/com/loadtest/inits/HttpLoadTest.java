package com.loadtest.inits;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.loadtest.status.HttpLoadTestStatus;
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
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	protected void runLoadTest() throws InterruptedException, ExecutionException{
		ExecutorService ex = Executors.newFixedThreadPool(concurrentUsers);
		Set<Callable<HttpLoadTestStatus>> callables = new HashSet<Callable<HttpLoadTestStatus>>();
		for (int i = 0; i < totalUsers; i++) {
			HttpLoadTestStatus httpLoadStatus = new HttpLoadTestStatus();
			httpLoadStatus.setLoadTestId(i);
			callables.add(new HttpWorker(url, httpLoadStatus));
		}
		
		List<Future<HttpLoadTestStatus>> futures = ex.invokeAll(callables);
		
		int count = futures.size();
		
		for(Future<HttpLoadTestStatus> future : futures){
			HttpLoadTestStatus httpStatus = future.get();
			System.out.println("Load Test Id "+ httpStatus.getLoadTestId());
			
		    Exception exception = httpStatus.getException();
			if(exception!=null){
		    	System.out.println("Load Test Exception " + exception.getMessage());
		    }
			
			System.out.println("Load Test Status "+httpStatus.getResponseCode());
		}
		
		System.out.println("Total Load Runs "+count);
		
		ex.shutdown();
	}
}
