package kr.or.common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StringToJsonParser {
	public static JSONObject JsonParser(String str) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JSONObject) obj;
	}
}
