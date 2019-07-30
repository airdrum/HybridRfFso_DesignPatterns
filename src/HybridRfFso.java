import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.BSONObject;
import org.json.JSONObject;

import utility.*;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

class FsoThroughput implements Runnable{
	private static SshClass sshMngrfso = null;
	public FsoThroughput(SshClass sshMngrfso ) {
		this.sshMngrfso = sshMngrfso;
	}
	
	private volatile static boolean isTerminated = true;
	public boolean isTerminated() {
		return done;
	}

	private volatile static boolean done = false;
	
	public void shutdown() {
		done = true;
	}
	static Mongo mongo = new Mongo("localhost", 27017);
	static DB db = mongo.getDB("mydb");
	static DBCollection collection = db.getCollection("FsoThroughput");
	

	private final static Object lock = new Object();
	
	public static void getThroughput() {
		synchronized (lock) {
			JSONObject objName = new JSONObject();
			String fsoLines[]  = sshMngrfso.recvData().split("\\r?\\n");
			int count =1;
			while(fsoLines.length<2) {
				fsoLines  = sshMngrfso.recvData().split("\\r?\\n");
	
				try {
					System.out.println(">> Retry count for FSO: " + count + " at Thread-" + Thread.currentThread().getId());
					Thread.sleep(500);
					count++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				if(count>5) {
					done = true;
					System.out.println("----BYE FSO-------");
					break;
				} 
			}
	//		if(rfLines.length<2) {
	//			int retryCount = 3;
	//
	//			System.out.println("----------------FSOOOOOOO-------");
	//			try {
	//				Thread.sleep(1000);
	//			} catch (InterruptedException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//			while(retryCount<3) {
	//				System.out.println(">> FSO is retried - " +retryCount);
	//				rfLines  = sshMngrfso.recvData().split("\\r?\\n");
	//				try {
	//					Thread.sleep(2000);
	//				} catch (InterruptedException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				}
	//				if(rfLines.length<2)
	//					retryCount++;
	//				else
	//					break;
	//			}
	//			if(rfLines.length<2) {
	//				done = true;
	//				System.out.println("----BYE FSO-------");
	//			}
	//			
	//		}
			for (String name:fsoLines)
	        {
				String innerFso[]=name.split("\\s+");
				int indexRf = Arrays.asList(innerFso).indexOf("sec");
				if (indexRf>0) {
					System.out.println("FSO: " +innerFso[0]+","+innerFso[indexRf+3]+" "+innerFso[indexRf+4]);
					objName.put("time",innerFso[0]);
					objName.put("fso",innerFso[indexRf+3]);
					objName.put("fsopacket",innerFso[indexRf+4]);
					DBObject dbObject = (DBObject)JSON.parse(objName.toString());
				    collection.insert(dbObject);
				}
					
	        }
		}
	}
	@Override
	public void run() {
		while(!done) {
			try {
				getThroughput();
				Thread.sleep(500);
			}catch (InterruptedException e) {
				isTerminated=false;
			}
			
		}

		System.out.println("# FSO Stopped.");
	}
}





class RfThroughput implements Runnable{
	private static SshClass sshMngrrf = null;
	public RfThroughput(SshClass sshMngrrf ) {
		this.sshMngrrf = sshMngrrf;
	}
	
	private static volatile boolean isTerminated = true;
	public boolean isTerminated() {
		return done;
	}
	
	 private volatile static boolean done = false;
	 
	  
	 
	  public void shutdown() {
	    done = true;
	    isTerminated = true;
	  }
	  
	  
	static Mongo mongo = new Mongo("localhost", 27017);
	static DB db = mongo.getDB("mydb");
	static DBCollection collection = db.getCollection("RfThroughput");
	
	private final static Object lock = new Object();
	
