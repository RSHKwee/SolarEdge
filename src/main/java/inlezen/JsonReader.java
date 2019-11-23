package inlezen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
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
		/*
		 * int totalTagsCount = 0; for (String field = iter.readObject(); field != null;
		 * field = iter.readObject()) { System.out.println("field: /" + field + "/"); if
		 * (field.equals("site")) { while (iter.readArray()) { for (String field2 =
		 * iter.readObject(); field2 != null; field2 = iter.readObject()) {
		 * System.out.println("field2: /" + field2 + "/");
		 * 
		 * if (field2.equals("installationDate")) {
		 * 
		 * // JsonIterator vv = iter.parse(field2); // String vvv = vv.readObject(); //
		 * Any ll_any = JsonIterator.deserialize(field2);
		 * 
		 * // while (iter.readArray()) { // String vv3 = iter.readObject(); //
		 * System.out.println("vv3 :" + vv3); // totalTagsCount++; // } } else {
		 * iter.skip(); } } } } else { iter.skip(); } }
		 */
		String l_jsonSites = json.get("sites").toString();
		Any l_any = JsonIterator.deserialize(json.get("sites").toString());
    Any l_anySite = l_any.get("site");
    List<Any> l_anyNote = l_anySite.asList();
		v_mapObj = Utility.jsonToMap(json);
		v_mapObj2 = Utility.jsonToMap(json.get("sites"));
		v_mapObj3 = Utility.jsonToMap(json.get("sites"));

		System.out.println("v_mapObj: " + v_mapObj.toString());
		System.out.println("v_mapObj2: " + v_mapObj2.toString());

		// dataTypen.Sites.Site Site =
		// JsonIterator.deserialize(json.get("sites").toString(),
		// dataTypen.Sites.Site.class);

		json = readJsonFromUrl(v_SolarEdgeUrl + "/site/" + v_siteId + "/details.json?" + "&" + v_apikey);
		System.out.println("Details: " +  json.toString());

		json = readJsonFromUrl(v_SolarEdgeUrl + "/site/" + v_siteId + "/timeFrameEnergy?startDate=" + v_startDate
		    + "&endDate=" + v_endDate + "&" + v_apikey);
		System.out.println("Time Frame Energy: " + json.toString());

		json = readJsonFromUrl(v_SolarEdgeUrl + "/site/" + v_siteId + "/power?startTime=2015-07-01%2000:01:00"
		    + "&endTime=2015-07-05%2023:59:00" + "&" + v_apikey);
		System.out.println("Power: " + json.toString());
		
		Any l_power = JsonIterator.deserialize(json.get("power").toString());
		Any l_value = l_power.get("values");
    List<Any> l_anyValues = l_value.asList();
    l_anyValues.forEach(l_anyvalue ->{
    	try {
    	String v_date =  l_anyvalue.get("date").toString();
    	String v_value = l_anyvalue.get("value").toString();
    	System.out.println(l_anyvalue.toString());
    	System.out.println(" Datum:" + v_date + " Waarde:" + v_value);
    	} catch (Exception e) {
    		System.out.println(e.toString());
    	}
    });
  	/*    
    JSONObject v_power = new JSONObject (json.get("power"));
    System.out.println(v_power.toString());
    JSONObject v_values = new JSONObject(json.get("values"));
    System.out.println(v_values.toString());
   
	
		JSONArray ja = new JSONArray();
		ja.put(json.get("power"));

		System.out.println(ja.get(0));
		String power_example = "{\"power\":{\"unit\":\"W\",values:[{\"date\":\"2015-07-01 00:00:00\",value:null},{\"date\":\"2015-07-01 00:15:00\",value:null},{\"date\":\"2015-07-01 00:30:00\",value:null},{\"date\":\"2015-07-01 00:45:00\",value:null},{\"date\":\"2015-07-01 01:00:00\",value:null},{\"date\":\"2015-07-01 01:15:00\",value:null},{\"date\":\"2015-07-01 01:30:00\",value:null},{\"date\":\"2015-07-01 01:45:00\",value:null},{\"date\":\"2015-07-01 02:00:00\",value:null},{\"date\":\"2015-07-01 02:15:00\",value:null},{\"date\":\"2015-07-01 02:30:00\",value:null},{\"date\":\"2015-07-01 02:45:00\",value:null},{\"date\":\"2015-07-01 03:00:00\",value:null},{\"date\":\"2015-07-01 03:15:00\",value:null},{\"date\":\"2015-07-01 03:30:00\",value:null},{\"date\":\"2015-07-01 03:45:00\",value:null},{\"date\":\"2015-07-01 04:00:00\",value:null},{\"date\":\"2015-07-01 04:15:00\",value:null},{\"date\":\"2015-07-01 04:30:00\",value:null},{\"date\":\"2015-07-01 04:45:00\",value:null},{\"date\":\"2015-07-01 05:00:00\",value:null},{\"date\":\"2015-07-01 05:15:00\",value:null},{\"date\":\"2015-07-01 05:30:00\",value:null},{\"date\":\"2015-07-01 05:45:00\",value:0},{\"date\":\"2015-07-01 06:00:00\",value:0},{\"date\":\"2015-07-01 06:15:00\",value:6.25},{\"date\":\"2015-07-01 06:30:00\",value:35.5},{\"date\":\"2015-07-01 06:45:00\",value:83.333336},{\"date\":\"2015-07-01 07:00:00\",value:176},{\"date\":\"2015-07-01 07:15:00\",value:315},{\"date\":\"2015-07-01 07:30:00\",value:521.5},{\"date\":\"2015-07-01 07:45:00\",value:814.1667},{\"date\":\"2015-07-01 08:00:00\",value:1097.3334},{\"date\":\"2015-07-01 08:15:00\",value:1386.1666},{\"date\":\"2015-07-01 08:30:00\",value:1661.8334},{\"date\":\"2015-07-01 08:45:00\",value:1973},{\"date\":\"2015-07-01 09:00:00\",value:2262.8333},{\"date\":\"2015-07-01 09:15:00\",value:2531.8333},{\"date\":\"2015-07-01 09:30:00\",value:2776.3333},{\"date\":\"2015-07-01 09:45:00\",value:3047.6667},{\"date\":\"2015-07-01 10:00:00\",value:3313.8333},{\"date\":\"2015-07-01 10:15:00\",value:3563},{\"date\":\"2015-07-01 10:30:00\",value:3759.8333},{\"date\":\"2015-07-01 10:45:00\",value:3936.1667},{\"date\":\"2015-07-01 11:00:00\",value:4119.1665},{\"date\":\"2015-07-01 11:15:00\",value:4286},{\"date\":\"2015-07-01 11:30:00\",value:4447.3335},{\"date\":\"2015-07-01 11:45:00\",value:4550.5},{\"date\":\"2015-07-01 12:00:00\",value:4604.5},{\"date\":\"2015-07-01 12:15:00\",value:4787.3335},{\"date\":\"2015-07-01 12:30:00\",value:4775.8335},{\"date\":\"2015-07-01 12:45:00\",value:4931.1665},{\"date\":\"2015-07-01 13:00:00\",value:4811.3335},{\"date\":\"2015-07-01 13:15:00\",value:4906.3335},{\"date\":\"2015-07-01 13:30:00\",value:4773.3335},{\"date\":\"2015-07-01 13:45:00\",value:5107.1665},{\"date\":\"2015-07-01 14:00:00\",value:5008.3335},{\"date\":\"2015-07-01 14:15:00\",value:4862.3335},{\"date\":\"2015-07-01 14:30:00\",value:4753.1665},{\"date\":\"2015-07-01 14:45:00\",value:4299.6665},{\"date\":\"2015-07-01 15:00:00\",value:4618.5},{\"date\":\"2015-07-01 15:15:00\",value:4454},{\"date\":\"2015-07-01 15:30:00\",value:4315.8335},{\"date\":\"2015-07-01 15:45:00\",value:4111.3335},{\"date\":\"2015-07-01 16:00:00\",value:3977},{\"date\":\"2015-07-01 16:15:00\",value:3829.1667},{\"date\":\"2015-07-01 16:30:00\",value:3618.5},{\"date\":\"2015-07-01 16:45:00\",value:3398.8333},{\"date\":\"2015-07-01 17:00:00\",value:3133.3333},{\"date\":\"2015-07-01 17:15:00\",value:2956.6667},{\"date\":\"2015-07-01 17:30:00\",value:2696.8333},{\"date\":\"2015-07-01 17:45:00\",value:2456.3333},{\"date\":\"2015-07-01 18:00:00\",value:2120},{\"date\":\"2015-07-01 18:15:00\",value:1783},{\"date\":\"2015-07-01 18:30:00\",value:1487.1666},{\"date\":\"2015-07-01 18:45:00\",value:1224.3334},{\"date\":\"2015-07-01 19:00:00\",value:936.3333},{\"date\":\"2015-07-01 19:15:00\",value:690.1667},{\"date\":\"2015-07-01 19:30:00\",value:525.1667},{\"date\":\"2015-07-01 19:45:00\",value:386},{\"date\":\"2015-07-01 20:00:00\",value:258.33334},{\"date\":\"2015-07-01 20:15:00\",value:154.33333},{\"date\":\"2015-07-01 20:30:00\",value:85.333336},{\"date\":\"2015-07-01 20:45:00\",value:28.833334},{\"date\":\"2015-07-01 21:00:00\",value:10.166667},{\"date\":\"2015-07-01 21:15:00\",value:0},{\"date\":\"2015-07-01 21:30:00\",value:0},{\"date\":\"2015-07-01 21:45:00\",value:0},{\"date\":\"2015-07-01 22:00:00\",value:0},{\"date\":\"2015-07-01 22:15:00\",value:0},{\"date\":\"2015-07-01 22:30:00\",value:null},{\"date\":\"2015-07-01 22:45:00\",value:null},{\"date\":\"2015-07-01 23:00:00\",value:null},{\"date\":\"2015-07-01 23:15:00\",value:null},{\"date\":\"2015-07-01 23:30:00\",value:null},{\"date\":\"2015-07-01 23:45:00\",value:null},{\"date\":\"2015-07-02 00:00:00\",value:null},{\"date\":\"2015-07-02 00:15:00\",value:null},{\"date\":\"2015-07-02 00:30:00\",value:null},{\"date\":\"2015-07-02 00:45:00\",value:null},{\"date\":\"2015-07-02 01:00:00\",value:null},{\"date\":\"2015-07-02 01:15:00\",value:null},{\"date\":\"2015-07-02 01:30:00\",value:null},{\"date\":\"2015-07-02 01:45:00\",value:null},{\"date\":\"2015-07-02 02:00:00\",value:null},{\"date\":\"2015-07-02 02:15:00\",value:null},{\"date\":\"2015-07-02 02:30:00\",value:null},{\"date\":\"2015-07-02 02:45:00\",value:null},{\"date\":\"2015-07-02 03:00:00\",value:null},{\"date\":\"2015-07-02 03:15:00\",value:null},{\"date\":\"2015-07-02 03:30:00\",value:null},{\"date\":\"2015-07-02 03:45:00\",value:null},{\"date\":\"2015-07-02 04:00:00\",value:null},{\"date\":\"2015-07-02 04:15:00\",value:null},{\"date\":\"2015-07-02 04:30:00\",value:null},{\"date\":\"2015-07-02 04:45:00\",value:null},{\"date\":\"2015-07-02 05:00:00\",value:null},{\"date\":\"2015-07-02 05:15:00\",value:null},{\"date\":\"2015-07-02 05:30:00\",value:0},{\"date\":\"2015-07-02 05:45:00\",value:0},{\"date\":\"2015-07-02 06:00:00\",value:0.8333333},{\"date\":\"2015-07-02 06:15:00\",value:25.666666},{\"date\":\"2015-07-02 06:30:00\",value:78.166664},{\"date\":\"2015-07-02 06:45:00\",value:133.83333},{\"date\":\"2015-07-02 07:00:00\",value:227.33333},{\"date\":\"2015-07-02 07:15:00\",value:360},{\"date\":\"2015-07-02 07:30:00\",value:546},{\"date\":\"2015-07-02 07:45:00\",value:787.5},{\"date\":\"2015-07-02 08:00:00\",value:1027.3334},{\"date\":\"2015-07-02 08:15:00\",value:1286.3334},{\"date\":\"2015-07-02 08:30:00\",value:1544},{\"date\":\"2015-07-02 08:45:00\",value:1822},{\"date\":\"2015-07-02 09:00:00\",value:2077.6667},{\"date\":\"2015-07-02 09:15:00\",value:2327.6667},{\"date\":\"2015-07-02 09:30:00\",value:2568.6667},{\"date\":\"2015-07-02 09:45:00\",value:2798.1667},{\"date\":\"2015-07-02 10:00:00\",value:3063.3333},{\"date\":\"2015-07-02 10:15:00\",value:3270.3333},{\"date\":\"2015-07-02 10:30:00\",value:3449.8333},{\"date\":\"2015-07-02 10:45:00\",value:3634.5},{\"date\":\"2015-07-02 11:00:00\",value:3782.3333},{\"date\":\"2015-07-02 11:15:00\",value:3921.1667},{\"date\":\"2015-07-02 11:30:00\",value:4057.8333},{\"date\":\"2015-07-02 11:45:00\",value:4160},{\"date\":\"2015-07-02 12:00:00\",value:4279.8335},{\"date\":\"2015-07-02 12:15:00\",value:4375},{\"date\":\"2015-07-02 12:30:00\",value:4443.1665},{\"date\":\"2015-07-02 12:45:00\",value:4459.8335},{\"date\":\"2015-07-02 13:00:00\",value:4475.6665},{\"date\":\"2015-07-02 13:15:00\",value:4496.8335},{\"date\":\"2015-07-02 13:30:00\",value:4522.6665},{\"date\":\"2015-07-02 13:45:00\",value:4551.3335},{\"date\":\"2015-07-02 14:00:00\",value:4483.1665},{\"date\":\"2015-07-02 14:15:00\",value:4398.3335},{\"date\":\"2015-07-02 14:30:00\",value:4394.5},{\"date\":\"2015-07-02 14:45:00\",value:3484.5},{\"date\":\"2015-07-02 15:00:00\",value:3745},{\"date\":\"2015-07-02 15:15:00\",value:3946.3333},{\"date\":\"2015-07-02 15:30:00\",value:3465},{\"date\":\"2015-07-02 15:45:00\",value:3770.8333},{\"date\":\"2015-07-02 16:00:00\",value:3469.6667},{\"date\":\"2015-07-02 16:15:00\",value:3505},{\"date\":\"2015-07-02 16:30:00\",value:3441.3333},{\"date\":\"2015-07-02 16:45:00\",value:1259.5},{\"date\":\"2015-07-02 17:00:00\",value:2034},{\"date\":\"2015-07-02 17:15:00\",value:1334.6666},{\"date\":\"2015-07-02 17:30:00\",value:1458.1666},{\"date\":\"2015-07-02 17:45:00\",value:1236.8334},{\"date\":\"2015-07-02 18:00:00\",value:2096.8333},{\"date\":\"2015-07-02 18:15:00\",value:1082.5},{\"date\":\"2015-07-02 18:30:00\",value:342.66666},{\"date\":\"2015-07-02 18:45:00\",value:854.8333},{\"date\":\"2015-07-02 19:00:00\",value:1330},{\"date\":\"2015-07-02 19:15:00\",value:907.8333},{\"date\":\"2015-07-02 19:30:00\",value:574.8333},{\"date\":\"2015-07-02 19:45:00\",value:457},{\"date\":\"2015-07-02 20:00:00\",value:415},{\"date\":\"2015-07-02 20:15:00\",value:163.83333},{\"date\":\"2015-07-02 20:30:00\",value:220.5},{\"date\":\"2015-07-02 20:45:00\",value:8.5},{\"date\":\"2015-07-02 21:00:00\",value:4.8333335},{\"date\":\"2015-07-02 21:15:00\",value:0},{\"date\":\"2015-07-02 21:30:00\",value:0},{\"date\":\"2015-07-02 21:45:00\",value:0},{\"date\":\"2015-07-02 22:00:00\",value:0},{\"date\":\"2015-07-02 22:15:00\",value:null},{\"date\":\"2015-07-02 22:30:00\",value:null},{\"date\":\"2015-07-02 22:45:00\",value:null},{\"date\":\"2015-07-02 23:00:00\",value:null},{\"date\":\"2015-07-02 23:15:00\",value:null},{\"date\":\"2015-07-02 23:30:00\",value:null},{\"date\":\"2015-07-02 23:45:00\",value:null},{\"date\":\"2015-07-03 00:00:00\",value:null},{\"date\":\"2015-07-03 00:15:00\",value:null},{\"date\":\"2015-07-03 00:30:00\",value:null},{\"date\":\"2015-07-03 00:45:00\",value:null},{\"date\":\"2015-07-03 01:00:00\",value:null},{\"date\":\"2015-07-03 01:15:00\",value:null},{\"date\":\"2015-07-03 01:30:00\",value:null},{\"date\":\"2015-07-03 01:45:00\",value:null},{\"date\":\"2015-07-03 02:00:00\",value:null},{\"date\":\"2015-07-03 02:15:00\",value:null},{\"date\":\"2015-07-03 02:30:00\",value:null},{\"date\":\"2015-07-03 02:45:00\",value:null},{\"date\":\"2015-07-03 03:00:00\",value:null},{\"date\":\"2015-07-03 03:15:00\",value:null},{\"date\":\"2015-07-03 03:30:00\",value:null},{\"date\":\"2015-07-03 03:45:00\",value:null},{\"date\":\"2015-07-03 04:00:00\",value:null},{\"date\":\"2015-07-03 04:15:00\",value:null},{\"date\":\"2015-07-03 04:30:00\",value:null},{\"date\":\"2015-07-03 04:45:00\",value:null},{\"date\":\"2015-07-03 05:00:00\",value:null},{\"date\":\"2015-07-03 05:15:00\",value:null},{\"date\":\"2015-07-03 05:30:00\",value:null},{\"date\":\"2015-07-03 05:45:00\",value:null},{\"date\":\"2015-07-03 06:00:00\",value:0},{\"date\":\"2015-07-03 06:15:00\",value:46.333332},{\"date\":\"2015-07-03 06:30:00\",value:96.5},{\"date\":\"2015-07-03 06:45:00\",value:176.66667},{\"date\":\"2015-07-03 07:00:00\",value:277.83334},{\"date\":\"2015-07-03 07:15:00\",value:396.33334},{\"date\":\"2015-07-03 07:30:00\",value:578.6667},{\"date\":\"2015-07-03 07:45:00\",value:809},{\"date\":\"2015-07-03 08:00:00\",value:1078.6666},{\"date\":\"2015-07-03 08:15:00\",value:1329.3334},{\"date\":\"2015-07-03 08:30:00\",value:1588.5},{\"date\":\"2015-07-03 08:45:00\",value:1858.6666},{\"date\":\"2015-07-03 09:00:00\",value:2149.6667},{\"date\":\"2015-07-03 09:15:00\",value:2412.6667},{\"date\":\"2015-07-03 09:30:00\",value:2672.6667},{\"date\":\"2015-07-03 09:45:00\",value:2910.1667},{\"date\":\"2015-07-03 10:00:00\",value:3179.8333},{\"date\":\"2015-07-03 10:15:00\",value:3407.5},{\"date\":\"2015-07-03 10:30:00\",value:3614.3333},{\"date\":\"2015-07-03 10:45:00\",value:3801.6667},{\"date\":\"2015-07-03 11:00:00\",value:3963},{\"date\":\"2015-07-03 11:15:00\",value:4109.1665},{\"date\":\"2015-07-03 11:30:00\",value:4257.6665},{\"date\":\"2015-07-03 11:45:00\",value:4482},{\"date\":\"2015-07-03 12:00:00\",value:4511.5},{\"date\":\"2015-07-03 12:15:00\",value:4563.6665},{\"date\":\"2015-07-03 12:30:00\",value:4691},{\"date\":\"2015-07-03 12:45:00\",value:4637.6665},{\"date\":\"2015-07-03 13:00:00\",value:4678.5},{\"date\":\"2015-07-03 13:15:00\",value:4719.8335},{\"date\":\"2015-07-03 13:30:00\",value:4796.8335},{\"date\":\"2015-07-03 13:45:00\",value:4647.3335},{\"date\":\"2015-07-03 14:00:00\",value:4701.8335},{\"date\":\"2015-07-03 14:15:00\",value:4560.3335},{\"date\":\"2015-07-03 14:30:00\",value:4582.3335},{\"date\":\"2015-07-03 14:45:00\",value:4558.1665},{\"date\":\"2015-07-03 15:00:00\",value:4612.1665},{\"date\":\"2015-07-03 15:15:00\",value:4260.5},{\"date\":\"2015-07-03 15:30:00\",value:3999.3333},{\"date\":\"2015-07-03 15:45:00\",value:3925.5},{\"date\":\"2015-07-03 16:00:00\",value:3671.8333},{\"date\":\"2015-07-03 16:15:00\",value:3543.1667},{\"date\":\"2015-07-03 16:30:00\",value:3319.3333},{\"date\":\"2015-07-03 16:45:00\",value:2821.3333},{\"date\":\"2015-07-03 17:00:00\",value:2916.3333},{\"date\":\"2015-07-03 17:15:00\",value:2701.6667},{\"date\":\"2015-07-03 17:30:00\",value:2478.3333},{\"date\":\"2015-07-03 17:45:00\",value:2202.6667},{\"date\":\"2015-07-03 18:00:00\",value:1920.8334},{\"date\":\"2015-07-03 18:15:00\",value:1632.8334},{\"date\":\"2015-07-03 18:30:00\",value:1378.1666},{\"date\":\"2015-07-03 18:45:00\",value:1111.1666},{\"date\":\"2015-07-03 19:00:00\",value:782.3333},{\"date\":\"2015-07-03 19:15:00\",value:566},{\"date\":\"2015-07-03 19:30:00\",value:517.8333},{\"date\":\"2015-07-03 19:45:00\",value:350.83334},{\"date\":\"2015-07-03 20:00:00\",value:421.5},{\"date\":\"2015-07-03 20:15:00\",value:278.16666},{\"date\":\"2015-07-03 20:30:00\",value:176.83333},{\"date\":\"2015-07-03 20:45:00\",value:109.833336},{\"date\":\"2015-07-03 21:00:00\",value:33.333332},{\"date\":\"2015-07-03 21:15:00\",value:0},{\"date\":\"2015-07-03 21:30:00\",value:0},{\"date\":\"2015-07-03 21:45:00\",value:0},{\"date\":\"2015-07-03 22:00:00\",value:0},{\"date\":\"2015-07-03 22:15:00\",value:0},{\"date\":\"2015-07-03 22:30:00\",value:0},{\"date\":\"2015-07-03 22:45:00\",value:null},{\"date\":\"2015-07-03 23:00:00\",value:null},{\"date\":\"2015-07-03 23:15:00\",value:null},{\"date\":\"2015-07-03 23:30:00\",value:null},{\"date\":\"2015-07-03 23:45:00\",value:null},{\"date\":\"2015-07-04 00:00:00\",value:null},{\"date\":\"2015-07-04 00:15:00\",value:null},{\"date\":\"2015-07-04 00:30:00\",value:null},{\"date\":\"2015-07-04 00:45:00\",value:null},{\"date\":\"2015-07-04 01:00:00\",value:null},{\"date\":\"2015-07-04 01:15:00\",value:null},{\"date\":\"2015-07-04 01:30:00\",value:null},{\"date\":\"2015-07-04 01:45:00\",value:null},{\"date\":\"2015-07-04 02:00:00\",value:null},{\"date\":\"2015-07-04 02:15:00\",value:null},{\"date\":\"2015-07-04 02:30:00\",value:null},{\"date\":\"2015-07-04 02:45:00\",value:null},{\"date\":\"2015-07-04 03:00:00\",value:null},{\"date\":\"2015-07-04 03:15:00\",value:null},{\"date\":\"2015-07-04 03:30:00\",value:null},{\"date\":\"2015-07-04 03:45:00\",value:null},{\"date\":\"2015-07-04 04:00:00\",value:null},{\"date\":\"2015-07-04 04:15:00\",value:null},{\"date\":\"2015-07-04 04:30:00\",value:null},{\"date\":\"2015-07-04 04:45:00\",value:null},{\"date\":\"2015-07-04 05:00:00\",value:null},{\"date\":\"2015-07-04 05:15:00\",value:null},{\"date\":\"2015-07-04 05:30:00\",value:null},{\"date\":\"2015-07-04 05:45:00\",value:0},{\"date\":\"2015-07-04 06:00:00\",value:8.666667},{\"date\":\"2015-07-04 06:15:00\",value:22},{\"date\":\"2015-07-04 06:30:00\",value:50.666668},{\"date\":\"2015-07-04 06:45:00\",value:136.83333},{\"date\":\"2015-07-04 07:00:00\",value:288},{\"date\":\"2015-07-04 07:15:00\",value:501.16666},{\"date\":\"2015-07-04 07:30:00\",value:869.1667},{\"date\":\"2015-07-04 07:45:00\",value:763.6667},{\"date\":\"2015-07-04 08:00:00\",value:541.6667},{\"date\":\"2015-07-04 08:15:00\",value:1048.1666},{\"date\":\"2015-07-04 08:30:00\",value:1405.6666},{\"date\":\"2015-07-04 08:45:00\",value:1100.8334},{\"date\":\"2015-07-04 09:00:00\",value:954.3333},{\"date\":\"2015-07-04 09:15:00\",value:1917.8334},{\"date\":\"2015-07-04 09:30:00\",value:2245.6667},{\"date\":\"2015-07-04 09:45:00\",value:2261},{\"date\":\"2015-07-04 10:00:00\",value:3011.1667},{\"date\":\"2015-07-04 10:15:00\",value:3175.5},{\"date\":\"2015-07-04 10:30:00\",value:2840.3333},{\"date\":\"2015-07-04 10:45:00\",value:2241.1667},{\"date\":\"2015-07-04 11:00:00\",value:2744.3333},{\"date\":\"2015-07-04 11:15:00\",value:4311.8335},{\"date\":\"2015-07-04 11:30:00\",value:3005},{\"date\":\"2015-07-04 11:45:00\",value:3766.3333},{\"date\":\"2015-07-04 12:00:00\",value:4415.1665},{\"date\":\"2015-07-04 12:15:00\",value:4548},{\"date\":\"2015-07-04 12:30:00\",value:4518.1665},{\"date\":\"2015-07-04 12:45:00\",value:4744},{\"date\":\"2015-07-04 13:00:00\",value:4796.6665},{\"date\":\"2015-07-04 13:15:00\",value:4773.1665},{\"date\":\"2015-07-04 13:30:00\",value:4853.8335},{\"date\":\"2015-07-04 13:45:00\",value:4853.6665},{\"date\":\"2015-07-04 14:00:00\",value:5233},{\"date\":\"2015-07-04 14:15:00\",value:3172.6667},{\"date\":\"2015-07-04 14:30:00\",value:4347.5},{\"date\":\"2015-07-04 14:45:00\",value:4798.3335},{\"date\":\"2015-07-04 15:00:00\",value:3231},{\"date\":\"2015-07-04 15:15:00\",value:3883.6667},{\"date\":\"2015-07-04 15:30:00\",value:4162.1665},{\"date\":\"2015-07-04 15:45:00\",value:4327.6665},{\"date\":\"2015-07-04 16:00:00\",value:4025.6667},{\"date\":\"2015-07-04 16:15:00\",value:3790.5},{\"date\":\"2015-07-04 16:30:00\",value:3391.5},{\"date\":\"2015-07-04 16:45:00\",value:3292.3333},{\"date\":\"2015-07-04 17:00:00\",value:3089.3333},{\"date\":\"2015-07-04 17:15:00\",value:3093.6667},{\"date\":\"2015-07-04 17:30:00\",value:1675.6666},{\"date\":\"2015-07-04 17:45:00\",value:2095},{\"date\":\"2015-07-04 18:00:00\",value:2186.5},{\"date\":\"2015-07-04 18:15:00\",value:1252.1666},{\"date\":\"2015-07-04 18:30:00\",value:1042.6666},{\"date\":\"2015-07-04 18:45:00\",value:829.1667},{\"date\":\"2015-07-04 19:00:00\",value:604.3333},{\"date\":\"2015-07-04 19:15:00\",value:556.8333},{\"date\":\"2015-07-04 19:30:00\",value:489.16666},{\"date\":\"2015-07-04 19:45:00\",value:467.33334},{\"date\":\"2015-07-04 20:00:00\",value:332.5},{\"date\":\"2015-07-04 20:15:00\",value:270.16666},{\"date\":\"2015-07-04 20:30:00\",value:226},{\"date\":\"2015-07-04 20:45:00\",value:136.33333},{\"date\":\"2015-07-04 21:00:00\",value:48.833332},{\"date\":\"2015-07-04 21:15:00\",value:5.1666665},{\"date\":\"2015-07-04 21:30:00\",value:0},{\"date\":\"2015-07-04 21:45:00\",value:0},{\"date\":\"2015-07-04 22:00:00\",value:0},{\"date\":\"2015-07-04 22:15:00\",value:0},{\"date\":\"2015-07-04 22:30:00\",value:0},{\"date\":\"2015-07-04 22:45:00\",value:null},{\"date\":\"2015-07-04 23:00:00\",value:null},{\"date\":\"2015-07-04 23:15:00\",value:null},{\"date\":\"2015-07-04 23:30:00\",value:null},{\"date\":\"2015-07-04 23:45:00\",value:null},{\"date\":\"2015-07-05 00:00:00\",value:null},{\"date\":\"2015-07-05 00:15:00\",value:null},{\"date\":\"2015-07-05 00:30:00\",value:null},{\"date\":\"2015-07-05 00:45:00\",value:null},{\"date\":\"2015-07-05 01:00:00\",value:null},{\"date\":\"2015-07-05 01:15:00\",value:null},{\"date\":\"2015-07-05 01:30:00\",value:null},{\"date\":\"2015-07-05 01:45:00\",value:null},{\"date\":\"2015-07-05 02:00:00\",value:null},{\"date\":\"2015-07-05 02:15:00\",value:null},{\"date\":\"2015-07-05 02:30:00\",value:null},{\"date\":\"2015-07-05 02:45:00\",value:null},{\"date\":\"2015-07-05 03:00:00\",value:null},{\"date\":\"2015-07-05 03:15:00\",value:null},{\"date\":\"2015-07-05 03:30:00\",value:null},{\"date\":\"2015-07-05 03:45:00\",value:null},{\"date\":\"2015-07-05 04:00:00\",value:null},{\"date\":\"2015-07-05 04:15:00\",value:null},{\"date\":\"2015-07-05 04:30:00\",value:null},{\"date\":\"2015-07-05 04:45:00\",value:null},{\"date\":\"2015-07-05 05:00:00\",value:null},{\"date\":\"2015-07-05 05:15:00\",value:null},{\"date\":\"2015-07-05 05:30:00\",value:null},{\"date\":\"2015-07-05 05:45:00\",value:0},{\"date\":\"2015-07-05 06:00:00\",value:25.833334},{\"date\":\"2015-07-05 06:15:00\",value:9.5},{\"date\":\"2015-07-05 06:30:00\",value:31.833334},{\"date\":\"2015-07-05 06:45:00\",value:184.83333},{\"date\":\"2015-07-05 07:00:00\",value:152.33333},{\"date\":\"2015-07-05 07:15:00\",value:281.5},{\"date\":\"2015-07-05 07:30:00\",value:264},{\"date\":\"2015-07-05 07:45:00\",value:666.5},{\"date\":\"2015-07-05 08:00:00\",value:1026.8334},{\"date\":\"2015-07-05 08:15:00\",value:667},{\"date\":\"2015-07-05 08:30:00\",value:1300},{\"date\":\"2015-07-05 08:45:00\",value:1451.6666},{\"date\":\"2015-07-05 09:00:00\",value:1916.1666},{\"date\":\"2015-07-05 09:15:00\",value:2132.1667},{\"date\":\"2015-07-05 09:30:00\",value:1714.1666},{\"date\":\"2015-07-05 09:45:00\",value:2382.5},{\"date\":\"2015-07-05 10:00:00\",value:2426.8333},{\"date\":\"2015-07-05 10:15:00\",value:3298},{\"date\":\"2015-07-05 10:30:00\",value:2882.6667},{\"date\":\"2015-07-05 10:45:00\",value:2600},{\"date\":\"2015-07-05 11:00:00\",value:3450.6667},{\"date\":\"2015-07-05 11:15:00\",value:4239.8335},{\"date\":\"2015-07-05 11:30:00\",value:3687},{\"date\":\"2015-07-05 11:45:00\",value:3540.8333},{\"date\":\"2015-07-05 12:00:00\",value:3921.3333},{\"date\":\"2015-07-05 12:15:00\",value:4482.6665},{\"date\":\"2015-07-05 12:30:00\",value:3913},{\"date\":\"2015-07-05 12:45:00\",value:4614},{\"date\":\"2015-07-05 13:00:00\",value:4568.8335},{\"date\":\"2015-07-05 13:15:00\",value:3120.8333},{\"date\":\"2015-07-05 13:30:00\",value:3463.6667},{\"date\":\"2015-07-05 13:45:00\",value:3154.6667},{\"date\":\"2015-07-05 14:00:00\",value:4700},{\"date\":\"2015-07-05 14:15:00\",value:4397.6665},{\"date\":\"2015-07-05 14:30:00\",value:2649.5},{\"date\":\"2015-07-05 14:45:00\",value:2320},{\"date\":\"2015-07-05 15:00:00\",value:1038},{\"date\":\"2015-07-05 15:15:00\",value:691.5},{\"date\":\"2015-07-05 15:30:00\",value:279.33334},{\"date\":\"2015-07-05 15:45:00\",value:3102.3333},{\"date\":\"2015-07-05 16:00:00\",value:2244},{\"date\":\"2015-07-05 16:15:00\",value:684.5},{\"date\":\"2015-07-05 16:30:00\",value:463.66666},{\"date\":\"2015-07-05 16:45:00\",value:741.3333},{\"date\":\"2015-07-05 17:00:00\",value:640},{\"date\":\"2015-07-05 17:15:00\",value:2030.3334},{\"date\":\"2015-07-05 17:30:00\",value:2250.5},{\"date\":\"2015-07-05 17:45:00\",value:1867.8334},{\"date\":\"2015-07-05 18:00:00\",value:1982.6666},{\"date\":\"2015-07-05 18:15:00\",value:2244.6667},{\"date\":\"2015-07-05 18:30:00\",value:1228.3334},{\"date\":\"2015-07-05 18:45:00\",value:1005.5},{\"date\":\"2015-07-05 19:00:00\",value:287.83334},{\"date\":\"2015-07-05 19:15:00\",value:288.83334},{\"date\":\"2015-07-05 19:30:00\",value:593.6667},{\"date\":\"2015-07-05 19:45:00\",value:583.5},{\"date\":\"2015-07-05 20:00:00\",value:181.83333},{\"date\":\"2015-07-05 20:15:00\",value:261.5},{\"date\":\"2015-07-05 20:30:00\",value:143.83333},{\"date\":\"2015-07-05 20:45:00\",value:122.5},{\"date\":\"2015-07-05 21:00:00\",value:52.5},{\"date\":\"2015-07-05 21:15:00\",value:38.833332},{\"date\":\"2015-07-05 21:30:00\",value:0},{\"date\":\"2015-07-05 21:45:00\",value:0},{\"date\":\"2015-07-05 22:00:00\",value:0},{\"date\":\"2015-07-05 22:15:00\",value:0},{\"date\":\"2015-07-05 22:30:00\",value:0},{\"date\":\"2015-07-05 22:45:00\",value:null},{\"date\":\"2015-07-05 23:00:00\",value:null},{\"date\":\"2015-07-05 23:15:00\",value:null},{\"date\":\"2015-07-05 23:30:00\",value:null},{\"date\":\"2015-07-05 23:45:00\",value:null}],measuredBy:\"INVERTER\",timeUnit:\"QUARTER_OF_AN_HOUR\"}}";

		// l_any = JsonIterator.deserialize(json.get("power").toString());
		l_any = JsonIterator.deserialize(power_example);
		System.out.println(json.toString());
*/
	}
}
