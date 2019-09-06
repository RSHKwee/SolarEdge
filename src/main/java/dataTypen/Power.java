package dataTypen;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

//@formatter:off
/**
 * URL: /site/{siteId}/ power Example URL:
 * https://monitoringapi.solaredge.com/site/1/power?startTime=2013-05-5%2011:00:00&endTime=2013-05-05%2013:00:00&api
 * key=L4QLVQ1LOKCQX2193VSEICXW61NP6B1O
 * 
 * § Usage limitation: This API is limited to one-month period. This means that
 * the period between endTime and startTime should not exceed one month. If the
 * period is longer, the system will generate error 403 with proper description.
 * § Request: The following are parameters to include in the request: Parameter
 * Type Mandatory Description siteId Integer Yes The site identifier startTime
 * String Yes The start (date + time) to get power measurements. endTime String
 * Yes The end (date + time) to get power measurements
 * 
 * § Response: The response includes the time unit (i.e. QUARTER_OF_AN_HOUR),
 * the measurement units (e.g. Watt) and the pairs of date and power (in Watts)
 * for every date ({"date":"2013-06-04 14:00:00","value":7722.3896}) 16
 * 
 * API Description The date is calculated in ticks starting 1-1-1970 and
 * presented based on the time zone of the site. “null” means there is no data
 * for that time. § Example: JSON output: { "power":{
 * "timeUnit":"QUARTER_OF_AN_HOUR", "unit":"W", "values":[{ "date":"2013-06-04
 * 11:00:00", "value":7987.03 },
 * 
 * 
 * 
 * 
 * 
 * {"power": {"unit":"W", "values":[{"date":"2015-07-01 00:00:00",
 * "value":null}, {"date":"2015-07-01 00:15:00", "value":null},
 * {"date":"2015-07-01 00:30:00", "value":null}, {"date":"2015-07-01 00:45:00",
 * "value":null}, {"date":"2015-07-01 01:00:00", "value":null},
 * {"date":"2015-07-01 01:15:00", "value":null}, {"date":"2015-07-01 01:30:00",
 * "value":null}, {"date":"2015-07-01 01:45:00", "value":null},
 * {"date":"2015-07-01 02:00:00", "value":null}, {"date":"2015-07-01 02:15:00",
 * "value":null}, {"date":"2015-07-01 02:30:00", "value":null},
 * {"date":"2015-07-01 02:45:00", "value":null}, {"date":"2015-07-01 03:00:00",
 * "value":null}, {"date":"2015-07-01 03:15:00", "value":null},
 * {"date":"2015-07-01 03:30:00", "value":null}, {"date":"2015-07-01 03:45:00",
 * "value":null}, {"date":"2015-07-01 04:00:00", "value":null},
 * {"date":"2015-07-01 04:15:00", "value":null}, {"date":"2015-07-01 04:30:00",
 * "value":null}, {"date":"2015-07-01 04:45:00", "value":null},
 * {"date":"2015-07-01 05:00:00", "value":null}, {"date":"2015-07-01 05:15:00",
 * "value":null}, {"date":"2015-07-01 05:30:00",
 * "value":null},{"date":"2015-07-01 05:45:00","value":0},{"date":"2015-07-01
 * 06:00:00","value":0},{"date":"2015-07-01
 * 06:15:00","value":6.25},{"date":"2015-07-01
 * 06:30:00","value":35.5},{"date":"2015-07-01
 * 
 * 06:45:00","value":83.333336},{"date":"2015-07-01
 * 07:00:00","value":176},{"date":"2015-07-01
 * 07:15:00","value":315},{"date":"2015-07-01
 * 07:30:00","value":521.5},{"date":"2015-07-01
 * 07:45:00","value":814.1667},{"date":"2015-07-01
 * 08:00:00","value":1097.3334},{"date":"2015-07-01
 * 
 * 22:45:00","value":null},{"date":"2015-07-05
 * 23:00:00","value":null},{"date":"2015-07-05
 * 23:15:00","value":null},{"date":"2015-07-05 23:30:00", "value":null},
 * {"date":"2015-07-05 23:45:00", "value":null}], 
 * "measuredBy":"INVERTER",
 * "timeUnit":"QUARTER_OF_AN_HOUR"}}
 * 
 * @author René
 *
 */
// @formatter:on

public class Power {
	private String measuredBy;
	private String timeUnit;
	private Values values;

	public class Values {
		private Value[] value;

		@JsonCreator
		public Values(@JsonProperty("values") Value[] value) {
			this.value = value;
		}
	}

	public class Value {
		private String date;
		private String value;

		@JsonCreator
		public Value(@JsonProperty("date") String date, @JsonProperty("value") String value) {
			this.date = date;
			this.value = value;
		}
	}

	@JsonCreator
	public Power(@JsonProperty("measuredBy") String measuredBy, @JsonProperty("timeUnit") String timeUnit
	// , @JsonProperty("values") Values values
	) {
		this.measuredBy = measuredBy;
		this.timeUnit = timeUnit;
		// this.values = values;
	}

}
