package de.obackhaus.testprojects;

import org.json.JSONArray;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class RESTful_Client {
	
	String URI = "";
	Client client = null;
	WebResource webresource = null;
	String responseString ="";
	
	public RESTful_Client(String URI) {
		
		this.client = Client.create();
		this.webresource = client.resource(URI);
	}

	
	public String RESTful_GET_String() {
		
		this.responseString = webresource.get(String.class);
		return responseString;
	}
	
	
	public JSONArray RESTful_GET_JSONArray() {		
		
		JSONArray jsonArray = new JSONArray(this.responseString);
		return jsonArray;
	}
	
	
	
	
	
}
