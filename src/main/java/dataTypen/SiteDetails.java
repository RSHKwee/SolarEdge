package dataTypen;

import org.json.JSONObject;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

//@formatter:off
/**
 * 
 * URL: /site/{siteId}/dataPeriod Example URL:
 * https://monitoringapi.solaredge.com/site/1/dataPeriod?apikey=L4QLVQ1LOKCQX2193VSEICXW61NP6B1O
 * 
 * {"sites":
 * {"site":[
 *   {
 *     "notes":"",
 *     "installationDate":"2014-07-01",
 *     "type":"Optimizers & Inverters",
 *     "accountId":2849,
 *     "ptoDate":null,
 *     "uris":
 *     {
 *       "DETAILS":"/site/62137/details",
 *       "OVERVIEW":"/site/62137/overview",
 *       "SITE_IMAGE":"/site/62137/siteImage/IMG-20140701-WA0006.jpg",
 *       "DATA_PERIOD":"/site/62137/dataPeriod"
 *     },
 *     "peakPower":6.13,
 *     "publicSettings":
 *     {
 *       "isPublic":false
 *     },
 *     "name":"René Kwee",
 *     "primaryModule":
 *     {
 *       "modelName":"X21.345",
 *       "manufacturerName":"SunPower",
 *       "maximumPower":345
 *     },
 *     "currency":"EUR",
 *     "location":
 *     {
 *       "zip":"3871 TP",
 *       "country":"Netherlands",
 *       "address":"van Dedemlaan 13",
 *       "city":"Hoevelaken",
 *       "address2":"",
 *       "countryCode":"NL",
 *       "timeZone":"Europe/Amsterdam"
 *     },
 *     "id":62137,
 *     "status":"Active",
 *     "lastUpdateTime":"2019-12-15"
 *   }],
 *   "count":1
 * }
 * 
 * @author René
 *
 */
//@formatter:on
public class SiteDetails {
	private String m_site;
	private int m_count;

	private String m_notes;
	private String m_installationDate;
	private String m_type;
	private String m_accountId;
	private String m_ptoDate;
	private double m_peakPower;
	private String m_name;
	private String m_currency;

	private String m_id;
	private String m_status;
	private String m_lastUpdateTime;

	private String m_urisDetails;
	private String m_urisOverview;
	private String m_urisSiteImage;
	private String m_urisDataPeriod;

	private Boolean m_publicSettingsPublic;

	private String m_primaryModuleModel;
	private String m_primaryModuleManufacturer;
	private double m_primaryMaximumPower;

	private String m_locationZip;
	private String m_locationCountry;
	private String m_locationAddress;
	private String m_locationCity;
	private String m_locationAddress2;
	private String m_locationCountryCode;
	private String m_locationTimeZone;

	public void ReadSiteDetails(JSONObject a_json) {
		Any l_site = JsonIterator.deserialize(a_json.get("details").toString());
		ReadSite(l_site);
	}

	public void ReadSite(Any l_site) {
		Any v_notes = l_site.get("notes");
		Any v_installationDate = l_site.get("installationDate");
		Any v_type = l_site.get("type");
		Any v_accountId = l_site.get("accountId");
		Any v_ptoDate = l_site.get("ptoDate");
		Any v_peakPower = l_site.get("peakPower");
		Any v_name = l_site.get("name");
		Any v_currency = l_site.get("currency");
		Any v_id = l_site.get("id");
		Any v_status = l_site.get("status");
		Any v_lastUpdateTime = l_site.get("lastUpdateTime");

		Any l_uris = l_site.get("uris");
		Any l_publicSettings = l_site.get("publicSettings");
		Any l_primaryModule = l_site.get("primaryModule");
		Any l_location = l_site.get("location");

		Any v_urisDetails = l_uris.get("DETAILS");
		Any v_urisOverview = l_uris.get("OVERVIEW");
		Any v_urisSiteImage = l_uris.get("SITE_IMAGE");
		Any v_urisDataPeriod = l_uris.get("DATA_PERIOD");

		Any v_publicSettingsPublic = l_publicSettings.get("isPublic");

		Any v_primaryModuleModel = l_primaryModule.get("modelName");
		Any v_primaryModuleManufacturer = l_primaryModule.get("manufacturerName");
		Any v_primaryMaximumPower = l_primaryModule.get("maximumPower");

		Any v_locationZip = l_location.get("zip");
		Any v_locationCountry = l_location.get("country");
		Any v_locationAddress = l_location.get("address");
		Any v_locationCity = l_location.get("city");
		Any v_locationAddress2 = l_location.get("address2");
		Any v_locationCountryCode = l_location.get("countryCode");
		Any v_locationTimeZone = l_location.get("timeZone");
		m_site = l_site.toString();

		m_notes = v_notes.toString();
		m_installationDate = v_installationDate.toString();
		m_type = v_type.toString();
		m_accountId = v_accountId.toString();
		m_ptoDate = v_ptoDate.toString();
		m_peakPower = v_peakPower.toDouble();
		m_name = v_name.toString();
		m_currency = v_currency.toString();
		m_id = v_id.toString();
		m_status = v_status.toString();
		m_lastUpdateTime = v_lastUpdateTime.toString();

		m_urisDetails = v_urisDetails.toString();
		m_urisOverview = v_urisOverview.toString();
		m_urisSiteImage = v_urisSiteImage.toString();
		m_urisDataPeriod = v_urisDataPeriod.toString();

		m_publicSettingsPublic = v_publicSettingsPublic.toBoolean();

		m_primaryModuleModel = v_primaryModuleModel.toString();
		m_primaryModuleManufacturer = v_primaryModuleManufacturer.toString();
		m_primaryMaximumPower = v_primaryMaximumPower.toDouble();

		m_locationZip = v_locationZip.toString();
		m_locationCountry = v_locationCountry.toString();
		m_locationAddress = v_locationAddress.toString();
		m_locationCity = v_locationCity.toString();
		m_locationAddress2 = v_locationAddress2.toString();
		m_locationCountryCode = v_locationCountryCode.toString();
		m_locationTimeZone = v_locationTimeZone.toString();
	}

