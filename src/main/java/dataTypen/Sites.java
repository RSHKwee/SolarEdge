package dataTypen;

import java.util.ArrayList;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.annotation.JsonWrapper;

//@formatter:off
/**
 * 
 * Site List
 * 
 * URL: /sites/list Example URL (with all options):
 * https://monitoringapi.solaredge.com/sites/list?size=5&searchText=Lyon&sortProperty=name&sortOrder=ASC&api
 * key=L4QLV Q1LOKCQX2193VSEICXW61NP6B1O
 * 
 * · id - the site ID · name - the site name · account id -the account this site
 * belongs to · status - the site status (see Site Status on page 53) · peak
 * power - site peak power · CURRENCY · installationDate – site installation
 * date (format: yyyy-MM-DD hh:mm:ss ) · ptoDate – permission to operate date ·
 * notes · type – site type (see Site Type on page 53) · location - includes
 * country, state, city, address, secondary address, time zone and zip ·
 * alertQuantity - number of open alerts in this site · alertSeverity - the
 * highest alert severity in this site · publicSettings - includes if this site
 * is public and its public name
 * 
 * Example: JSON output: { "Sites": { "count":1567, "list":[ { "id":1,
 * "name":"Test", "accountId":0, "status":"Active", "peakPower":10.0,
 * "currency":"EUR", "installationDate":"2012-06-08 00:00:00", “ptoDate”:
 * “2017-05-11”, "notes":"test notes", "type":"Optimizers & Inverters",
 * "location":{ "country":"the country", "state":"the state", "city":"the city",
 * "address":"the address", "address2":"the address2", "zip":"00000",
 * "timeZone":"GMT" }, "alertQuantity":0, "alertSeverity":"NONE", "uris":{
 * "PUBLIC_URL":"the public URL name", "IMAGE_URI":"the site image link" },
 * "publicSettings":{ "name":"the public name", "isPublic":true } },
 * 
 * Data example: {"sites":{ "site":[{ "notes":"",
 * "installationDate":"2014-07-01", "type":"Optimizers & Inverters",
 * "accountId":2849, "ptoDate":null, "uris":{ "DETAILS":"/site/62137/details",
 * "OVERVIEW":"/site/62137/overview",
 * "SITE_IMAGE":"/site/62137/siteImage/IMG-20140701-WA0006.jpg",
 * "DATA_PERIOD":"/site/62137/dataPeriod" }, "peakPower":6.13,
 * "publicSettings":{ "isPublic":false }, "name":"René Kwee", "primaryModule":{
 * "modelName":"X21.345", "manufacturerName":"SunPower", "maximumPower":345 },
 * "currency":"EUR", "location": { "zip":"3871 TP", "country":"Netherlands",
 * "address":"van Dedemlaan 13", "city":"Hoevelaken", "address2":"",
 * "countryCode":"NL", "timeZone":"Europe/Amsterdam" }, "id":62137,
 * "status":"Active", "lastUpdateTime":"2019-08-11" }], "count":1 }}
 *
 * @author René
 * 
 */
// @formatter:on

public class Sites {

	private ArrayList<Site> site = new ArrayList<Site>();
	private Integer count = 0;

	public String example = "{\"sites\":{\"site\":[{\"notes\":\"\",installationDate:\"2014-07-01\",type:\"Optimizers & Inverters\",accountId:2849,ptoDate:null,uris:{\"DETAILS\":\"/site/62137/details\",OVERVIEW:\"/site/62137/overview\",SITE_IMAGE:\"/site/62137/siteImage/IMG-20140701-WA0006.jpg\",DATA_PERIOD:\"/site/62137/dataPeriod\"},peakPower:6.13,publicSettings:{\"isPublic\":false},name:\"René Kwee\",primaryModule:{\"modelName\":\"X21.345\",manufacturerName:\"SunPower\",maximumPower:345},currency:\"EUR\",location:{\"zip\":\"3871 TP\",country:\"Netherlands\",address:\"van Dedemlaan 13\",city:\"Hoevelaken\",address2:\"\",countryCode:\"NL\",timeZone:\"Europe/Amsterdam\"},id:62137,status:\"Active\",lastUpdateTime:\"2019-08-07\"}],count:1}}";

	public class Site {
		private Integer id;
		private String notes;
		private String installationDate;
		private String type;
		private Integer accountId;
		private String ptoDate;
		private String name;
		private String currency;
		private Double peakPower;
		private String status;
		private String lastUpdateTime;

		private Uris uris;
		private PublicSettings publicSettings;
		private PrimaryModule primaryModule;
		private Location location;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public String getInstallationDate() {
			return installationDate;
		}

