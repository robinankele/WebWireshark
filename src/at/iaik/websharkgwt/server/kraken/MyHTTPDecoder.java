package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.http.HttpDecoder;
import org.krakenapps.pcap.decoder.ip.Ipv4Packet;
import org.krakenapps.pcap.decoder.udp.UdpPacket;

public class MyHTTPDecoder extends HttpDecoder {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyHTTPDecoder(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
		
		System.out.println("HTTP Decoder");
		
	}
	
}
