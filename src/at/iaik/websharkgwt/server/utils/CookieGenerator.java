package at.iaik.websharkgwt.server.utils;

import java.security.SecureRandom;


public class CookieGenerator {

	public static MyCookie createNewCookie()
	{
		String rand_session_id;
		do{
			 SecureRandom random = new SecureRandom();
			 rand_session_id = new Integer(random.nextInt()).toString();
		 
		}while(CookieStore.getInstance().isCookieInStore( rand_session_id.toString()) != null);
		
		return new MyCookie(rand_session_id);
		
	}
}
