package deneme;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;
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
	static Random rand = new Random(); 
	static double innerFso = 90;
	double random;
	public static void getThroughput() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		innerFso = 94 + rand.nextGaussian()*0.1;
		DateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		Date date = new Date();
		String currentTime = df.format(date);
		JSONObject objName = new JSONObject();
		System.out.println("FSO: " +currentTime+","+Double.toString(Math.round(innerFso*100.0)/100.0) +",Mbits/sec");
		objName.put("time",currentTime);
		objName.put("fso",Double.toString(Math.round(innerFso*100.0)/100.0));
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
	static Random rand = new Random(); 
	public static void getThroughput() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		innerFso = 34 + rand.nextGaussian() *3;
		DateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		Date date = new Date();
		String currentTime = df.format(date);
		JSONObject objName = new JSONObject();
		System.out.println("RF: " +currentTime+","+Double.toString(Math.round(innerFso*100.0)/100.0) +",Mbits/sec");
		objName.put("time",currentTime);
		objName.put("rf",Double.toString(Math.round(innerFso*100.0)/100.0));
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

	static Mongo mongo = new Mongo("localhost", 27017);
	static DB db = mongo.getDB("mydb");
	static DBCollection collection = db.getCollection("WeatherData");
	
	static double innerFso;
	static double barometer;
	static double inTemp;
	static double inHum;
	static double outTemp;
	static double windSpeed;
	static double windDir;
	static double outHum;
	static double uv;
	static double solpow;
	static double temp;
	static double rainrate;
	
	static Random rand = new Random(); 
	public static void getThroughput() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		barometer = 29+ rand.nextGaussian() *0.03;
		inTemp=85+ rand.nextGaussian() *3;
		inHum=47+ rand.nextGaussian() *3;
		outTemp=79+ rand.nextGaussian() *3;
		windSpeed=10+ rand.nextGaussian() *5;
		windDir=180+ rand.nextGaussian() *10;
		outHum=62+ rand.nextGaussian() *5;
		uv=30+ rand.nextGaussian() *10;
		solpow=500+ rand.nextGaussian() *50;
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		Date date = new Date();
		String currentTime = df.format(date);
		JSONObject objName = new JSONObject();
		System.out.println("WEATHER: " +currentTime+","
		+Double.toString(Math.round(barometer*100.0)/100.0)+"," 
		+Double.toString(Math.round(inTemp*100.0)/100.0)+","
		+Double.toString(Math.round(inHum*100.0)/100.0) +","
		+Double.toString(Math.round(outTemp*100.0)/100.0)+","
		+Double.toString(Math.round(windSpeed*100.0)/100.0) +","
		+Double.toString(Math.round(windDir*100.0)/100.0)+","
		+Double.toString(Math.round(outHum*100.0)/100.0) +","

		+Double.toString(Math.round(solpow*100.0)/100.0) +","
		+Double.toString(Math.round(uv*100.0)/100.0));
		objName.put("time",currentTime);
		objName.put("Barometer", Double.toString(Math.round(barometer*100.0)/100.0));
	    objName.put("InsideTemperature", Double.toString(Math.round(inTemp*100.0)/100.0));
	    objName.put("InsideHumidity", Double.toString(Math.round(inHum*100.0)/100.0));
	    objName.put("OutsideTemperature", Double.toString(Math.round(outTemp*100.0)/100.0));
	    objName.put("WindSpeed", Double.toString(Math.round(windSpeed*100.0)/100.0));
		objName.put("WindDirection", Double.toString(Math.round(windDir*100.0)/100.0));
		objName.put("OutsideHumidity", Double.toString(Math.round(outHum*100.0)/100.0));
		objName.put("UV", Double.toString(Math.round(uv*100.0)/100.0));
		objName.put("SolarRadiation", Double.toString(Math.round(solpow*100.0)/100.0)); 
		DBObject dbObject = (DBObject)JSON.parse(objName.toString());
	    collection.insert(dbObject);
	/*
	 * objName.put("Barometer", getBarometer());
		    objName.put("InsideTemperature", getInTemp());
		    objName.put("InsideHumidity", getInHumidity());
		    objName.put("OutsideTemperature", getOutTemp());
		    objName.put("WindSpeed", getWindSpeed());
    		objName.put("WindDirection", getWindDir());
    		objName.put("OutsideHumidity", getOutHumidity());
    		objName.put("UV", getUv());
    		objName.put("SolarRadiation", getSolarPow());
    		objName.put("SunRise", getSunrise());
    		objName.put("SunSet", getSunset());
    		objName.put("Temperature", getTemp());
    		objName.put("Rainrate", getRainrate());    
	 */
        
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

public class App {
	
	public static void main(String[] args) {


		Weather worker = new Weather();
		RfThroughput rf= new RfThroughput();
		FsoThroughput fso= new FsoThroughput();
		Thread t1 = new Thread(rf);
		Thread t2 = new Thread(fso);
		Thread t3 = new Thread(worker);
		t1.start();
		t2.start();
		t3.start();
		
		
	}

}