package inlezen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import dataTypen.Power;

import dataTypen.TimeFrameEnergy;
import dataTypen.TimeFrameEnergy.EnergyValue;
import library.Berichten;
import dataTypen.Power.Value;
import dataTypen.SiteDetails;
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
		String v_apikeykaal = "ZSFYC1HPDSUXQH6KCDKSUJTFDGW884LE";

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

		Berichten bericht = new Berichten(v_apikeykaal, v_siteId);
		JSONObject json = readJsonFromUrl(
		    "https://monitoringapi.solaredge.com/sites/list?size=5&searchText=Lyon&sortProperty=name&sortOrder=ASC&api_key=ZSFYC1HPDSUXQH6KCDKSUJTFDGW884LE");
		System.out.println(json.toString());
		System.out.println(json.get("sites"));

		json = readJsonFromUrl(v_SolarEdgeUrl + "/" + v_listSites + "&" + v_apikey);
		System.out.println(json.toString());
		System.out.println(json.get("sites"));

		JsonIterator iter = JsonIterator.parse(json.get("sites").toString());

		System.out.println("v_mapObj: " + v_mapObj.toString());
		System.out.println("v_mapObj2: " + v_mapObj2.toString());

		Sites sites = new Sites();
		sites.ReadSites(bericht.getSites());

		SiteDetails site = new SiteDetails();
		site.ReadSiteDetails(bericht.getSiteDetails());

		TimeFrameEnergy timeframe = new TimeFrameEnergy();
		timeframe.ReadTimeFrameEnergy(bericht.getTimeFrameEnergy(v_startDate, v_endDate));
		EnergyValue StartValue = timeframe.getStartValue();
		EnergyValue EndValue = timeframe.getEndValue();

		Power power = new Power();
		power.ReadPower(bericht.getPower("2019-12-01", "2019-12-05"));
		ArrayList<Value> values = new ArrayList<Value>();
		values = power.getValues();

		/*
		 * values.forEach(value -> { try { String v_date = value.getDate(); double
		 * v_value = value.getValue(); System.out.println(" Datum:" + v_date +
		 * " Waarde:" + v_value); } catch (Exception e) {
		 * System.out.println(e.toString()); } });
		 */
	}
}
