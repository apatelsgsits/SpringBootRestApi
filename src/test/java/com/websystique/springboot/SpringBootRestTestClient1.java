package com.websystique.springboot;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.websystique.springboot.model.CityOfIndia;
import com.websystique.springboot.model.User;
 

public class SpringBootRestTestClient1 {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
         
        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
     
    /* GET */
    private static void getUser(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
    }
     
    /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Sarah",51,134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"Tomy",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }
 
    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }
 
 
    /* DELETE */
    private static void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }
    
    private static void getUserUsingRestTemplateAPI(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(REST_SERVICE_URI+"/user/1", HttpMethod.GET, entity, String.class);
		//ResponseEntity<User> result = restTemplate.exchange(REST_SERVICE_URI+"/user/1", HttpMethod.GET, entity, User.class);
        System.out.println(result);
    }
    
	private static void getCityDetailsUsingHttpURLConnectionRestAPI() {
		System.out.println("Get City Details API----------");
		
		try {
			URL url= new URL("http://restapi.demoqa.com/utilities/weather/city/Hyderabad");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void getCityDetailsUsingRestTemplateAPIWithCunstomObjectMapper() {
		final String uri = "http://restapi.demoqa.com/utilities/weather/city/Indore";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);
		
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			// Convert JSON string from file to Object
			// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// CityOfIndia user = mapper.readValue(new ObjectMapper().writeValueAsString(result.getBody()), CityOfIndia.class);
			//CityOfIndia user = mapper.readValue(result.getBody().toString(),CityOfIndia.class);
			System.out.println("result.getBody():- "+result.getBody());
			System.out.println("new ObjectMapper().writeValueAsString(result.getBody()) :-"+new ObjectMapper().writeValueAsString(result.getBody()));
			CityOfIndia user = mapper.readValue(new ObjectMapper().writeValueAsString(result.getBody()),CityOfIndia.class);
			System.out.println(user);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("In Exception :"+e.getMessage());
		}
		

	}
	
	private static void getCityDetailsUsingRestTemplateAPI() {
		final String uri = "http://restapi.demoqa.com/utilities/weather/city/Delhi";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		String string = result.getBody();
		JSONObject jsonObject = (JSONObject) JSONValue.parse(string);
		System.out.println("jsonObject.get(\"Temperature\") :-"+jsonObject.get("Temperature"));

		System.out.println(string);

	}
	
	
	public static String genrateToken(){
	       JsonNode jsonResponse = null;
	        try {
	                     jsonResponse = Unirest.post("https://sso-dev.johndeere.com/oauth2/ausgnh0431ebF8iiL0h7/v1/token")
	                            .field("scope", "cap-dev")
	                            .field("client_id", "0oahq30j99uN0EN8x0h7")
	                            .field("client_secret", "Sjml_gwasCaT5iEfbkL6uhFQgmwnyFdkFRmIfPF6")
	                            .field("grant_type", "client_credentials")
	                            .asJson()
	                            .getBody();
	        } catch (UnirestException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	        }
	        System.out.println("Bearer "+jsonResponse.getObject().get("access_token"));
	        return "Bearer "+jsonResponse.getObject().get("access_token");
	  }
	
	private static void getBookDetailsRestTemplateAPI() {
		final String uri = "http://restapi.demoqa.com/utilities/books/getallbooks";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		String string = result.getBody();
		//JSONObject jsonObject = (JSONObject) JSONValue.parse(string);

		System.out.println(string);

	}

    public static void main(String args[]){
        //listAllUsers();
        //getUser();
        //createUser();
        //listAllUsers();
        //updateUser();
        //listAllUsers();
        //deleteUser();
        //listAllUsers();
        //deleteAllUsers();
        //listAllUsers();
    	 // getUserUsingRestTemplateAPI();
        getCityDetailsUsingHttpURLConnectionRestAPI();
        getCityDetailsUsingRestTemplateAPI();
        getCityDetailsUsingRestTemplateAPIWithCunstomObjectMapper();
        //genrateToken();
        getBookDetailsRestTemplateAPI();
    }
}