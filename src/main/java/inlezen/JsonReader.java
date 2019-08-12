package inlezen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import dataTypen.Sites;

public class JsonReader {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		String v_apikey = "api_key=ZSFYC1HPDSUXQH6KCDKSUJTFDGW884LE";
		String v_SolarEdgeUrl = "https://monitoringapi.solaredge.com";
		String v_listSites = "sites/list?size=5&searchText=Lyon&sortProperty=name&sortOrder=ASC";
		String v_siteId = "62137";
		String v_startDate = "2014-07-03";
		String v_endDate = "2015-06-30";
		String v_startDate1 = "2015-07-01";
		String v_endDate1 = "2016-06-30";
		Map<String, Object> v_mapObj = new HashMap<String, Object>();
		Map<String, Object> v_mapObj2 = new HashMap<String, Object>();
		Map<String, Object> v_mapObj3 = new HashMap<String, Object>();

		JSONObject json = readJsonFromUrl(
		    "https://monitoringapi.solaredge.com/sites/list?size=5&searchText=Lyon&sortProperty=name&sortOrder=ASC&api_key=ZSFYC1HPDSUXQH6KCDKSUJTFDGW884LE");
		System.out.println(json.toString());
		System.out.println(json.get("sites"));

		json = readJsonFromUrl(v_SolarEdgeUrl + "/" + v_listSites + "&" + v_apikey);
		System.out.println(json.toString());
		System.out.println(json.get("sites"));

		JsonIterator iter = JsonIterator.parse(json.get("sites").toString());
		// Sites l_sites = iter.read(Sites.class);

		int totalTagsCount = 0;
		for (String field = iter.readObject(); field != null; field = iter.readObject()) {
			System.out.println("field: /" + field + "/");
			if (field.equals("site")) {
				while (iter.readArray()) {
					for (String field2 = iter.readObject(); field2 != null; field2 = iter.readObject()) {
						System.out.println("field2: /" + field2 + "/");
						// JsonIterator vv = iter.parse(field2);
						// String vvv = vv.readObject();
						// Any ll_any = JsonIterator.deserialize(field2);
						if (field2.equals("notes")) {
							while (iter.readArray()) {
								String vv3 = iter.readObject();
								System.out.println("vv3 :" + vv3);
								totalTagsCount++;
							}
						} else {
							iter.skip();
						}
					}
				}
			} else {
				iter.skip();
			}
		}

		String l_jsonSites = json.get("sites").toString();
		Any l_any = JsonIterator.deserialize(json.get("sites").toString());

		v_mapObj = Utility.jsonToMap(json);
		v_mapObj2 = Utility.jsonToMap(json.get("sites"));
		v_mapObj3 = Utility.jsonToMap(json.get("sites"));

		System.out.println("v_mapObj: " + v_mapObj.toString());
		System.out.println("v_mapObj2: " + v_mapObj2.toString());

		// dataTypen.Sites.Site Site =
		// JsonIterator.deserialize(json.get("sites").toString(),
		// dataTypen.Sites.Site.class);

		json = readJsonFromUrl(v_SolarEdgeUrl + "/site/" + v_siteId + "/details.json?" + "&" + v_apikey);
		System.out.println(json.toString());

		json = readJsonFromUrl(v_SolarEdgeUrl + "/site/" + v_siteId + "/timeFrameEnergy?startDate=" + v_startDate
		    + "&endDate=" + v_endDate + "&" + v_apikey);
		System.out.println(json.toString());

		json = readJsonFromUrl(v_SolarEdgeUrl + "/site/" + v_siteId + "/timeFrameEnergy?startDate=" + v_startDate1
		    + "&endDate=" + v_endDate1 + "&" + v_apikey);
		System.out.println(json.toString());

		v_mapObj = Utility.jsonToMap(json);
		System.out.println("v_mapObj: " + v_mapObj.toString());

		json = readJsonFromUrl(v_SolarEdgeUrl + "/site/" + v_siteId + "/power?startTime=2015-07-01%2000:01:00"
		    + "&endTime=2015-07-05%2023:59:00" + "&" + v_apikey);
		System.out.println(json.toString());

		JSONArray ja = new JSONArray();
		ja.put(json.get("power"));

		System.out.println(ja.get(0));

	}
}
