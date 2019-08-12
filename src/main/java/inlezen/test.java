package inlezen;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class test {
	public static void main(String[] args) {
		JSONTokener jsonArray = null;
		JSONObject jsonObj = new JSONObject(jsonArray);
		JSONArray docs = jsonObj.getJSONArray("results");
		File file = new File("C:/fromJSON2.csv");
		String csv = getDocs(docs);
		// Files..writeStringToFile(file, csv);
	}

	public static String getDocs(JSONArray ja) throws JSONException {
		String result = "";
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject jo = ja.optJSONObject(i);
			if (jo != null) {
				getAllTopKeyAndValue(jo, map);
				if (i == 0) {
					result += keyOfMap2String(map) + "\n";
				}
				result += valueOfMap2String(map) + "\n";
			}
		}

		return result;
	}

	public static void getAllTopKeyAndValue(JSONObject jo, Map<String, Integer> map) {
		if (jo != null) {
			JSONArray names = jo.names();
			new ArrayList<Integer>();
			if (names != null) {
				for (int i = 0; i < names.length(); i++) {
					String name = names.getString(i);
					JSONObject object = jo.optJSONObject(name);
					if (object != null) {
						getAllTopKeyAndValue(object, map);
					} else {
						map.put(name, (Integer) jo.get(name));
					}
				}
			}
		}
	}

	public static String keyOfMap2String(Map<String, Integer> map) {
		String result = "";
		Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> entry = iter.next();
			result += entry.getKey();
			if (iter.hasNext()) {
				result += ",";
			}
		}
		return result;
	}

	public static String valueOfMap2String(Map<String, Integer> map) {
		String result = "";
		Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> entry = iter.next();
			result += entry.getValue();
			if (iter.hasNext()) {
				result += ",";
			}
		}
		return result;
	}
}