		public void setInstallationDate(String installationDate) {
			this.installationDate = installationDate;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getAccountId() {
			return accountId;
		}

		public void setAccountId(Integer accountId) {
			this.accountId = accountId;
		}

		public String getPtoDate() {
			return ptoDate;
		}

		public void setPtoDate(String ptoDate) {
			this.ptoDate = ptoDate;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public Double getPeakPower() {
			return peakPower;
		}

		public void setPeakPower(Double peakPower) {
			this.peakPower = peakPower;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getLastUpdateTime() {
			return lastUpdateTime;
		}

		public void setLastUpdateTime(String lastUpdateTime) {
			this.lastUpdateTime = lastUpdateTime;
		}

		public Uris getUris() {
			return uris;
		}

		public void setUris(Uris uris) {
			this.uris = uris;
		}

		public PublicSettings getPublicSettings() {
			return publicSettings;
		}

		public void setPublicSettings(PublicSettings publicSettings) {
			this.publicSettings = publicSettings;
		}

		public PrimaryModule getPrimaryModule() {
			return primaryModule;
		}

		public void setPrimaryModule(PrimaryModule primaryModule) {
			this.primaryModule = primaryModule;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public ArrayList<Site> getSite() {
			return site;
		}

		public Integer getCount() {
			return count;
		}

		@JsonCreator
		public Site(@JsonProperty("ïd") Integer id, @JsonProperty("notes") String notes,
		    @JsonProperty("installationDate") String installationDate, @JsonProperty("type") String type,
		    @JsonProperty("accountId") Integer accountId, @JsonProperty("ptoDate") String ptoDate,
		    @JsonProperty("name") String name, @JsonProperty("currency") String currency,
		    @JsonProperty("peakPower") Double peakPower, @JsonProperty("status") String status,
		    @JsonProperty("lastUpdateTime") String lastUpdateTime,

		    @JsonProperty("uris") Uris uris, @JsonProperty("publicSettings") PublicSettings publicSettings,
		    @JsonProperty("primaryModule") PrimaryModule primaryModule, @JsonProperty("location") Location location) {
			this.id = id;
			this.notes = notes;
			this.installationDate = installationDate;
			this.type = type;
			this.accountId = accountId;
			this.ptoDate = ptoDate;
			this.name = name;
			this.currency = currency;
			this.peakPower = peakPower;
			this.status = status;
			this.lastUpdateTime = lastUpdateTime;

			this.uris = uris;
			this.publicSettings = publicSettings;
			this.primaryModule = primaryModule;
			this.location = location;
		}
	}

	public class Uris {
		private String details;
		private String overview;
		private String site_image;
		private String data_period;

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}

		public String getOverview() {
			return overview;
		}

		public void setOverview(String overview) {
			this.overview = overview;
		}

		public String getSite_image() {
			return site_image;
		}

		public void setSite_image(String site_image) {
			this.site_image = site_image;
		}

		public String getData_period() {
			return data_period;
		}

		public void setData_period(String data_period) {
			this.data_period = data_period;
		}

		public Uris(@JsonProperty("details") String details, @JsonProperty("overview") String overview,
		    @JsonProperty("site_image") String site_image, @JsonProperty("data_period") String data_period) {
			this.details = details;
			this.overview = overview;
			this.site_image = site_image;
			this.data_period = data_period;
		}
	}

	public class PublicSettings {
		private Boolean isPublic;

		public Boolean getIsPublic() {
			return isPublic;
		}

		public void setIsPublic(Boolean isPublic) {
			this.isPublic = isPublic;
		}

		public PublicSettings(@JsonProperty("isPublic") Boolean isPublic) {
			this.isPublic = isPublic;
		}
	}

	public class PrimaryModule {
		private String modelName;
		private String manufacturerName;
		private Integer maximumPower;

		public String getModelName() {
			return modelName;
		}

		public void setModelName(String modelName) {
			this.modelName = modelName;
		}

		public String getManufacturerName() {
			return manufacturerName;
		}

		public void setManufacturerName(String manufacturerName) {
			this.manufacturerName = manufacturerName;
		}

		public Integer getMaximumPower() {
			return maximumPower;
		}

		public void setMaximumPower(Integer maximumPower) {
			this.maximumPower = maximumPower;
		}

		public PrimaryModule(@JsonProperty("modelName") String modelName,
		    @JsonProperty("manufacturerName") String manufacturerName, @JsonProperty("maximumPower") Integer maximumPower) {
			this.modelName = modelName;
			this.manufacturerName = manufacturerName;
			this.maximumPower = maximumPower;
		}
	}

	public class Location {
		private String country;
		private String address;
		private String city;
		private String address2;
		private String countryCode;
		private String timeZone;

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		@JsonCreator
		public Location(@JsonProperty("country") String country, @JsonProperty("address") String address,
		    @JsonProperty("city") String city, @JsonProperty("address2") String address2,
		    @JsonProperty("countryCode") String countryCode, @JsonProperty("timeZone") String timeZone) {
			this.country = country;
			this.address = address;
			this.city = city;
			this.address2 = address2;
			this.countryCode = countryCode;
			this.timeZone = timeZone;
		}

	}

	public Sites(Integer id, String notes, String installationDate, String type, Integer accountId, String ptoDate,
	    String name, String currency, Double peakPower, String status, String lastUpdateTime,

	    Uris uris, PublicSettings publicSettings, PrimaryModule primaryModule, Location location,

	    ArrayList<Site> site, Integer count) {

	}

	@JsonWrapper
	public void setSite(@JsonProperty("ïd") Integer id, @JsonProperty("notes") String notes,
	    @JsonProperty("installationDate") String installationDate, @JsonProperty("type") String type,
	    @JsonProperty("accountId") Integer accountId, @JsonProperty("ptoDate") String ptoDate,
	    @JsonProperty("name") String name, @JsonProperty("currency") String currency,
	    @JsonProperty("peakPower") Double peakPower, @JsonProperty("status") String status,
	    @JsonProperty("lastUpdateTime") String lastUpdateTime,

	    @JsonProperty("uris") Uris uris, @JsonProperty("publicSettings") PublicSettings publicSettings,
	    @JsonProperty("primaryModule") PrimaryModule primaryModule, @JsonProperty("location") Location location) {
		Site l_site = new Site(id, notes, installationDate, type, accountId, ptoDate, name, currency, peakPower, status,
		    lastUpdateTime,

		    uris, publicSettings, primaryModule, location);
		this.site.add(l_site);
		this.count++;
	}

}