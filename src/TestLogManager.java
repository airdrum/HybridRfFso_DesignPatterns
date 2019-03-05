import java.util.ArrayList;

import org.json.*;
import config.TestConfig;
import nodefactory.FsoNodeFactory;
import nodefactory.NodeFactory;
import nodefactory.RemoteNode;
import nodefactory.RfFsoNodeFactory;
import nodefactory.RfNodeFactory;
public class TestLogManager {
	private ArrayList<JSONObject> testItems = null;
	private ArrayList<JSONObject> testSettingNodes = null;
	// TODO Auto-grenerated method stu
			
	public TestLogManager() {
		
		JSONObject testSettings = null;
		TestConfig testConfig = new TestConfig();
		testSettings = testConfig.getTestConfig();
		this.testItems = new ArrayList<JSONObject>();
		JSONObject obj = (JSONObject) testSettings.get("Ozyegin");
		JSONObject pageName = (JSONObject) obj.get("Tests");
		JSONArray arr = (JSONArray) pageName.get("Test");
		for (int i = 0; i < arr.length(); i++) {
			testItems.add(i, (JSONObject) arr.get(i));
		}
	
	}
	
	public ArrayList<JSONObject> getTestItems(){
		return this.testItems;
	}

	private void setTestSettings(){
		NodeFactory myRfNodeFactory,myFsoNodeFactory, myRfFsoNodeFactory;
		RemoteNode fsoNodeSettings, rfNodeSettings, rfFsoNodeSettings;
		myFsoNodeFactory = new FsoNodeFactory();
		myRfNodeFactory = new RfNodeFactory();
		myRfFsoNodeFactory = new RfFsoNodeFactory();
		JSONObject fsoNode, rfNode,rfFsoNode = null;
		testSettingNodes = new ArrayList<JSONObject>();
		for (int i = 0; i < this.testItems.size(); i++) {
			if (this.testItems.get(i).get("TransmissionType").equals("FSO")) {
				fsoNodeSettings = myFsoNodeFactory.createNode((String)this.testItems.get(i).get("TransmissionDirection"), (String)this.testItems.get(i).get("TransmissionProtocol"));
				fsoNode = fsoNodeSettings.getNodeObject();
				this.testSettingNodes.add(i, fsoNode);
			}else if(this.testItems.get(i).get("TransmissionType").equals("RF")) {
				rfNodeSettings = myRfNodeFactory.createNode((String)this.testItems.get(i).get("TransmissionDirection"), (String)this.testItems.get(i).get("TransmissionProtocol"));
				rfNode = rfNodeSettings.getNodeObject();
				this.testSettingNodes.add(i, rfNode);
			}else if(this.testItems.get(i).get("TransmissionType").equals("RFFSO")) {
				rfFsoNodeSettings = myRfFsoNodeFactory.createNode((String)this.testItems.get(i).get("TransmissionDirection"), (String)this.testItems.get(i).get("TransmissionProtocol"));
				rfFsoNode = rfFsoNodeSettings.getNodeObject();
				this.testSettingNodes.add(i, rfFsoNode);
			}	
		}		
	}
	
	public ArrayList<JSONObject> getTestSettings(){
		setTestSettings();
		return this.testSettingNodes;
	}
	
}