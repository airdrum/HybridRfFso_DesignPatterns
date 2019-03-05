import java.util.ArrayList;

import org.json.*;
import config.TestConfig;
public class TestLogManager {
	private ArrayList<JSONObject> testItems = null;
	
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

}