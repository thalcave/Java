import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

class SynchronizedCounter
{
	public synchronized void increment()
	{
		++counter;
	}
	
	public synchronized void decrement()
	{
		--counter;
	}
	
	public synchronized int counter()
	{
		return counter;
	}
	
	public synchronized int prefixIncrement()
	{
		++counter;
		return counter;
	}
	
	private int counter = 0; 
}

class CounterThread implements Runnable
{
	public CounterThread(String s, SynchronizedCounter sc)
	{
		str = s;
		sCounter = sc;
	}
	
	@Override
	public void run() {
		try {
            for (int i = 0; i < 20; ++i) {
                Thread.sleep(1000);
                System.out.print(str + " " + sCounter.prefixIncrement() + "\n" + "..");
                //sCounter.increment();
            }

        } catch (InterruptedException e) {
            System.out.println(" interrupted");
        }
	}

	
	private String str;
	private SynchronizedCounter sCounter;
}


class RunnableThread implements Runnable
{
	public RunnableThread(String s, List<Integer> l, Semaphore se)
	{
		str = s;
		listInt = l;
		sem = se;
	}
	
	public void run()
	{
/*		try {
			while (!Thread.interrupted())
			{
				System.out.println("Running thread: " + str);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println(str + " interrupted!");
			return;
		}*/
		
		try {
			while (!Thread.interrupted())
			{
				{
					sem.acquire();
					System.out.println("Running thread: " + str + " " + listInt.get(0));
					Integer tmp = listInt.get(0);
					++tmp;
					listInt.set(0,  tmp);
					sem.release();
				}
			}
			System.out.println(str + " interrupted!");
		} catch(InterruptedException e) {
			System.out.println(str + " interrupted exception");
			sem.release();
		}
	}
	
	private String str;
	private List<Integer> listInt;
	private Semaphore sem;
}

public class ThreadEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> sharedList = new LinkedList<Integer>();
		
		sharedList.add(10);
		
		Semaphore sem = new Semaphore(1);
		
		RunnableThread rt1 = new RunnableThread("Thread1", sharedList, sem);
		RunnableThread rt2 = new RunnableThread("Thread2", sharedList, sem);
		
		
		Thread t1 = new Thread(rt1);
		Thread t2 = new Thread(rt2);
		
		//start threads
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(4000);
			
			t1.interrupt();
			
			//Thread.sleep(3000);
			t2.interrupt();
			
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		{
			SynchronizedCounter sCounter = new SynchronizedCounter();
			
			Thread th1 = new Thread(new CounterThread("ct1", sCounter));
			Thread th2 = new Thread(new CounterThread("ct2", sCounter));
			th1.start();
			th2.start();
			
			try {
				th1.join();
				th2.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		
		System.out.println(Thread.currentThread());
		

	}

}
