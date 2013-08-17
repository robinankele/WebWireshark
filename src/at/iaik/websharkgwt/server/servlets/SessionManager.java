package at.iaik.websharkgwt.server.servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ibm.icu.util.Calendar;

import at.iaik.websharkgwt.server.utils.CookieStore;
import at.iaik.websharkgwt.server.utils.MyCookie;

public class SessionManager {

	public static void sessionControl(HttpServletRequest req)
	{
		
	}


	public static boolean isSessionIdValid(String sessionId) {
		// TODO Auto-generated method stub
		System.out.println("Session_id: " + sessionId);
		MyCookie cookie = CookieStore.getInstance().isCookieInStore(sessionId);
		if(cookie == null)
		{
			System.out.println("SessionId not found in Store");
			return false;
		}
		String time_stamp = cookie.getTimeStamp();
		Long current_time = Calendar.getInstance().getTimeInMillis();
		if(Long.valueOf(time_stamp) + 10000 < current_time) // 10 sec
		{
			System.out.println("[INFO] Session is invalid due to Old timestamp. CurrentTime: " + current_time + " Cookie_Time_stamp: " + time_stamp);
			return false;
		}
		System.out.println("[INFO] ValidSession: CurrentTime: " + current_time + " Cookie_Time_stamp: " + time_stamp);
		return true;
	}
	
	

	public static String getSessionIdFormCookie(Cookie[] cookies)
	{
		String sessionId = null;
	    if (cookies != null) {
	    	for (int i = 0; i < cookies.length; i++) {
	        if (cookies[i].getName().equals("JSESSIONID")) {
	        	 sessionId = cookies[i].getValue();
	             return sessionId;
	        }

	    	}
	    }
		return null;
	}


	public static void cleanOldCookieOfClienBrowser(HttpServletRequest req) {
		// TODO Auto-generated method stub
		if(SessionManager.getSessionIdFormCookie(req.getCookies()) != null)
		{
			//nothing happens in this moment
		}
	}
}

