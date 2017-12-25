package com.kmlee.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

/*	 java.util.concurrent.Semaphore: 
	  상호배제를 하면서 동시에 수행할 수 있는 스레드의 개수를 설정할 수 있는 클래스 
     Semaphore(int permits): 동시 수행 개수 지정 
	    acquire() : p연산
	    release() ​: v연산 */
	public static void main(String[] args) {
		
/*		Critical Section(임계영역) : 공유자원을 사용하는 코드영역

		Mutual Exclusion(상호배제) : 실행 중인 다른 스레드가 사용중인 자원은 수정하면 안된다.

		Synchronous : 작업의 순서 결정

		Dead Lock : 결코 발생할 수 없는 사건을 무한정 기다리는 것

		Semaphore : 동시에 실행되는 스레드의 개수를 설정
*/
//		3개만 실행되고, 1 후 자원 해제 되면 나머지 2개 실행
		Semaphore sem = new Semaphore(3);
		
		SemaThread th1 = new SemaThread(sem);
		SemaThread th2 = new SemaThread(sem);
		SemaThread th3 = new SemaThread(sem);
		SemaThread th4 = new SemaThread(sem);
		SemaThread th5 = new SemaThread(sem);
		SemaThread th6 = new SemaThread(sem);
		SemaThread th7 = new SemaThread(sem);
		SemaThread th8 = new SemaThread(sem);
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		th7.start();
		th8.start();
	}
}
