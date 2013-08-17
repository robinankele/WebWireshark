package at.iaik.websharkgwt.server.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import at.iaik.websharkgwt.server.webserverstorage.PacketStore;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;
import at.iaik.websharkgwt.server.webserverstorage.TaskControllerSingleton;


@SuppressWarnings("serial")
public class StartAnalysisServlet extends HttpServlet {
	
	protected List<String> nonStaticStore = new Vector<String>();
	int msDelayBetweenPacket;
	int queueSize;
	protected Gson gson;

	public void init() throws ServletException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);	//THIS is needed to serialize LONG variables in a way that GWT understands.. (as string)
		gson = gsonBuilder.create();
		super.init();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.msDelayBetweenPacket = Integer.parseInt(req.getHeader("delayTime"));
		this.queueSize = Integer.parseInt(req.getHeader("queueSize"));
		String sessionID = SessionManager.getSessionIdFormCookie(req.getCookies());
		if(SessionManager.isSessionIdValid(sessionID) == false)
		{
			resp.setStatus(403); // HTTP session invalid code / 403 FORBIDDEN
			System.out.println("###server: invalid session");
			return;
		}
		
		
		System.out.println("###Server: Analysis Starting for SessionID: " + sessionID);

		PacketStore packetStore = PacketStoreSingleton.getInstance();
		packetStore.setQueueSize(queueSize);
		packetStore.clearMyPackeOfSessionID(sessionID);
		
		TaskControllerSingleton taskStore = TaskControllerSingleton.getInstance();
		if (taskStore.isTaskRunning(sessionID)) {
			
		} else {
			PCAPAnalysisThread pcapAnalysisThread = new PCAPAnalysisThread(sessionID, msDelayBetweenPacket);
			taskStore.runTask(sessionID,pcapAnalysisThread);
			resp.setStatus(200);
		}

		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
		//we could also implement code for other requests such as post, put, delete etc. 
		//If these requests are not needed, the methods do not need to be overwritten
		//In this case, this is done to show you how other requests could be implemented
	}
	
	

}
