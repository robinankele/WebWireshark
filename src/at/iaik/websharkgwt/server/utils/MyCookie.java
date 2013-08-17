package at.iaik.websharkgwt.server.utils;

import com.ibm.icu.util.Calendar;

public class MyCookie {

	private String session_id_;
	private String comment_;
	private String max_age_ = new Integer(10).toString();
	private String time_stamp_;
	
	public MyCookie(String session_id) {
		// TODO Auto-generated constructor stub
		session_id_ = session_id;
		Calendar local_cal = Calendar.getInstance();
		time_stamp_ = String.valueOf(local_cal.getTimeInMillis());
	}
	
	public String getComment_() {
		return comment_;
	}
	public String getMaxAge() {
		return max_age_;
	}
	public String getSessionId() {
		return session_id_;
	}

	public String getCookieString() {
		// TODO Auto-generated method stub
		return new String("JSESSIONID = " + session_id_);
	}
	
	public String getTimeStamp() {
		return time_stamp_;
	}
}
