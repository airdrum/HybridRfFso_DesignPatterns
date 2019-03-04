package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.XML;

public class XmlClass {
	public String xml2string() {
		return null;
	}
	
	public JSONObject xml2json(String path){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(path)));
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
	
		return XML.toJSONObject(sb.toString());
		
	}
	
	void writeXmlToFile(String outputPath) {
		
	}
	
}
