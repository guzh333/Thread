package test;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;  

/**
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
 * @author guzh
 *
 */
public class ThreadPoolExecutorTest {  
 public static void main(String[] args) {  
  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  
  for (int i = 0; i < 10; i++) {  
   final int index = i;  
   try {  
    Thread.sleep(index * 1000);  
   } catch (InterruptedException e) {  
    e.printStackTrace();  
   }
   cachedThreadPool.execute(new Runnable() {  
    public void run() {  
     System.out.println(index);  
    }  
   });  
  }
 } 
}  

/**
 * (2) newFixedThreadPool
创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
 * @author guzh
 *
 */
class ThreadPoolExecutorTest2 {  
	 public static void main(String[] args) {  
	  ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
	  for (int i = 0; i < 10; i++) {  
	   final int index = i;  
	   fixedThreadPool.execute(new Runnable() {
	    public void run() {  
	     try {  
	      System.out.println(index);  
	      Thread.sleep(2000);  
	     } catch (InterruptedException e) {  
	      e.printStackTrace();  
	     }  
	    }  
	   });
	  }  
	 }  
	}  

/**
 * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
 * @author guzh
 *
 */
 class ThreadPoolExecutorTest33 {  
	 public static void main(String[] args) {  
	  ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
	  scheduledThreadPool.schedule(new Runnable() {  
	   public void run() {  
	    System.out.println("delay 3 seconds");  
	   }  
	  }, 3, TimeUnit.SECONDS);  
	 }  
	}
 /**
  * 表示延迟3秒执行。
定期执行示例代码如下：
  * @author guzh
  *
  */
  class ThreadPoolExecutorTest333 {  
	 public static void main(String[] args) {  
	  ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
	  scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
	   public void run() {  
	    System.out.println("delay 1 seconds, and excute every 3 seconds");  
	   }  
	  }, 1, 3, TimeUnit.SECONDS);  
	 }  
	}  
 
 
  /**
   * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
   * @author guzh
   *
   */
  class ThreadPoolExecutorTest44 {  
	  public static void main(String[] args) {  
	   ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();  
	   for (int i = 0; i < 10; i++) {  
	    final int index = i;  
	    singleThreadExecutor.execute(new Runnable() {  
	     public void run() {  
	      try {  
	       System.out.println(index);  
	       Thread.sleep(2000);  
	      } catch (InterruptedException e) {  
	       e.printStackTrace();  
	      }  
	     }  
	    });  
	   }  
	  }  
	 }  
  
  
  class ThreadPoolExecutorTest7 {  
	  public static void main(String[] args) {  
	   ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();  
	   for (int i = 0; i < 100; i++) {  
	    final int index = i;  
	    singleThreadExecutor.execute(new Runnable() {  
	     public void run() {  
	      try {  
	       while(true) {  
	        System.out.println(index);  
	        Thread.sleep(10 * 1000);  
	       }  
	      } catch (InterruptedException e) {  
	       e.printStackTrace();  
	      }  
	     }  
	    });  
	    try {  
	     Thread.sleep(500);  
	    } catch (InterruptedException e) {  
	     e.printStackTrace();  
	    }  
	   }  
	  }  
	 }  