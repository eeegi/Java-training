import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
 
public class NewFixedThreadPoolTest {
	public static void main(String[] args) {
		/**
		 * 1) Executors.newFixedThreadPool(10)
		 * 최대 쓰레드를 10개까지 만드는 풀.  동시에 일어나는 업무의 량이 비교적 일정할때 사용한다.
		 */
		ExecutorService executorService = 
				Executors.newFixedThreadPool(5);
		for (int i = 0; i <5;i++) {
			executorService.execute(new Task("Task" + i));
		}
		executorService.shutdown();
		while(!executorService.isTerminated()) {
//			System.out.println("wating");
		}
		System.out.println("All task are completed");
		
		
		ExecutorService executorService1 = 
				Executors.newCachedThreadPool();
		for (int i = 0;i<3;i++) {
			executorService1.execute(new Task("Task"+i));
		}
		System.out.println("cached thread size was " + ((ThreadPoolExecutor)executorService1).getPoolSize());
		try {
			Thread.sleep(7*1000);
			System.out.println("cached thread size was " + ((ThreadPoolExecutor)executorService1).getPoolSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		executorService1.shutdown();
		while(!executorService1.isTerminated()) {
			
		}
		System.out.println("Cached tasks are completed");
	}
}