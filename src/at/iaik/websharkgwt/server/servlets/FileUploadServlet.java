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
import at.iaik.websharkgwt.shared.utils.HttpRequestDebugger;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;


import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import at.iaik.websharkgwt.server.utils.Utils;
import at.iaik.websharkgwt.server.webserverstorage.PCAPFileStorageSingleton;
import java.util.HashMap;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {
	
	protected List<String> nonStaticStore = new Vector<String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sessionID = SessionManager.getSessionIdFormCookie(req.getCookies());
		if(SessionManager.isSessionIdValid(sessionID) == false)
		{
			System.out.println("^^^^^^^^^^^");
			resp.setContentType("text/html");
			resp.setStatus(403); // HTTP session invalid code / 403 FORBIDDEN
			resp.getWriter().printf("403");
			System.out.println("###server: invalid session");
			return;
		}
		System.out.println("###Server FileUploadServlet: SessionID is: " + sessionID);
		try {
			
			ServletFileUpload upload = new ServletFileUpload();
			
			FileItemIterator iterator = upload.getItemIterator(req);
			FileItemStream item = iterator.next();
			InputStream pcapFileInputStream = item.openStream();
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			Utils.copyStream(pcapFileInputStream, bOut);
			pcapFileInputStream.close();
			bOut.close();
		    PCAPFileStorageSingleton pcapFileStorageSingleton =  PCAPFileStorageSingleton.getInstance();
		    pcapFileStorageSingleton.clearPacketStore(sessionID);
	 	    pcapFileStorageSingleton.addFile(sessionID, bOut.toByteArray());
	 	    
	 	    resp.setStatus(200);
	 	   
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
		//we could also implement code for other requests such as post, put, delete etc. 
		//If these requests are not needed, the methods do not need to be overwritten
		//In this case, this is done to show you how other requests could be implemented
	}
	
	

}
