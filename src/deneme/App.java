package deneme;

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
	
	static double innerFso = 90;
	double random;
	public static void getThroughput() {
		innerFso = innerFso -3 + Math.random() * (3 - (-3));
		DateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		Date date = new Date();
		String currentTime = df.format(date);
		JSONObject objName = new JSONObject();
		System.out.println("FSO: " +currentTime+","+Double.toString(Math.round(innerFso)) +",Mbits/sec");
		objName.put("time",currentTime);
		objName.put("fso",Double.toString(Math.round(innerFso)));
		objName.put("fsopacket","Mbits/sec");
		DBObject dbObject = (DBObject)JSON.parse(objName.toString());
	    collection.insert(dbObject);
	    
        
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
private static SshClass sshMngrfso = null;
	
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
	
	static double innerFso = 40;
	double random;
	public static void getThroughput() {
		innerFso = innerFso -3 + Math.random() * (15 - (-15));
		DateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		Date date = new Date();
		String currentTime = df.format(date);
		JSONObject objName = new JSONObject();
		System.out.println("RF: " +currentTime+","+Double.toString(Math.round(innerFso)) +",Mbits/sec");
		objName.put("time",currentTime);
		objName.put("rf",Double.toString(Math.round(innerFso)));
		objName.put("rfpacket","Mbits/sec");
		DBObject dbObject = (DBObject)JSON.parse(objName.toString());
	    collection.insert(dbObject);
	    
        
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

public class App {
	
	public static void main(String[] args) {


		//Weather worker = new Weather();
		RfThroughput rf= new RfThroughput();
		FsoThroughput fso= new FsoThroughput();
		Thread t1 = new Thread(rf);
		Thread t2 = new Thread(fso);
		//Thread t3 = new Thread(fso);
		t1.start();
		t2.start();
		//t3.start();
		
		
	}

}