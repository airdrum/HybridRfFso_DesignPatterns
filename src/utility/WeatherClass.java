package utility;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class WeatherClass {
		
			public WeatherClass(){
				init();
			}
		  private void init() {
			  	try {
					socket = new Socket("10.100.93.21", 20108);
				} catch (Exception e) {
					
				}
				
				try {
					socket.setSoTimeout(10000);
				} catch (SocketException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					input = new DataInputStream(socket.getInputStream());
					output = new DataOutputStream(socket.getOutputStream());
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					//e4.printStackTrace();
				}
				
			}
		  private static int[] dataLengths = { 9,11,13,15,17,21,39,51,53,105,107,117 };

		  private static boolean weatherRetval = false;
		  private static float[] dataMultipliers = { 0.001f, 
				  									0.1f,
				  									1,
				  									0.1f,
				  									1,
				  									1,
				  									1,
				  									1,
				  									1,
				  									1,
				  									1,
				  									0.1f};
		  static float[] outputArray = new float[dataLengths.length];
		  private static Socket socket = null;
		  private static byte[] socketMAC_addr = { 0x01, 0x04, 0x00, 0x00, 0x00, 0x3e, 0x71, (byte) 0xda };
		  
		  private static DataInputStream input = null;
		  private static DataOutputStream output = null;
		  private static boolean getWeatherArray(String[] batch)
		  {
			  if (batch.length <129)
			  {
				  return false;
			  }
			  
			  String concat="";
			  for (int i = 0; i < dataLengths.length; i++) {
				  concat = (batch[dataLengths[i]].toString() + batch[dataLengths[i]+1].toString());
				  outputArray[i] = dataMultipliers[i] *(int) Long.parseLong(concat,16);
				  
			  } 
			return true;
			  
		  }
		  
		  private static String getBarometer() {return String.valueOf(outputArray[0]);} //0.001
		  private static String getInTemp() {return String.valueOf(outputArray[1]);}//0.1
		  private static String getInHumidity() {return String.valueOf(outputArray[2]);}//1
		  private static String getOutTemp() {return String.valueOf(outputArray[3]);}//0.1
		  private static String getWindSpeed() {return String.valueOf(outputArray[4]);}//1
		  private static String getWindDir() {return String.valueOf(outputArray[5]);}//1
		  private static String getOutHumidity() {return String.valueOf(outputArray[6]);}//1
		  private static String getUv() {return String.valueOf(outputArray[7]);}//1
		  private static String getSolarPow() {return String.valueOf(outputArray[8]);}//1
		  private static String getSunrise() {return String.valueOf(outputArray[9]);}//1
		  private static String getSunset() {return String.valueOf(outputArray[10]);}//1
		  private static String getTemp() {return String.valueOf(outputArray[11]);}//0.1
		  @SuppressWarnings("unchecked")
		public  DBObject getWeatherDataDBObject()
		  {
		
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			JSONObject objName = new JSONObject();
			

		    

		    objName.put("Barometer", getBarometer());
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
		    StringWriter out = new StringWriter();
		    try {
				objName.writeJSONString(out);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      
		      String jsonText = out.toString();
	
			    DBObject dbObject = (DBObject)JSON.parse(jsonText);
		      //System.out.print(jsonText);
		      //collection.insert(dbObject);
	
			    try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    try {
					output.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return dbObject;

			}
			public void clearWeatherArray() {
				try {
					output.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public boolean setWeatherOutputData()
			{
			 	try {
					output.write(socketMAC_addr);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					try {

						output.write(socketMAC_addr);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
			    String[] batch = new String[129];
			    String samet="";
			    for (int i = 0; i < 129; i++) {
			    	try {
			    		batch[i] = Integer.toHexString(input.read());
			    		//samet=samet+batch[i].toString()+"-";
			    		//System.out.print(batch[i]+ "-");
					} catch (Exception e) {
						//if (++count == maxTries) throw e;
					}
			    }

		    	//System.out.println();
				if(getWeatherArray(batch))
					return true;
				else
					return false;
			}		
		
		  
		

		
			

}
