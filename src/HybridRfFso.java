import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class HybridRfFso {
	
	public static void main(String[] args) {
		// TODO Auto-grenerated method stu
//		
//		TestLogManager tManager = new TestLogManager();
//		ArrayList<JSONObject> settings = tManager.getTestSettings();
//		ArrayList<JSONObject> items = tManager.getTestItems();
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection collection = db.getCollection("WeatherData");
		

		DBObject jsonout = null;

		
//		SshClass sshMngr = new SshClass("10.100.93.28", "pi","raspberry");
		SshClass sshMngrrf = new SshClass("10.100.93.16", "pi","raspberry");
		SshClass sshMngrfso = new SshClass("10.100.93.16", "pi","raspberry");
		sshMngrfso.sendCommand("killall iperf;iperf -s -u -i1 -p4000");
		sshMngrrf.sendCommand("killall iperf;iperf -s -u -i1 -p5000");
		
//		sshMngr.sendCommand("killall iperf;iperf -c 192.168.100.21 -u -b100M -i1 -t9999999 -p4000 & iperf -c 192.168.2.178 -u -b40M -i1 -t9999999 -p5000 &");
		try {
			Process p = Runtime.getRuntime().exec(new String[]{"bash","-c","killall iperf;iperf -c 192.168.100.21 -u -b100M -i1 -t9999999 -p4000 & iperf -c 192.168.2.178 -u -b40M -i1 -t9999999 -p5000 &"});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		sshMngr.sendCommand("killall iperf;iperf -c 192.168.2.177 -u -b50M -i1 -t60 -p5000&");
		
		
//		for (int i = 0; i < settings.size(); i++) {
//			System.out.println(settings.get(i));
//		}
		WeatherClass weather = new WeatherClass();
		String fsostr,rfstr,fsoparse="",rfparse="";
		
		boolean fsobool = true; 
		boolean rfbool=true;
		
		while(true) {
			weather.clearWeatherArray();
			weather.setWeatherOutputData();
		    jsonout = weather.getWeatherDataDBObject();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
		    String currentTime = df.format(date);
			JSONObject objName = new JSONObject();
			try {
				fsostr = sshMngrfso.recvData();
			}catch (Exception e) {
				// TODO: handle exception
				fsostr = "0.0";
			}
			try {
				rfstr = sshMngrrf.recvData();
			}catch (Exception e) {
				rfstr = "0.0";
			}
			
			
			for (int i = 0; i < fsostr.split("\r\n").length; i++) {
				String string = fsostr.split("\r\n")[i];

				 Matcher m = Pattern.compile(
                         Pattern.quote("Bytes")
                         + "(.*?)"
                         + Pattern.quote("bits")
                ).matcher(string);
				while(m.find()){
					fsoparse = m.group(1);
					 //System.out.println("FSO: "+fsoparse);
					 //here you insert 'match' into the list
				}
			}	
			for (int i = 0; i < rfstr.split("\r\n").length; i++) {
				String string = rfstr.split("\r\n")[i];

				 Matcher m = Pattern.compile(
                         Pattern.quote("Bytes")
                         + "(.*?)"
                         + Pattern.quote("bits")
                ).matcher(string);
				while(m.find()){
					 rfparse = m.group(1);
					 //System.out.println("RF: "+rfparse);
					 //here you insert 'match' into the list
				}
			}
			jsonout.put("time", currentTime);
			jsonout.put("RF", rfparse);
			jsonout.put("FSO", fsoparse);
		    System.out.println(jsonout.get("time")+
		    		",FSO:"+jsonout.get("FSO")+
		    		",RF:"+jsonout.get("RF")
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
		}
	}

}
