import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁 Mutex是一个自定义同步组建，它在同一时刻只允许一个线程占有锁。如果经过cas设置成功（同步状态设置为1）
 * 则代表获取了同步状态，而在tryRelease方法中只是将同步状态重置为0.
 * @author guzh
 *
 */
public class Mutex implements Lock{
	//静态内部类，自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer{
		//是否处于占用状态
		protected boolean isHeldExclusively(){
			return getState() == 1;
		}
		
		//当前状态为0的时候获取锁
		public boolean tryAcquire(int acquires){
			if(compareAndSetState(0, 1)){
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}else{
				return false;
			}
		}
		
		//释放锁，将状态设置为0
		protected boolean tryRelease(int releases){
			if(getState() == 0){
				try {
					throw new IllegalAccessException();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		Condition newCondition(){
			return new ConditionObject();
		}
	}

	//仅需要将操作代理到Sync上即可
	private final Sync sync = new Sync();
	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		sync.tryAcquireNanos(1, unit.toNanos(time));
		return false;
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
	
}
