package inlezen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
	static private void updateCustomer() {
		try {
			URL url = new URL(
			    "https://monitoringapi.solaredge.com/sites/list?size=5&searchText=Lyon&sortProperty=name&sortOrder=ASC&api_key=ZSFYC1HPDSUXQH6KCDKSUJTFDGW884LE");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("Content-Type", "application/xml");
			// connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Type", "application/ms-excel");
			// connection.setRequestProperty("Content-Type", "text/csv");

			InputStream is = connection.getInputStream();
			String v_str = convertStreamToString(is);
			is.close();

			System.out.println(v_str);
			// ArrayList<int>
			// os.re.read()

			connection.getResponseCode();
			connection.disconnect();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	public static void main(String[] args) throws IOException {
		updateCustomer();

	}

}
