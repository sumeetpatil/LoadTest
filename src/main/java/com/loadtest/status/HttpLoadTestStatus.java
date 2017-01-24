package com.loadtest.status;

/**
 * Status for Http Load Test
 * 
 * @author sumeetpatil
 *
 */
public class HttpLoadTestStatus {
	int responseCode;
	Exception exception;
	double timeTaken;
	long loadTestId;

	public long getLoadTestId() {
		return loadTestId;
	}

	public void setLoadTestId(long loadTestId) {
		this.loadTestId = loadTestId;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public double getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(double timeTaken) {
		this.timeTaken = timeTaken;
	}
}
