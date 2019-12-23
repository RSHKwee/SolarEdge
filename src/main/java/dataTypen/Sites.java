package dataTypen;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

public class Sites {
	ArrayList<SiteDetails> m_SiteDetails = new ArrayList<SiteDetails>();

	public void ReadSites(JSONObject a_json) {
		Any l_sites = JsonIterator.deserialize(a_json.get("sites").toString());
		Any l_siteList = l_sites.get("site");
		List<Any> l_anySites = l_siteList.asList();
		l_anySites.forEach(l_anySite -> {
			SiteDetails v_sitedetail = new SiteDetails();
			v_sitedetail.ReadSite(l_anySite);
			m_SiteDetails.add(v_sitedetail);
		});
	}

	public ArrayList<SiteDetails> getSiteDetails() {
		return m_SiteDetails;
	}

}
