package code;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String cities;
		String events;
		String key;
		int selectedCityNum;
		String selectedCityName;

		Scanner reader = new Scanner(System.in);
		
		CityGetter cityGetter = new CityGetter();
		EventGetter eventGetter = new EventGetter();
		Decoder decoder = new Decoder();
		cities = cityGetter.getCity();
		
		decoder.decodeJSONCity(cities);
		System.out.println();
		System.out.println("Unesite redni broj grada cije dogadjaje zelite da vidite:");
		
		selectedCityNum = reader.nextInt();
		if (selectedCityNum > decoder.getCityList().size() || selectedCityNum < 1) {
			System.out.println("Redni broj koji ste uneli ne odgovara nijednom gradu!");
		} else {
			selectedCityName = decoder.getCity(selectedCityNum);
		
			key = eventGetter.getAPIKey();

			events = eventGetter.getEvents(selectedCityName, key);
			decoder.decodeJSONEvent(events, selectedCityName);
		
			reader.close();
		}

	}

}
