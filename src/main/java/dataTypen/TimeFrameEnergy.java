package dataTypen;

import org.json.JSONObject;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

//@formatter:off
/**
Site Energy – Time Period
Description: Return the site total energy produced for a given period. 
URL: /site/{siteId}/timeFrameEnergy
Example URL: https://monitoringapi.solaredge.com/site/1/timeFrameEnergy?startDate=2013-05-01&endDate=2013-05-06&apikey=L4QLVQ1LOKCQX2193VSEICXW61NP6B1O 
Method: GET
Accepted formats: JSON and XML
    • Usage limitation: This API is limited to one year when using timeUnit=DAY (i.e., daily resolution). This means that the period between endTime and startTime should not exceed one year). If the period is longer, the system will generate error 403 with proper description
    • Request: The following are parameters to include in the request:
    • Response: The response includes the energy summary for the given time period with units of measurement (e.g. Wh) The date is calculated based on the time zone where the site is located.

 * 
Time Frame Energy: 
{
  "timeFrameEnergy":
  {
    "unit":"Wh",
    "endLifetimeEnergy":
    {
	    "date":"2015-06-29",
	    "unit":"Wh",
	    "energy":5807860
	  },
    "startLifetimeEnergy":
    {
	    "date":"2014-07-02",
		  "unit":"Wh",
		  "energy":37216.4
	  },
    "measuredBy":"INVERTER",
    "energy":5770643.5
  }
}
 * 
 * @author René
 *
 */
// @formatter:on

public class TimeFrameEnergy {
	private String measuredBy;
	private String energy;
	private String unit;
	private EnergyValue StartValue;
	private EnergyValue EndValue;

	public void ReadTimeFrameEnergy(JSONObject a_json) {
		Any l_power = JsonIterator.deserialize(a_json.get("timeFrameEnergy").toString());

		Any v_unit = l_power.get("unit");
		Any v_measured = l_power.get("measuredBy");
		Any v_timeUnit = l_power.get("energy");
		Any l_endLifevalues = l_power.get("endLifetimeEnergy");
		Any l_startLifevalues = l_power.get("startLifetimeEnergy");

		unit = v_unit.toString();
		measuredBy = v_measured.toString();
		energy = v_timeUnit.toString();

		String v_date = l_endLifevalues.get("date").toString();
		String vv_unit = l_endLifevalues.get("unit").toString();
		double v_value = l_endLifevalues.get("energy").toDouble();

		EndValue = new EnergyValue(v_date, vv_unit, v_value);

		String v_sdate = l_startLifevalues.get("date").toString();
		String vv_sunit = l_startLifevalues.get("unit").toString();
		double v_svalue = l_startLifevalues.get("energy").toDouble();

		StartValue = new EnergyValue(v_sdate, vv_sunit, v_svalue);
	}

	public String getUnit() {
		return unit;
	}

	public String getMeasuredBy() {
		return measuredBy;
	}

	public String getEnergy() {
		return energy;
	}

	public EnergyValue getStartValue() {
		return StartValue;
	}

	public EnergyValue getEndValue() {
		return EndValue;
	}

	public class EnergyValue {
		private String date;
		private String unit;
		private double value;

		public EnergyValue(String a_date, String a_unit, double a_value) {
			date = a_date;
			unit = a_unit;
			value = a_value;
		}

		public double getValue() {
			return value;
		}

		public String getUnit() {
			return unit;
		}

		public String getDate() {
			return date;
		}
	}
}
