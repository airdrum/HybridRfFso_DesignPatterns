import java.util.ArrayList;
import java.util.List;

import org.json.*;
import config.TestConfig;
import nodefactory.FsoNodeFactory;
import nodefactory.NodeFactory;
import nodefactory.RemoteNode;
import nodefactory.RfNodeFactory;

public class TestLogManager {

	public TestLogManager() {
		NodeFactory myRfNodeFactory,myFsoNodeFactory;
		RemoteNode fsoNodeTcpSettings, rfNodeUdpSettings;
		TestConfig testConfig = new TestConfig();
		JSONObject testSettings = null;
		myFsoNodeFactory = new FsoNodeFactory();
		myRfNodeFactory = new RfNodeFactory();

		/*
		 * direction
		 * protocol
		 * interface
		 */

		testSettings = testConfig.getTestConfig();
		
		JSONObject obj = (JSONObject) testSettings.get("Ozyegin");
		JSONObject pageName = (JSONObject) obj.get("Tests");
		JSONObject test = (JSONObject) pageName.get("Test");
		JSONArray arr = obj.getJSONArray("posts");
		for (int i = 0; i < arr.length(); i++)
		{
		    String post_id = arr.getJSONObject(i).getString("post_id");
		  
		}
		
		fsoNodeTcpSettings = myFsoNodeFactory.createNode("ENGTOBUS", "TCP");
		rfNodeUdpSettings = myRfNodeFactory.createNode("BUSTOENG", "UDP");
		
	}

}