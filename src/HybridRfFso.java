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

		System.out.println(settings);
		System.out.println(items);
	}

}
