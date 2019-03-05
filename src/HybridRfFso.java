import java.util.ArrayList;

import org.json.JSONObject;

public class HybridRfFso {
	
	public static void main(String[] args) {
		// TODO Auto-grenerated method stu
		
		TestLogManager tManager = new TestLogManager();
		ArrayList<JSONObject> settings = tManager.getTestSettings();
		ArrayList<JSONObject> items = tManager.getTestItems();
		System.out.println("ITEMS:" + items);
		System.out.println("SETTINGS:" + settings);

	}

}
