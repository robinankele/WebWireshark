package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.http.HttpProcessor;
import org.krakenapps.pcap.decoder.http.HttpRequest;
import org.krakenapps.pcap.decoder.http.HttpResponse;
import org.krakenapps.pcap.util.Buffer;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;
import at.iaik.websharkgwt.shared.packet.implementation.MyPcapPacket;

public class MyHTTPProcessor implements HttpProcessor {

	protected String sessionID;
	protected boolean addToResults;
	
	public MyHTTPProcessor(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
		System.out.println("HTTP Processor");
		
	}

	@Override
	public void onMultipartData(Buffer arg0) {
		System.out.println("HTTP on MultipartData");
	}

	@Override
	public void onRequest(HttpRequest arg0) {
		if (addToResults) {
			System.out.println("HTTP Processor Request");
			PacketStoreSingleton.sendAllowed = false;
			MyPcapPacket tcpPacket = PacketStoreSingleton.getInstance().getLastPacket(sessionID);
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null, arg0, null, tcpPacket, sessionID));
			PacketStoreSingleton.sendAllowed = true;
		}
	}

	@Override
	public void onResponse(HttpRequest arg0, HttpResponse arg1) {
		if (addToResults) {
			System.out.println("HTTP Processor Response");
			PacketStoreSingleton.sendAllowed = false;
			MyPcapPacket tcpPacket = PacketStoreSingleton.getInstance().getLastPacket(sessionID);
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null, arg0, arg1, tcpPacket, sessionID));
			PacketStoreSingleton.sendAllowed = true;
		}
	}
	
	

}