	public String getM_site() {
		return m_site;
	}

	public void setM_site(String m_site) {
		this.m_site = m_site;
	}

	public int getM_count() {
		return m_count;
	}

	public void setM_count(int m_count) {
		this.m_count = m_count;
	}

	public String getM_notes() {
		return m_notes;
	}

	public void setM_notes(String m_notes) {
		this.m_notes = m_notes;
	}

	public String getM_installationDate() {
		return m_installationDate;
	}

	public void setM_installationDate(String m_installationDate) {
		this.m_installationDate = m_installationDate;
	}

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
	}

	public String getM_accountId() {
		return m_accountId;
	}

	public void setM_accountId(String m_accountId) {
		this.m_accountId = m_accountId;
	}

	public String getM_ptoDate() {
		return m_ptoDate;
	}

	public void setM_ptoDate(String m_ptoDate) {
		this.m_ptoDate = m_ptoDate;
	}

	public double getM_peakPower() {
		return m_peakPower;
	}

	public void setM_peakPower(double m_peakPower) {
		this.m_peakPower = m_peakPower;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_currency() {
		return m_currency;
	}

	public void setM_currency(String m_currency) {
		this.m_currency = m_currency;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_status() {
		return m_status;
	}

	public void setM_status(String m_status) {
		this.m_status = m_status;
	}

	public String getM_lastUpdateTime() {
		return m_lastUpdateTime;
	}

	public void setM_lastUpdateTime(String m_lastUpdateTime) {
		this.m_lastUpdateTime = m_lastUpdateTime;
	}

	public String getM_urisDetails() {
		return m_urisDetails;
	}

	public void setM_urisDetails(String m_urisDetails) {
		this.m_urisDetails = m_urisDetails;
	}

	public String getM_urisOverview() {
		return m_urisOverview;
	}

	public void setM_urisOverview(String m_urisOverview) {
		this.m_urisOverview = m_urisOverview;
	}

	public String getM_urisSiteImage() {
		return m_urisSiteImage;
	}

	public void setM_urisSiteImage(String m_urisSiteImage) {
		this.m_urisSiteImage = m_urisSiteImage;
	}

	public String getM_urisDataPeriod() {
		return m_urisDataPeriod;
	}

	public void setM_urisDataPeriod(String m_urisDataPeriod) {
		this.m_urisDataPeriod = m_urisDataPeriod;
	}

	public Boolean getM_publicSettingsPublic() {
		return m_publicSettingsPublic;
	}

	public void setM_publicSettingsPublic(Boolean m_publicSettingsPublic) {
		this.m_publicSettingsPublic = m_publicSettingsPublic;
	}

	public String getM_primaryModuleModel() {
		return m_primaryModuleModel;
	}

	public void setM_primaryModuleModel(String m_primaryModuleModel) {
		this.m_primaryModuleModel = m_primaryModuleModel;
	}

	public String getM_primaryModuleManufacturer() {
		return m_primaryModuleManufacturer;
	}

	public void setM_primaryModuleManufacturer(String m_primaryModuleManufacturer) {
		this.m_primaryModuleManufacturer = m_primaryModuleManufacturer;
	}

	public double getM_primaryMaximumPower() {
		return m_primaryMaximumPower;
	}

	public void setM_primaryMaximumPower(double m_primaryMaximumPower) {
		this.m_primaryMaximumPower = m_primaryMaximumPower;
	}

	public String getM_locationZip() {
		return m_locationZip;
	}

	public void setM_locationZip(String m_locationZip) {
		this.m_locationZip = m_locationZip;
	}

	public String getM_locationCountry() {
		return m_locationCountry;
	}

	public void setM_locationCountry(String m_locationCountry) {
		this.m_locationCountry = m_locationCountry;
	}

	public String getM_locationAddress() {
		return m_locationAddress;
	}

	public void setM_locationAddress(String m_locationAddress) {
		this.m_locationAddress = m_locationAddress;
	}

	public String getM_locationCity() {
		return m_locationCity;
	}

	public void setM_locationCity(String m_locationCity) {
		this.m_locationCity = m_locationCity;
	}

	public String getM_locationAddress2() {
		return m_locationAddress2;
	}

	public void setM_locationAddress2(String m_locationAddress2) {
		this.m_locationAddress2 = m_locationAddress2;
	}

	public String getM_locationCountryCode() {
		return m_locationCountryCode;
	}

	public void setM_locationCountryCode(String m_locationCountryCode) {
		this.m_locationCountryCode = m_locationCountryCode;
	}

	public String getM_locationTimeZone() {
		return m_locationTimeZone;
	}

	public void setM_locationTimeZone(String m_locationTimeZone) {
		this.m_locationTimeZone = m_locationTimeZone;
	}

}
