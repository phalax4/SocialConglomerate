import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class WeatherU {
	public String getW() throws IOException{
		String J = "";
		String state = "CA";
		String city = "San_Jose";
		String web = "http://api.wunderground.com/api/4ebc3b3ae2c41145/geolookup/conditions/forecast/q/"+state+"/"+city+".json";

		 URL oracle = new URL(web);
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	       
	        while ((inputLine = in.readLine()) != null){
	        	JsonParser jp = new JsonParser();
		    	JsonElement root = jp.parse(in);
		    	JsonObject rootobj = root.getAsJsonObject();
		        
	        	String forecast = rootobj.get("forecast").getAsJsonObject().get("txt_forecast").getAsJsonObject().get("forecastday").getAsJsonArray().get(0).getAsJsonObject().get("fcttext").getAsString();
	    		String pic = rootobj.get("forecast").getAsJsonObject().get("txt_forecast").getAsJsonObject().get("forecastday").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject().get("icon_url").getAsString();
	        	//System.out.println(pic+ " " + forecast);
			//System.out.println(forecast);
	           	//System.out.println(inputLine);
	    		J = pic + " " + forecast;
	        }
	        return J;
	    }
	}