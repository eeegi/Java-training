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
 

// ���� ���� : http://blog.naver.com/2feelus/220728222140

public class NewFixedThreadPoolTest {
	public static void main(String[] args) {
		if(false) {
		/**
		 * 1) Executors.newFixedThreadPool(10)
		 * �ִ� �����带 10������ ����� Ǯ.  ���ÿ� �Ͼ�� ������ ���� ���� �����Ҷ� ����Ѵ�.
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
		 *  ������ ���� ������ ���� ���� ����� ������Ǯ �������, ���ο� ������ ���� ��û�� ���ö����� �ϳ��� �����带 �����Ѵ�.
		 *  ������ ����� ��������� �ٷ� ������� �ʰ� 1�е��� ����ִٰ� �ٸ� �۾� ��û�� ������ �������. ª�� �ݺ��Ǵ� ��Ÿ���� �۾���û�� ���� ��� �����ϴ�.
		 * 	
		 *  cachedThreadPool�� ������ �ΰ��� �������� Ǯ ������ Ȯ���ߴ��� 2������. 62�� �Ŀ� Ǯ������ Ȯ���ߴ��� 0���� ����Ǿ���. 
		 * 	�� ������ 60�����Ŀ��� �����尡 ������� �����̴�.
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
		 * ���ϳ��� �����带 �����Ѵ�. �ַ� ������ �۾��߿� ���ܻ�Ȳ�� �߻��� ��� ����ó���� ���� ������ ���������� ���� ����Ѵ�.
		 * �ϳ��� �ٸ� �����忡�� ���������� task���� ����ȴ�.
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
		 * �����ð����� �ֱ������� �ݺ��ؾ� �ϴ� ��Ÿ���� �����۾��� ���� ������Ǯ. Timer Ŭ������ ��ü�Ҽ� �ִ� ������ Ǯ����̴�.
		 * 
		 * ���� �ð��� 2�ʸ��� ������ִ� �����带 newScheduleThreadPool�� ����Ͽ� �����Ͽ���.
		 * scheduleAtFixedRate�� runnable ��ü�� ���� ���۽ð�, �ֱ�, �ֱ��� �ð������� �Է¹޴´�.
		 * �� ���������� ���ٽ����� milli second�� �Է����� �޾� ��Ʈ������ ����ϴ� getDate �Լ���  �����ϰ�, scheduleAtFixedRate���� 
		 * runnable��ü�ȿ��� getDate ���� ���� �ð��� ����ϵ��� �Ͽ���
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