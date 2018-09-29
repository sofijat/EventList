package code;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Decoder {
	private ArrayList<String> cityList = new ArrayList<String>();
	
	public void decodeJSONCity(String cities) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(cities);
			JSONArray results = (JSONArray) obj.get("results");
			System.out.println("Gradovi u Srbiji: ");
			int order = 1;

			Iterator i = results.iterator(); 
			while(i.hasNext()){
				JSONObject city = (JSONObject) i.next();
				String cityName = city.get("city").toString();
				System.out.println(order + ". "+city.get("city").toString());
				cityList.add(cityName);
				order++;
				
				i.next();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void decodeJSONEvent(String events, String selectedCity) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(events);
			JSONArray results = (JSONArray) obj.get("results");
			System.out.println("Dogadjaji u gradu " + selectedCity + ": ");
			System.out.println();

			Iterator i = results.iterator(); 
			if (!i.hasNext()) {
				System.out.print("U odabranom gradu nema dogadjaja!");
				return;
			}
			
			while(i.hasNext()){
				JSONObject event = (JSONObject) i.next();

				System.out.println("Naziv: " + event.get("name"));
				System.out.println("Opis: " + event.get("description"));
				System.out.println("Status: " + event.get("status"));
				
				String time = event.get("time").toString();
				Long millis = Long.parseLong(time);

				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				
				System.out.println("Datum i vreme: " + formatter.format(millis));

				System.out.println("***");
				System.out.println();
				if (i.hasNext()) i.next();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCity(int num) {
		return cityList.get(num - 1);
	}

	public ArrayList<String> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}
	
	
}