	public static void getThroughput() {
		synchronized (lock) {
			JSONObject objName = new JSONObject();
			String rfLines[]  = sshMngrrf.recvData().split("\\r?\\n");
			
			int count =1;
			while(rfLines.length<2) {
				rfLines  = sshMngrrf.recvData().split("\\r?\\n");

				try {
					System.out.println("Retry count for RF: " + count + " at Thread-" + Thread.currentThread().getId());
					Thread.sleep(500);
					count++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(count>5) {
					done = true;
					System.out.println("----BYE RF-------");
					break;
				}
			}

			
//			if(rfLines.length<2) {
//				int retryCount = 3;
//				System.out.println("---------------------RF--------------");
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				while(retryCount<3) {
//					System.out.println(">> RF is retried - " +retryCount);
//					rfLines  = sshMngrrf.recvData().split("\\r?\\n");
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					if(rfLines.length<2)
//						retryCount++;
//					else
//						break;
//				}
//				if(rfLines.length<2) {
//					done = true;
//					System.out.println("----BYE RF-------");
//				}
//				
//			}
				
			for (String name:rfLines)
	        {
				String innerRf[]=name.split("\\s+");
				int indexRf = Arrays.asList(innerRf).indexOf("sec");
				if (indexRf>0) {
					System.out.println("RF: " +innerRf[0]+","+innerRf[indexRf+3]+" "+innerRf[indexRf+4]);
					objName.put("time",innerRf[0]);
					objName.put("rf",innerRf[indexRf+3]);
					objName.put("rfpacket",innerRf[indexRf+4]);
					DBObject dbObject = (DBObject)JSON.parse(objName.toString());
				    collection.insert(dbObject);
				}
					
	        }
		}
	}
	@Override
	public void run() {
		while(!done) {
			try {
				getThroughput();
				Thread.sleep(500);
			}catch (InterruptedException e) {
				isTerminated=false;
			}
			
		   
		}
		System.out.println("# RF Stopped.");
	}
}

class Weather implements Runnable{
	private static RfThroughput rf = null;
	private static FsoThroughput fso = null;
	public Weather(RfThroughput rf ,FsoThroughput fso) {
		this.rf = rf;
		this.fso = fso;
	}
	private volatile static boolean isTerminated = true;
	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
	private volatile static boolean done = false;
	 
	  
	 
	  public void shutdown() {
	    done = true;
	  }
	static Mongo mongo = new Mongo("localhost", 27017);
	static DB db = mongo.getDB("mydb");
	static DBCollection collection = db.getCollection("WeatherData");
	

	public static DBObject jsonout = null;
	public static WeatherClass weather = new WeatherClass();

	
	
	public static void getWeather() {
		weather.clearWeatherArray();
		weather.setWeatherOutputData();
	    jsonout = weather.getWeatherDataDBObject();
		// TODO Auto-generated method stub
	    DateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		Date date = new Date();
	    String currentTime = df.format(date);
	    jsonout.put("time", currentTime);
		
	    System.out.println("Weather:" + jsonout.get("time")
			    +",InTemp:"+jsonout.get("InsideTemperature")
			    +",InHum:"+ jsonout.get("InsideHumidity")
			    +",OutTemp:"+ jsonout.get("OutsideTemperature")
			    +",WindS:"+ jsonout.get("WindSpeed")
	    		+",WindD:"+ jsonout.get("WindDirection")
	    		+",OutHum:"+ jsonout.get("OutsideHumidity")
	    		+",UV:"+ jsonout.get("UV")
	    		+",SolRad:"+ jsonout.get("SolarRadiation"));
	    DBObject dbObject = (DBObject)JSON.parse(jsonout.toString());
	    collection.insert(dbObject);
	    try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void checkTermination() {
		if((rf.isTerminated()&&fso.isTerminated())) {
			done = true;
		}
			
	}
	@Override
	public void run() {
		while(!done) {
			try {
				getWeather();
				checkTermination();
				Thread.sleep(1);
			}catch (InterruptedException e) {
				isTerminated=false;
			}
		   
		}
		System.out.println("# Weather Stopped.");
	}
}



public class HybridRfFso {
	private static final boolean debug = false;
	public static void main(String[] args) {
			SshClass sshMngr;
			if(debug){
				sshMngr = new SshClass("10.100.93.15", "pi","raspberry");
			}
			SshClass sshMngrrf = new SshClass("10.100.93.16", "pi","raspberry");
			SshClass sshMngrfso = new SshClass("10.100.93.16", "pi","raspberry");
			sshMngrfso.sendCommand("killall iperf;iperf -s -u -i0.5 -p4000 |ts '%Y%m%d-%H:%M:%.S'");
			sshMngrrf.sendCommand("killall iperf;iperf -s -u -i0.5 -p5000 | ts '%Y%m%d-%H:%M:%.S'");//////////////////////////////
			
			if(debug) {
				sshMngr.sendCommand("killall iperf;iperf -c 192.168.100.21 -u -b100M -t30 -p4000 & iperf -c 192.168.2.21 -u -b50M -t30 -p5000 &");
			}else {
				try {
					Process p = Runtime.getRuntime().exec(new String[]{"bash","-c","killall iperf;iperf -c 192.168.100.21 -u -b100M -i1 -t21600 -p4000 & iperf -c 192.168.2.21 -u -b50M -i1 -t21600 -p5000 &"});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
	
			
			RfThroughput rf= new RfThroughput(sshMngrrf);
			FsoThroughput fso= new FsoThroughput(sshMngrfso);
			Weather worker = new Weather(rf,fso);

			
			Thread t1 = new Thread(worker);
			Thread t2 = new Thread(rf);
			Thread t3 = new Thread(fso);


			ArrayList<Thread> threadList = new ArrayList<Thread>();
			threadList.add(t1);
			threadList.add(t2);
			threadList.add(t3);

			
			for (Thread _thread:threadList)
	        {
				_thread.start();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			
			for (Thread _thread:threadList)
	        {
				try {
					_thread.join();
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
			
			rf.shutdown();
			fso.shutdown();
			worker.shutdown();

			if(debug) {
				sshMngr.close();
			}
			sshMngrrf.close();
			sshMngrfso.close();

	}

}