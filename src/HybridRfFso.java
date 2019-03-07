import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import utility.SshClass;

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
		DBCollection collection = db.getCollection("Throughput");
		DBObject jsonout = null; 
		

		
		//SshClass sshMngr = new SshClass("10.100.93.28", "pi","raspberry");
		SshClass sshMngrrf = new SshClass("10.100.93.20", "pi","raspberry");
		SshClass sshMngrfso = new SshClass("10.100.93.20", "pi","raspberry");
		sshMngrfso.sendCommand("killall iperf;iperf -s -u -i1 -p4000");
		sshMngrrf.sendCommand("killall iperf;iperf -s -u -i1 -p5000");
		//sshMngr.sendCommand("killall iperf;iperf -c 192.168.100.21 -u -b100M -i1 -t9999999 -p4000 & iperf -c 192.168.2.177 -u -b30M -i1 -t9999999 -p5000 &");
		try {
			Process p = Runtime.getRuntime().exec(new String[]{"bash","-c","killall iperf;iperf -c 192.168.100.21 -u -b100M -i1 -t9999999 -p4000 & iperf -c 192.168.2.177 -u -b30M -i1 -t9999999 -p5000 &"});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//sshMngr.sendCommand("killall iperf;iperf -c 192.168.2.177 -u -b50M -i1 -t60 -p5000&");
		
		
//		for (int i = 0; i < settings.size(); i++) {
//			System.out.println(settings.get(i));
//		}
		
		
		

		

	    
		String fsostr,rfstr,fsoparse="",rfparse="";
		while(true) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
		    String currentTime = df.format(date);
			JSONObject objName = new JSONObject();
			rfstr = sshMngrrf.recvData();
			fsostr = sshMngrfso.recvData();
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
			objName.put("time", currentTime);
		    objName.put("RF", rfparse);
		    objName.put("FSO", fsoparse);
		    System.out.println(objName.toString());
		      
		      
			    DBObject dbObject = (DBObject)JSON.parse(objName.toString());
			    collection.insert(dbObject);
			    System.out.println("*************");
		}
	}

}
