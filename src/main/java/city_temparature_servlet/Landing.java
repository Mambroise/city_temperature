package city_temparature_servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bean.Weather;

@WebServlet("/landing")
public class Landing extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Remplace ta_clé_api par ta vraie clé API
    private static final String API_KEY = "c85d4770e6baed8cf5e48f28e1d861b5";

    public Landing() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Afficher la page d'accueil ou une vue par défaut
        request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//get the city lat and long form a geo_API
        String city = request.getParameter("cityName");
        
        String geoApiUrl = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, API_KEY);
        
	    String lat = null;
	    String lon = null;
        
        try {
        	// API call
        	URL url = new URL(geoApiUrl);
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        	connection.setRequestMethod("GET");
        	
        	//Response treatment
        	int ResponseCode = connection.getResponseCode();
        	if (ResponseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder content = new StringBuilder();
				
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				
				String contentToString = content.toString();
			    // Parse the JSON string into a JsonArray
			    JsonArray jsonArray = JsonParser.parseString(contentToString).getAsJsonArray();

			    // Get the first object in the array
			    JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

			    // Extract latitude and longitude
			    lat = jsonObject.get("lat").getAsString();
			    lon = jsonObject.get("lon").getAsString();
			} else {
				System.out.println("respondecode pas ok"+ResponseCode);
			}
        	
        } catch (Exception e){
        	System.out.println("erreur de connection");
        	
        }
        
        // Crée l'URL pour l'API
        if (lat != null & lon != null) {
        	String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric", lat, lon, API_KEY);
        	
        	try {
        		URL url = new URL(apiUrl);
        		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        		connection.setRequestMethod("GET");
        		
        		int responseCode = connection.getResponseCode();
        		if (responseCode == HttpURLConnection.HTTP_OK) {
        			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        			String inputLine;
        			StringBuilder content = new StringBuilder();
        			
        			while ((inputLine = in.readLine()) != null) {
        				content.append(inputLine);
        			}
        			in.close();
        			System.out.println(content.toString());
        			JsonObject jsonObject = JsonParser.parseString(content.toString()).getAsJsonObject();

        			String cityname = jsonObject.get("name").getAsString();
        			Double latitude = jsonObject.getAsJsonObject("coord").get("lat").getAsDouble();
        			Double longitude = jsonObject.getAsJsonObject("coord").get("lon").getAsDouble();
        			String weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
        			String weatherDetails = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
        			Double temps = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        			Double feltTemp = jsonObject.getAsJsonObject("main").get("feels_like").getAsDouble();
        			Double wind = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
        			
        			Weather cityWeather = new Weather(cityname,latitude,longitude,weather,weatherDetails,temps,feltTemp,wind);
        			
        			// Passer la réponse JSON à la JSP
        			request.setAttribute("weatherData", cityWeather);
        			// Passer la réponse JSON à la JSP
        			request.setAttribute("search", city);
        			// Utiliser un forward pour la JSP
        			request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
        		} else {
        			// Écrire le message d'erreur dans la requête
        			request.setAttribute("errorMessage", "Failed to get weather data.");
        			// Utiliser un forward pour la JSP avec message d'erreur
        			request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        		System.out.println("in the catchhh"+e);
        		// Écrire le message d'erreur dans la requête
        		request.setAttribute("errorMessage", "Error: " + e.getMessage());
        		// Utiliser un forward pour la JSP avec message d'erreur
        		request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
        	}
        }
        

    }
}
