package at.iaik.websharkgwt.shared.packet.interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

public interface IMyHttpPacket extends IMyTcpPacket{
	public String getRequestMethod();
	public void setRequestMethod(String requestMethod);
	public String getRequestURL();
	public void setRequestURL(String requestURL);
	public String getRequestVersion();
	public void setRequestVersion(String requestVersion);
	/*public String getHost();
	public void setHost(String host);
	public String getUseragent();
	public void setUseragent(String useragent);
	public String getAccept();
	public void setAccept(String accept);
	public String getAcceptlanguage();
	public void setAcceptlanguage(String acceptlanguage);
	public String getAcceptencoding();
	public void setAcceptencoding(String acceptencoding);
	public String getConnection();
	public void setConnection(String connection);
	public String getReferer();
	public void setReferer(String referer);
	public String getCookie();
	public void setCookie(String cookie);*/
	public long getStatusCode();
	public void setStatusCode(long statusCode);
	/*public String getResponsePhrase();
	public void setResponsePhrase(String responsePhrase);
	public String getDate();
	public void setDate(String date);
	public String getServer();
	public void setServer(String server);
	public String getLastmodified();
	public void setLastmodified(String lastmodified);
	public String getAcceptranges();
	public void setAcceptranges(String acceptranges);
	public long getContentLength();
	public void setContentLength(long contentLength);
	public String getCachecontrol();
	public void setCachecontrol(String cachecontrol);
	public String getExpires();
	public void setExpires(String expires);
	public String getKeepalive();
	public void setKeepalive(String keepalive);
	public String getContentType();
	public void setContentType(String contentType);*/
	public Map<String, String> getHeader();
	public void setHeader(Map<String, String> header);
}
