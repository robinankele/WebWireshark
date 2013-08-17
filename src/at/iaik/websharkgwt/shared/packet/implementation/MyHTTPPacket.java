package at.iaik.websharkgwt.shared.packet.implementation;


import java.util.HashMap;
import java.util.Map;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyHttpPacket;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyHTTPPacket extends MyTCPPacket implements IMyHttpPacket {
	
	//request
	protected String requestMethod = null;
	protected String requestURL = null;
	protected String requestVersion = null;
	/*protected String host = null;
	protected String useragent = null;
	protected String accept = null;
	protected String acceptlanguage = null;
	protected String acceptencoding = null;
	protected String connection = null;
	protected String referer = null;
	protected String cookie = null;*/
	
	//response
	protected long statusCode = 0;
	/*protected String responsePhrase = null;
	protected String date = null;
	protected String server = null;
	protected String lastmodified = null;
	protected String acceptranges = null;
	protected long contentLength = 0;
	protected String cachecontrol = null;
	protected String expires = null;
	protected String keepalive = null;
	protected String contentType = null;*/
	
	protected Map<String, String> header = new HashMap<String, String>();

	
	public MyHTTPPacket(MyTCPPacket tcpPacket) {
		super.number = tcpPacket.number;
		super.protocol = "HTTP";
		super.time = tcpPacket.time;
		super.lengthOrig = tcpPacket.lengthOrig;
		super.arrivalTime = tcpPacket.arrivalTime;
		super.epochTime = tcpPacket.epochTime;
		super.frameNumber = tcpPacket.frameNumber;
		super.frameLength = tcpPacket.frameLength;
		super.captureLength = tcpPacket.captureLength;
		super.sourceAddress = tcpPacket.sourceAddress;
		super.destinationAddress = tcpPacket.destinationAddress;
		super.type = tcpPacket.type;
		super.version = tcpPacket.version;
		super.headerLength = tcpPacket.headerLength;
		super.differentiatedServiceField = tcpPacket.differentiatedServiceField;
		super.totalLength = tcpPacket.totalLength;
		super.identification = tcpPacket.identification;
		super.flags = tcpPacket.flags;
		super.fragmentOffset = tcpPacket.fragmentOffset;
		super.timeToLive = tcpPacket.timeToLive;
		super.protocolIP = tcpPacket.protocolIP;
		super.headerChecksum = tcpPacket.headerChecksum;
		super.sourceIPAddress = tcpPacket.sourceIPAddress;
		super.destinationIPAddress = tcpPacket.destinationIPAddress;
		super.sourcePort = tcpPacket.sourcePort;
		super.destinationPort = tcpPacket.destinationPort;
		super.sequenceNumber = tcpPacket.sequenceNumber;
		super.acknowlegmentNumber = tcpPacket.acknowlegmentNumber;
		super.windowSizeValue = tcpPacket.windowSizeValue;
		super.checksum = tcpPacket.checksum;
		super.options = tcpPacket.options;
	}
	
	public MyHTTPPacket() {
		super("HTTP");
	}

	public MyHTTPPacket(String protocol) {
		super(protocol);
	}
	
	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getRequestVersion() {
		return requestVersion;
	}

	public void setRequestVersion(String requestVersion) {
		this.requestVersion = requestVersion;
	}

	/*public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getAcceptlanguage() {
		return acceptlanguage;
	}

	public void setAcceptlanguage(String acceptlanguage) {
		this.acceptlanguage = acceptlanguage;
	}

	public String getAcceptencoding() {
		return acceptencoding;
	}

	public void setAcceptencoding(String acceptencoding) {
		this.acceptencoding = acceptencoding;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}*/

	public long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(long statusCode) {
		this.statusCode = statusCode;
	}

	/*public String getResponsePhrase() {
		return responsePhrase;
	}

	public void setResponsePhrase(String responsePhrase) {
		this.responsePhrase = responsePhrase;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getAcceptranges() {
		return acceptranges;
	}

	public void setAcceptranges(String acceptranges) {
		this.acceptranges = acceptranges;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getCachecontrol() {
		return cachecontrol;
	}

	public void setCachecontrol(String cachecontrol) {
		this.cachecontrol = cachecontrol;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getKeepalive() {
		return keepalive;
	}

	public void setKeepalive(String keepalive) {
		this.keepalive = keepalive;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}*/

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}
}
