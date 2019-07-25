package deneme;

import java.util.Random;
import java.util.Vector;
class Container3 implements Runnable {
	  private volatile static boolean done = false;
	 
	  
	 
	  public void shutdown() {
	    done = true;
	  }
	 
	  @Override public void run() {
	    Random number = new Random(123L);
	    int i = 1000;
	    while (!done && i > 0) {
	      System.out.println(i+" FROM " +Thread.currentThread().getId());
	      try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      i--;
	    }
	  }
}

class Container2 implements Runnable {
	  private volatile static boolean done = false;
	 
	  
	 
	  public void shutdown() {
	    done = true;
	  }
	 
	  @Override public void run() {
	    Random number = new Random(123L);
	    int i = 1000;
	    while (!done && i > 0) {
	      System.out.println(i+" FROM " +Thread.currentThread().getId());
	      try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      i--;
	    }
	  }
}

public final class Container {
	  
	 
	  public static void main(String[] args) throws InterruptedException {
	    Container2 container = new Container2();
	    Container3 container2 = new Container3();
	    Thread thread = new Thread(container);
	    Thread thread2 = new Thread(container2);
	    thread.start();
	    thread2.start();
	    Thread.sleep(5000);
	    container.shutdown();
	    container2.shutdown();
	  }
	}