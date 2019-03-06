import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import utility.SshClass;

public class HybridRfFso {
	
	public static void main(String[] args) {
		// TODO Auto-grenerated method stu
		
		TestLogManager tManager = new TestLogManager();
		ArrayList<JSONObject> settings = tManager.getTestSettings();
		ArrayList<JSONObject> items = tManager.getTestItems();
		SshClass sshMngr = new SshClass("10.100.93.28", "pi","raspberry");
		SshClass sshMngrrf = new SshClass("10.100.93.20", "pi","raspberry");
		SshClass sshMngrfso = new SshClass("10.100.93.20", "pi","raspberry");
		sshMngrfso.sendCommand("killall iperf;iperf -s -u -i1 -p4000");
		sshMngrrf.sendCommand("killall iperf;iperf -s -u -i1 -p5000");
		sshMngr.sendCommand("killall iperf;iperf -c 192.168.100.21 -u -b100M -i1 -t60 -p4000 & iperf -c 192.168.2.177 -u -b30M -i1 -t60 -p5000 &");
		//sshMngr.sendCommand("killall iperf;iperf -c 192.168.2.177 -u -b50M -i1 -t60 -p5000&");
		
		
//		for (int i = 0; i < settings.size(); i++) {
//			System.out.println(settings.get(i));
//		}
		String fsostr,rfstr;
		while(true) {

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
					 String match = m.group(1);
					 System.out.println("FSO: "+match);
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
					 String match = m.group(1);
					 System.out.println("RF: "+match);
					 //here you insert 'match' into the list
				}
			}		
			System.out.println("*************");
		}
	}

}
