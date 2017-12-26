package com.kmlee.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	/*Mutex: 한번에 하나의 thread만 critical section에 진입 가능 (lock, unlock)
	  	lock: 하나의 thread만 lock을 획득할 수 있고, 다른 쓰레드는 lock이 풀릴때 까지 대기.
 		tryLock: lock의 성질에 의해 진입할 수 없는 경우, false등을 반환하여 lock획득에 실패했음을 알리고 즉시 리턴. 
		(아무도 lock을 안잡았으면 잡고, lock이 잡혀있으면 즉시 리턴)
		timedTryLock: tryLock과 동일하나 lock 획득을 위해 일정시간 대기한다.
		unlock: lock을 해제

	Semaphore: N개의 thread만 critical section에 진입 가능 (acquire, release)
		acquire: lock과 동일하나 N개의 thread가 진입할 수 있다.
		tryAcquire: semaphore의 성질에 의해 진입할 수 없는 경우, false등을 반환하여 lock획득에 실패했음을 알리고 즉시 리턴.
		(semaphore를 획득할 수 있으면 획득, 아니면 즉시 리턴)
		timedTryAcquire: tryAcquire와 동일하나 semaphore 획득을 위해 일정시간 대기한다.
		release: acquire로 얻은 semaphore(lock) 반납.
	
	ConditionVariable: 상태 변수 (wait, notify(또는 signal)). Lock 객체와 연계하여 사용됨.
		wait: 특정 상태가 변경될 때까지 대기함(notify(signal) 또는 notifyAll(broadcast) 이 호출될 때까지 기다림).
		timedWait: wait와 동일하나 특정 시간 동안만 대기한다.
		notify(or signal): 대기 상태에 있는(wait를 호출한) thread를 깨운다. 동시에 여러 thread가 대기하고 있는 경우 임의의 thread 하나만 깨어난다.
		notifyAll(or broadcast): 대기 상태에 있는(wait를 호출한) 모든 thread를 깨운다.*/
	
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
