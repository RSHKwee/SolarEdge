package library;

import java.io.IOException;

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

	/**
	 * This API is limited to one-month period. This means that the period between
	 * endTime and startTime should not exceed one month.
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
			json = ReadJsonFromUrl.readJsonFromUrl(m_SolarEdgeUrl + "/site/" + m_siteId + "/power?startTime=" + a_StartDate
			    + "%2000:01:00" + "&endTime=" + a_EndDate + "%2023:59:00" + "&api_key=" + m_apikey);
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
			json = ReadJsonFromUrl.readJsonFromUrl(
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
			json = ReadJsonFromUrl
			    .readJsonFromUrl(m_SolarEdgeUrl + "/site/" + m_siteId + "/details.json?&api_key=" + m_apikey);
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
	 * This API is limited to one year when using timeUnit=DAY (i.e., daily
	 * resolution). This means that the period between endTime and startTime should
	 * not exceed one year).
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
			json = ReadJsonFromUrl.readJsonFromUrl(m_SolarEdgeUrl + "/site/" + m_siteId + "/timeFrameEnergy?startDate="
			    + a_StartDate + "&endDate=" + a_EndDate + "&api_key=" + m_apikey);
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
