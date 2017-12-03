import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
 

// 참고 블러그 : http://blog.naver.com/2feelus/220728222140

public class NewFixedThreadPoolTest {
	public static void main(String[] args) {
		if(false) {
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
		
		
		/**
		 *  2) Executors.newCachedThreadPool()
		 *  쓰레드 수의 제한을 두지 않은 방식의 쓰레드풀 방식으로, 새로운 쓰레드 시작 요청이 들어올때마다 하나씩 쓰레드를 생성한다.
		 *  업무가 종료된 쓰레드들은 바로 사라지지 않고 1분동안 살아있다가 다른 작업 요청이 없으면 사라진다. 짧고 반복되는 스타일의 작업요청이 들어올 경우 유용하다.
		 * 	
		 *  cachedThreadPool로 쓰레드 두개를 생성한후 풀 갯수를 확인했더니 2개였다. 62초 후에 풀갯수를 확인했더니 0개로 변경되었다. 
		 * 	그 이유는 60초이후에는 쓰레드가 사라지기 때문이다.
		 */
		ExecutorService executorService1 = 
				Executors.newCachedThreadPool();
		for (int i = 0;i<3;i++) {
			executorService1.execute(new Task("Task"+i));
		}
		System.out.println("cached thread size was " + ((ThreadPoolExecutor)executorService1).getPoolSize());
		try {
			Thread.sleep(70*1000);
			System.out.println("cached thread size was " + ((ThreadPoolExecutor)executorService1).getPoolSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		executorService1.shutdown();
		while(!executorService1.isTerminated()) {
			
		System.out.println("Cached tasks are completed");
		
		/*
		 * 3) Executors.newSingleThreadExecutor()
		 * 단하나의 쓰레드를 생성한다. 주로 쓰레드 작업중에 예외상황이 발생한 경우 예외처리를 위한 쓰레드 생성용으로 많이 사용한다.
		 * 하나의 다른 쓰레드에서 순차적으로 task들이 수행된다.
		 */
		}
		ExecutorService executorService2 = Executors.newSingleThreadExecutor();
		executorService2.execute(()-> {
			try {
				Thread.sleep(1*1000);
				System.out.println("Thread via execute");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		executorService2.execute(()-> {
			try {
				Thread.sleep(1*1000);
				System.out.println("Thread via execute2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		executorService2.execute(()-> {
			try {
				Thread.sleep(1*1000);
				System.out.println("Thread via execute3");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		executorService2.shutdown();
		while(!executorService2.isTerminated()) {
			
		}
		System.out.println("Single tasks are completed");
		
		/**
		 * 
		 * 4) Executors.newScheduledThreadPool()
		 * 일정시간마다 주기적으로 반복해야 하는 스타일의 동시작업을 위한 쓰레드풀. Timer 클래스를 대체할수 있는 쓰레드 풀방식이다.
		 * 
		 * 현재 시각을 2초마다 출력해주는 쓰레드를 newScheduleThreadPool을 사용하여 구현하였다.
		 * scheduleAtFixedRate는 runnable 객체와 최초 시작시간, 주기, 주기의 시간단위를 입력받는다.
		 * 위 예제에서는 람다식으로 milli second를 입력으로 받아 스트링으로 출력하는 getDate 함수를  구현하고, scheduleAtFixedRate안의 
		 * runnable객체안에서 getDate 통해 현재 시간을 출력하도록 하였다
		 */
		}
		ScheduledExecutorService scheduledExecutorService =
				Executors.newScheduledThreadPool(2);
		final Function<Long,String> getData =(millis)-> {
			Date data = new Date(millis);
			SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy h:mm:ss,a", Locale.ENGLISH);
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			String formattedDate = sdf.format(data);
			return formattedDate;
		};
		scheduledExecutorService.scheduleAtFixedRate(()->
		System.out.println("Time="+getData.apply(System.currentTimeMillis())),
		0,2,TimeUnit.SECONDS);
	}
}