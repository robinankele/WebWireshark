package at.iaik.websharkgwt.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

public class StopAnalysisService {
	
	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";
	
	String baseUrl = GWT.getModuleBaseURL();
	String serviceUrl;
	public StopAnalysisService(String service_url) {
		serviceUrl = baseUrl + service_url ;
	}
	
	public void stopAnalyse()
	{
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(serviceUrl));
		builder.setHeader("Analyse", "Stop");
		
		try {
			builder.sendRequest(null, new RequestCallback() {

				public void onError(Request request, Throwable exception) {
			    }
//
			    public void onResponseReceived(Request request, Response response) {
			    	
			    	if (200 == response.getStatusCode()) {
			    		System.out.println("Analyse stoped");
			    		
			    	} else {
			    		System.out.println("Any error occured");
			    	}
			    }       
			});
		} catch (RequestException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		
		
		System.out.println(baseUrl);
	}

}
