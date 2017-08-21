package de.obackhaus.testprojects;



import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class main {

	public static void main(String[] args) {
		
		Client client = Client.create();
		WebResource webresource = client.resource("https://www.openligadb.de/api/getmatchdata/bl2/2017/3");
		String responseString = webresource.get(String.class);
				
//		System.out.println("XML :  " + webresource.type(MediaType.TEXT_PLAIN).get(String.class));
//		System.out.println("XML :  " + webresource.type(MediaType.TEXT_XML).get(String.class));
//		System.out.println("HTML :  " + webresource.type(MediaType.TEXT_HTML).get(String.class));
//		
//		String xml_response = webresource.type(MediaType.TEXT_XML).get(String.class);
//		
//		int last_position = 0;
//		for(int i=0; i < xml_response.length(); i++) {
//			
//			char current_letter = xml_response.charAt(i);
//			if(current_letter == '>' && xml_response.length() != i) {
//				System.out.println(xml_response.substring(last_position, i+1));
//				last_position = i+1;				
//			}
//			
//		}
		
		System.out.println("Start of the Webservice query (German 1.league football results, first matchday)\n");
		JSONArray jsonArray = new JSONArray(responseString);			
		System.out.println("Number of recieved JSON Objects (Games) :" + jsonArray.length() + "\n\n");
				
	    for (Object o : jsonArray) {
	        JSONObject jsonLineItem = (JSONObject) o;
	        //System.out.println(jsonLineItem.length());
	        //System.out.println(jsonLineItem.names());
	        
	        Boolean matchFinished = jsonLineItem.getBoolean("MatchIsFinished");
	        System.out.println("Game completed: " + matchFinished );
	        
	        
	        	
		        JSONObject team1Name = jsonLineItem.getJSONObject("Team1");
		        //System.out.println(team1Name.length());
		        
		        JSONObject team2Name = jsonLineItem.getJSONObject("Team2");
		        //System.out.println(team2Name.length());
		        
		    if(matchFinished != false) {    
		        JSONArray result = jsonLineItem.getJSONArray("MatchResults");
		        //System.out.println(result.length());
	        
	        	JSONObject resultsSecondHalf = result.getJSONObject(1);	        
	        
	        	System.out.println("Teams : " + team1Name.getString("TeamName") + " vs. " + team2Name.getString("TeamName"));
	        	System.out.println("Result : " + resultsSecondHalf.getInt("PointsTeam1") + " : " + resultsSecondHalf.getInt("PointsTeam2") + "\n");
	        } else {
	        	System.out.println("Teams : " + team1Name.getString("TeamName") + " vs. " + team2Name.getString("TeamName"));
	        	System.out.println("Result : " + " - " + " : " + " - " + "\n");
	        }
	    }
				
		System.out.println("\nEnd of Webservice query!");
	}

}
