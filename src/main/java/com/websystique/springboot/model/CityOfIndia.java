package com.websystique.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityOfIndia {

	@JsonProperty(value = "City")
	private String city;
	@JsonProperty(value = "Temperature")
	private String Temperature;
	@JsonProperty(value = "Humidity")
	private String Humidity;
	@JsonProperty(value = "WeatherDescription")
	private String WeatherDescription;
	@JsonProperty(value = "WindSpeed")
	private String WindSpeed;
	@JsonProperty(value = "WindDirectionDegree")
	private String WindDirectionDegree;
	
	public String getCity() {
		return city;
	}
	public void setCity(String City) {
		this.city = City;
	}
	public String getTemperature() {
		return Temperature;
	}
	public void setTemperature(String Temperature) {
		this.Temperature = Temperature;
	}
	public String getHumidity() {
		return Humidity;
	}
	public void setHumidity(String Humidity) {
		this.Humidity = Humidity;
	}
	public String getWeatherDescription() {
		return WeatherDescription;
	}
	public void setWeatherDescription(String WeatherDescription) {
		this.WeatherDescription = WeatherDescription;
	}
	public String getWindSpeed() {
		return WindSpeed;
	}
	public void setWindSpeed(String WindSpeed) {
		this.WindSpeed = WindSpeed;
	}
	public String getWindDirectionDegree() {
		return WindDirectionDegree;
	}
	public void setWindDirectionDegree(String WindDirectionDegree) {
		this.WindDirectionDegree = WindDirectionDegree;
	}

	@Override
	public String toString() {
		return "CityOfIndia [City=" + city + ", Temperature=" + Temperature + ", Humidity=" + Humidity
				+ ", WeatherDescription=" + WeatherDescription + ", WindSpeed=" + WindSpeed + ", WindDirectionDegree="
				+ WindDirectionDegree + "]";
	}
}
