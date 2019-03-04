package config;

import org.json.JSONObject;

import utility.XmlClass;

public class TestConfig  {
	private XmlClass xmlConfigParser = null;
	private JSONObject testConfig = null;


	public TestConfig() {
		// TODO Auto-generated method stub
		this.xmlConfigParser = new XmlClass();
		this.testConfig = xmlConfigParser.xml2json("config\\testconfig.xml");
	}

	public JSONObject getTestConfig() {
		return this.testConfig;
	}
	public JSONObject getTestObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTestConfig(String path) {
		// TODO Auto-generated method stub
		
	}

	public void setPath(String path) {
		// TODO Auto-generated method stub
		xmlConfigParser = new XmlClass();
		testConfig = xmlConfigParser.xml2json(path);
		
	}

	public void setTestConfigPath() {
		// TODO Auto-generated method stub
		
	}

}
