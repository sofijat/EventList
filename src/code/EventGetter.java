package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EventGetter {
	public static String host = "api.meetup.com";
	public static String path = "/2/open_events";
	public static String country = "rs";
	
	public String getEvents(String selectedCity, String key) {
		String responseString = "";
		try {		
			URI request = new URIBuilder().setScheme("http").setHost(host).setPath(path).setParameter("country", country).setParameter("city", selectedCity).setParameter("key", key).build();
			HttpGet get = new HttpGet(request);
			
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			responseString = EntityUtils.toString(response.getEntity());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return responseString;
	}
	
	public String getAPIKey() {
		String key = "";
		
		try {
			BufferedReader buff = new BufferedReader(new FileReader("apikey.txt"));
			key = buff.readLine().toString();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key;
	}
}
