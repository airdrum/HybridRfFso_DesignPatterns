
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;

import nodefactory.*;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
public class HybridRfFso {
	
	public static void main(String[] args) {
		// TODO Auto-grenerated method stub
		NodeFactory myRfNodeFactory,myFsoNodeFactory;
		RemoteNode fsoNodeTcpSettings, rfNodeUdpSettings;
		myFsoNodeFactory = new FsoNodeFactory();
		myRfNodeFactory = new RfNodeFactory();
		String xmlString = "<user><name>Samet</name><age>29</age></user>";
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("config\\testconfig.xml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		StringBuilder sb = new StringBuilder();

		try {
			while((line=br.readLine())!= null){
			    sb.append(line.trim());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * Burada test configleri ayarlayan metod girmeli ENGTO BUS, TCP-UDP, RF-FSO, RF-FSO same time,time, timestep ayarlarý
		 * burada girilmeli. Jsonobject olabilir.
		 */
		JSONObject jsonObject = XML.toJSONObject(sb.toString());
		System.out.println(jsonObject);
		fsoNodeTcpSettings = myFsoNodeFactory.createNode("ENGTOBUS", "TCP");
		rfNodeUdpSettings = myRfNodeFactory.createNode("BUSTOENG", "UDP");
		System.out.println("*****");
		System.out.println(fsoNodeTcpSettings.getNodeObject());
		System.out.println(rfNodeUdpSettings.getNodeObject());
		
		
		

	}

}
