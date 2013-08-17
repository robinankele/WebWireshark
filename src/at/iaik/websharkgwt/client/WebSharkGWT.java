package at.iaik.websharkgwt.client;

import at.iaik.websharkgwt.client.gui.LoginPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;


public class WebSharkGWT implements EntryPoint {
		
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	public void onModuleLoad() {

		RootPanel.get().add(new LoginPage());
		//RootPanel.ge
		//HTMLPanel htmlP = new HTMLPanel("<ul><li>Erster Link</li><li>Zweiter Link</li></ul>");
		//RootPanel.get("nav").add(htmlP);

	}
	
}
