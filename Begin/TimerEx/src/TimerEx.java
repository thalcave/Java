import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;




public class TimerEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*TimePrinter listener = new TimePrinter(5);
		
		//construct a timer that calls the listener every 10 seconds
		Timer t = new Timer();
		t.schedule((TimerTask) listener,
					0,	//initial delay
					1000);	//subsequent rate*/
		
		TalkingClock tc = new TalkingClock(5);
		tc.start();
		
		TalkingClock2 tc2 = new TalkingClock2(5);
		tc2.start();
		
		int[] counter = new int[1];
		counter[0] = 5;
		TalkingClock4 tc4 = new TalkingClock4();
		tc4.start(counter);


	}

}

//
class TimePrinter extends TimerTask
{
	TimePrinter(int c)
	{
		count = c;
	}

	public void run()
	{
		if (count-- > 0)
		{
			Date now = new Date();
			System.out.println("Date is: " + now);
			//Toolkit.getDefaultToolkit().beep();
		}
		else
		{
			System.exit(0);
		}
	}
	
	private int count;
}

//using inner class
class TalkingClock
{
	public TalkingClock(int interv)
	{
		interval = interv;
		timer = new Timer();
	}
	
	public void start()
	{
		timer.schedule(new TimePrinter(), 0, 1000);
	}
	
	private int interval;
	private Timer timer;
	
	public class TimePrinter extends TimerTask
	{
		public void run()
		{
			if (interval-- > 0)
			{
				Date now = new Date();
				System.out.println("InnerDate is: " + now);
			}
			else
			{
				//System.exit(0);
				TalkingClock.this.timer.cancel();
			}
		}
	}
}


//using local inner class
class TalkingClock2
{
	public TalkingClock2(int interv)
	{
		interval = interv;
		timer = new Timer();
	}
	
	public void start()
	{
		
		@SuppressWarnings("hiding")
		class TimePrinter extends TimerTask
		{
			public void run()
			{
				if (interval-- > 0)
				{
					Date now = new Date();
					System.out.println("InnerDate2 is: " + now);
				}
				else
				{
					//System.exit(0);
					timer.cancel();
				}
			}
		}
		
		timer.schedule(new TimePrinter(), 0, 2000);
	}
	
	private int interval;
	private Timer timer;
	
}

//accessing final variables
class TalkingClock3
{
	public TalkingClock3()
	{
		timer = new Timer();
	}
	
	//	public void start(int interval) error: cannot refer to a non-final variable inside an inner class
	public void start(final int interval)
	{
		
		@SuppressWarnings("hiding")
		class TimePrinter extends TimerTask
		{
			public void run()
			{
				int my_interval = interval;	//because interval cannot be modified: it's final
				if (my_interval-- > 0)
				{
					Date now = new Date();
					System.out.println("InnerDate2 is: " + now);
				}
				else
				{
					//System.exit(0);
					timer.cancel();
				}
			}
		}
		
		timer.schedule(new TimePrinter(), 0, 2000);
	}
	
	private Timer timer;
	
}

//anonymous inner class
class TalkingClock4
{
	public TalkingClock4()
	{
		timer = new Timer();
	}
	
	public void start(final int[] counter)
	{
		//anonymous inner class
		TimerTask ttask = new TimerTask()
		{
			public void run()
			{
				--counter[0];
				if (counter[0] > 0)
				{
					Date now = new Date();
					System.out.println("AnonDate is: " + now);
				}
				else
				{
					//System.exit(0);
					timer.cancel();
				}
			}
		};
		
		timer.schedule(ttask, 0, 1500);
		
	}
	
	private Timer timer;
	
}

