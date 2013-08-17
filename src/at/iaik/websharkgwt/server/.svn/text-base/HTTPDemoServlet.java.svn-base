package at.iaik.websharkgwt.server;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HTTPDemoServlet extends HttpServlet {
	
	protected List<String> nonStaticStore = new Vector<String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
		System.out.println("Hey we got some HONEY TITS request.");
		String greetingFromClient = req.getHeader("Greeting");
		if (greetingFromClient != null) {
		
			//the client has something for us...
			//let's keep it in a static store (in reality we would store that in a database, but for this example we use singletons for storing data (hint: session management!)
			//the store is based on a static variable, thus the stored String will also be available for other Servlets.
			
			//just reload the page multiple times, you will see that the old greetings will remain in the store
			StaticStore.getInstance().addGreeting(greetingFromClient);
			List<String> storedGreetings = StaticStore.getInstance().getGreetings();
			System.out.println("STATIC STORE");
			for (String storedGreeting : storedGreetings) {
				//put them to the server console...
				System.out.println(storedGreeting);
			}
			
			//since the client was so nice to us, we will send a message!
			resp.setHeader("Greeting", "Hello client!");
			
			//we set the status of the response to HTTP STATUS CODE 200 (indicating OK). This is done automatically and in this case is just shown for a better understanding...
			//check out http://www.w3.org/Protocols/HTTP/HTRESP.html for status codes...
			resp.setStatus(200); //
		
			//that's it, the response will be sent by the code that is implemented somewhere higher up in HTTPServlet
		} else {
			//the client was not nice, so we will send the HTTP STATUS CODE 501 (not implemented...)
			//check out http://www.w3.org/Protocols/HTTP/HTRESP.html for status codes
			resp.setStatus(501);
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		//we could also implement code for other requests such as post, put, delete etc. 
		//If these requests are not needed, the methods do not need to be overwritten
		//In this case, this is done to show you how other requests could be implemented
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
		//we could also implement code for other requests such as post, put, delete etc. 
		//If these requests are not needed, the methods do not need to be overwritten
		//In this case, this is done to show you how other requests could be implemented
	}
	
	

}
