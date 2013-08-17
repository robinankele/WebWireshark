package at.iaik.websharkgwt.shared.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestDebugger {
	
	public static void printRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		//System.out.println(req.getAuthType());
		//System.out.println(req.getCharacterEncoding());
		//System.out.println(req.getContentLength());
		//System.out.println(req.getHeader(null));
		Enumeration headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
		      String headerName = (String)headerNames.nextElement();
		      System.out.println(headerName + "   : " + req.getHeader(headerName));
		    }
		
		System.out.println("Debugger");
		
	}

}
