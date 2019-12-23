package inlezen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class Berichten {
	private String m_apikey = "";
	private String m_SolarEdgeUrl = "https://monitoringapi.solaredge.com";
	private String m_siteId = "";

	public Berichten(String a_ApiKey, String a_SiteId) {
		m_apikey = a_ApiKey;
		m_siteId = a_SiteId;
	}

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

	/**
	 * 
	 * @param a_StartDate
	 *          yyyy-mm-dd
	 * @param a_EndDate
	 *          yyyy-mm-dd
	 * @return
	 */
	public JSONObject getPower(String a_StartDate, String a_EndDate) {
		JSONObject json = null;
		try {
			json = readJsonFromUrl(m_SolarEdgeUrl + "/site/" + m_siteId + "/power?startTime=" + a_StartDate + "%2000:01:00"
			    + "&endTime=" + a_EndDate + "%2023:59:00" + "&api_key=" + m_apikey);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public JSONObject getSites() {
		JSONObject json = null;
		try {
			json = readJsonFromUrl(
			    m_SolarEdgeUrl + "/sites/list?size=5&searchText=Lyon&sortProperty=name&sortOrder=ASC&api_key=" + m_apikey);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public JSONObject getSiteDetails() {
		JSONObject json = null;
		try {
			json = readJsonFromUrl(m_SolarEdgeUrl + "/site/" + m_siteId + "/details.json?&api_key=" + m_apikey);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 
	 * @param a_StartDate
	 *          yyyy-mm-dd
	 * @param a_EndDate
	 *          yyyy-mm-dd
	 * @return
	 */
	public JSONObject getTimeFrameEnergy(String a_StartDate, String a_EndDate) {
		JSONObject json = null;
		try {
			json = readJsonFromUrl(m_SolarEdgeUrl + "/site/" + m_siteId + "/timeFrameEnergy?startDate=" + a_StartDate
			    + "&endDate=" + a_EndDate + "&api_key=" + m_apikey);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
