package com.kmlee.semaphore;

import java.util.concurrent.Semaphore;

public class SemaThread extends Thread {
	private Semaphore semaphore;
	
	public SemaThread(Semaphore semaphore) {
		super();
		this.semaphore = semaphore;
	}
	public void run() {
		try {
			semaphore.acquire();
			Thread.sleep(5000);
			System.out.printf("¿Ã∏ß %s \n", getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
