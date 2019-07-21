import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	private volatile boolean isTerminated = true;
	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	static Mongo mongo = new Mongo("localhost", 27017);
	static DB db = mongo.getDB("mydb");
	static DBCollection collection = db.getCollection("FsoThroughput");
	

	
	public static void getThroughput() {
		JSONObject objName = new JSONObject();
		String rfLines[]  = sshMngrfso.recvData().split("\\r?\\n");
		for (String name:rfLines)
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
	@Override
	public void run() {
		while(isTerminated) {

			getThroughput();
			
		    try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}





class RfThroughput implements Runnable{
	private static SshClass sshMngrrf = null;
	public RfThroughput(SshClass sshMngrrf ) {
		this.sshMngrrf = sshMngrrf;
	}
	
	private volatile boolean isTerminated = true;
	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	static Mongo mongo = new Mongo("localhost", 27017);
	static DB db = mongo.getDB("mydb");
	static DBCollection collection = db.getCollection("RfThroughput");
	

	
	public static void getThroughput() {
		JSONObject objName = new JSONObject();
		String rfLines[]  = sshMngrrf.recvData().split("\\r?\\n");
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
	@Override
	public void run() {
		while(isTerminated) {
			

			getThroughput();
			
		    try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Weather implements Runnable{
	private volatile boolean isTerminated = true;
	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	Mongo mongo = new Mongo("localhost", 27017);
	DB db = mongo.getDB("mydb");
	DBCollection collection = db.getCollection("WeatherData");
	

	DBObject jsonout = null;
	WeatherClass weather = new WeatherClass();

	@Override
	public void run() {
		while(isTerminated) {
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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class HybridRfFso {
	
	public static void main(String[] args) {

//		SshClass sshMngr = new SshClass("10.100.93.28", "pi","raspberry");
		SshClass sshMngrrf = new SshClass("10.100.93.16", "pi","raspberry");
		SshClass sshMngrfso = new SshClass("10.100.93.16", "pi","raspberry");
		sshMngrfso.sendCommand("killall iperf;iperf -s -u -i0.1 -p4000 |ts '%Y%m%d-%H:%M:%.S'");
		sshMngrrf.sendCommand("killall iperf;iperf -s -u -i0.1 -p5000 | ts '%Y%m%d-%H:%M:%.S'");
		
//		sshMngr.sendCommand("killall iperf;iperf -c 192.168.100.21 -u -b100M -t999999 -p4000 & iperf -c 192.168.2.178 -u -b50M -t9999 -p5000 &");
		try {
			Process p = Runtime.getRuntime().exec(new String[]{"bash","-c","killall iperf;iperf -c 192.168.100.21 -u -b100M -i1 -t9999999 -p4000 & iperf -c 192.168.2.178 -u -b40M -i1 -t9999999 -p5000 &"});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String fsostr,rfstr,fsoparse="",rfparse="";
		
		boolean exitFlag = true; 
		Weather worker = new Weather();
		RfThroughput rf= new RfThroughput(sshMngrrf);
		FsoThroughput fso= new FsoThroughput(sshMngrfso);
		Thread t1 = new Thread(worker);
		Thread t2 = new Thread(rf);
		Thread t3 = new Thread(fso);
		t1.start();
		t2.start();
		t3.start();
		
		
	}

}