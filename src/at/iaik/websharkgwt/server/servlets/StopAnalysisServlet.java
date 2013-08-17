package at.iaik.websharkgwt.server.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.iaik.websharkgwt.server.utils.CookieGenerator;
import at.iaik.websharkgwt.server.utils.CookieStore;
import at.iaik.websharkgwt.server.utils.MyCookie;
import at.iaik.websharkgwt.server.webserverstorage.TaskControllerSingleton;
import at.iaik.websharkgwt.shared.utils.HttpRequestDebugger;

@SuppressWarnings("serial")
public class StopAnalysisServlet extends HttpServlet {
	
	protected List<String> nonStaticStore = new Vector<String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		TaskControllerSingleton taskStore = TaskControllerSingleton.getInstance();
		taskStore.stopTask(SessionManager.getSessionIdFormCookie(req.getCookies()));
		resp.setStatus(200); 
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
