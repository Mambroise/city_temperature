package bean;

public class Weather {
	private String cityname;
	private Double latitude;
	private Double longitude;
	private String weather;
	private String weatherDetails;
	private Double temps;
	private Double feltTemp;
	private Double wind;
	
	public Weather(String cityname, Double latitude, Double longitude, String weather, String weatherDetails,
			Double temps, Double feltTemp, Double wind) {
		this.cityname = cityname;
		this.latitude = latitude;
		this.longitude = longitude;
		this.weather = weather;
		this.weatherDetails = weatherDetails;
		this.temps = temps;
		this.feltTemp = feltTemp;
		this.wind = wind;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWeatherDetails() {
		return weatherDetails;
	}

	public void setWeatherDetails(String weatherDetails) {
		this.weatherDetails = weatherDetails;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Double getFeltTemp() {
		return feltTemp;
	}

	public void setFeltTemp(Double feltTemp) {
		this.feltTemp = feltTemp;
	}

	public Double getWind() {
		return wind;
	}

	public void setWind(Double wind) {
		this.wind = wind;
	}
	
	
	
	

}
