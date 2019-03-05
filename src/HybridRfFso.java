

import java.util.ArrayList;

import org.json.JSONObject;

import nodefactory.FsoNodeFactory;
import nodefactory.NodeFactory;
import nodefactory.RemoteNode;
import nodefactory.RfFsoNodeFactory;
import nodefactory.RfNodeFactory;

public class HybridRfFso {
	
	public static void main(String[] args) {
		// TODO Auto-grenerated method stu
		NodeFactory myRfNodeFactory,myFsoNodeFactory, myRfFsoNodeFactory;
		RemoteNode fsoNodeSettings, rfNodeSettings, rfFsoNodeSettings;
		myFsoNodeFactory = new FsoNodeFactory();
		myRfNodeFactory = new RfNodeFactory();
		myRfFsoNodeFactory = new RfFsoNodeFactory();
		JSONObject fsoNode, rfNode,rfFsoNode = null;
		TestLogManager tManager = new TestLogManager();
		ArrayList<JSONObject> testItems = tManager.getTestItems();
		ArrayList<JSONObject> testSettingNodes = new ArrayList<JSONObject>();
		for (int i = 0; i < testItems.size(); i++) {
			if (testItems.get(i).get("TransmissionType").equals("FSO")) {
				fsoNodeSettings = myFsoNodeFactory.createNode((String)testItems.get(i).get("TransmissionDirection"), (String)testItems.get(i).get("TransmissionProtocol"));
				fsoNode = fsoNodeSettings.getNodeObject();
				testSettingNodes.add(i, fsoNode);
			}else if(testItems.get(i).get("TransmissionType").equals("RF")) {
				rfNodeSettings = myRfNodeFactory.createNode((String)testItems.get(i).get("TransmissionDirection"), (String)testItems.get(i).get("TransmissionProtocol"));
				rfNode = rfNodeSettings.getNodeObject();
				testSettingNodes.add(i, rfNode);
			}else if(testItems.get(i).get("TransmissionType").equals("RFFSO")) {
				rfFsoNodeSettings = myRfFsoNodeFactory.createNode((String)testItems.get(i).get("TransmissionDirection"), (String)testItems.get(i).get("TransmissionProtocol"));
				rfFsoNode = rfFsoNodeSettings.getNodeObject();
				testSettingNodes.add(i, rfFsoNode);
			}
			
		}
		System.out.println("TEST ITEMS:"+testItems);

		System.out.println("TEST SETTINGS:"+testSettingNodes);


	}

}
