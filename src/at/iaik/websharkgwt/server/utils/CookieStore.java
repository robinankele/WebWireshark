package at.iaik.websharkgwt.server.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class CookieStore {
	private static CookieStore cookieStore;
	private static Vector<MyCookie> cookies;
	
	public static CookieStore getInstance() {
		if (cookieStore == null) {
			cookieStore = new CookieStore();
		}
		return cookieStore;
	}
	
	private CookieStore() {
		cookies = new Vector<MyCookie>();
	}
	
	public void addCookie(MyCookie cookie) {
		cookies.add(cookie);
	}
	
	public List<MyCookie> getCookies() {
		return cookies;
	}
	
	public MyCookie isCookieInStore( String session_id)
	{
		for(MyCookie cookie : cookies)
		{
			if(cookie.getSessionId().equals(session_id))
				return cookie;
		}			
		return null;
	}
	
	public void deleteCookie(String session_id)
	{
		for(MyCookie cookie : cookies)
		{
			if(cookie.getSessionId().equals(session_id))
				cookies.remove(cookie);
		}
	}
}
