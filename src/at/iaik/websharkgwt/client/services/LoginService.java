package at.iaik.websharkgwt.client.services;

import at.iaik.websharkgwt.shared.utils.HttpResponseDebugger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

public class LoginService {
	
	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";
	
	String baseUrl = GWT.getModuleBaseURL();
	String serviceUrl;
	
	
	public static String sessionID;
	
	
	public LoginService(String service_url) {
		serviceUrl = baseUrl + service_url ;
	}
	
	public void sendLoginRequest()
	{
		initConnection();
	}
	public void initConnection()
	{
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(serviceUrl));
		
		builder.setHeader("Greeting", "Hello Server");
		System.out.println("Client: Client Want to Login.");
		try {
			builder.sendRequest(null, new RequestCallback() {
				
//				   
				public void onError(Request request, Throwable exception) {
			    }
//
			    public void onResponseReceived(Request request, Response response) {
			    	
			    	if (200 == response.getStatusCode()) {
			    		String greetingHeaderContent = response.getHeader("Greeting");
			    		
			    		LoginService.sessionID = response.getHeader("JSESSIONID");
			    		//System.out.println("SessionID: " + response.getHeader("Robin"));
			    		//Window.alert("You got a SessionID: " + LoginService.sessionID);
			    		
			    		if (greetingHeaderContent!=null) {
			    			GWT.log("Badhan Yeah! The server has a message for us!!! It is: " + greetingHeaderContent + "wassap");
			    			  			
			    		} else {
			    			GWT.log("Boooooring... no response from the server...");
			    		System.out.println("Client: Login completed");
			    		}
			    		
			    	} else {
			    		Window.alert("Please remove Your Cookie from Browser and Login again");
			    		//Window.alert("Time Out! please Login again");
			    		//Window.Location.reload();
			    	}
			    }       
			});
		} catch (RequestException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

}
