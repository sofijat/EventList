package code;

import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CityGetter {
	public static String host = "api.meetup.com";
	public static String path = "/2/cities";
	public static String country = "rs";
	
	public String getCity() {
		String responseString = "";
		
		try {
			URI request = new URIBuilder().setScheme("http").setHost(host).setPath(path).setParameter("country", country).build();
			
			HttpGet get = new HttpGet(request);
			
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			
			responseString = EntityUtils.toString(response.getEntity());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseString;
	}
	
}
