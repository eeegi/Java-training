
public class Task implements Runnable {

	private String name;
	public Task(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		System.out.println("Thread ID = " + Thread.currentThread().getName() 
				+ ", task name : " + name + " start");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread ID = " + Thread.currentThread().getName() 
				+ ", task name : " + name + " end");
	}

}
