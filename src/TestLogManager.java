import org.json.JSONObject;

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
		JSONObject tests = (JSONObject) testSettings.get("Ozyegin");
		JSONObject test = (JSONObject) tests.get("Tests");
		System.out.println(test);
		fsoNodeTcpSettings = myFsoNodeFactory.createNode("ENGTOBUS", "TCP");
		rfNodeUdpSettings = myRfNodeFactory.createNode("BUSTOENG", "UDP");
		
	}

}