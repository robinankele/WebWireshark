package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.tcp.TcpPacket;
import org.krakenapps.pcap.decoder.tcp.TcpSegment;
import org.krakenapps.pcap.decoder.tcp.TcpSegmentCallback;
import org.krakenapps.pcap.decoder.tcp.TcpSession;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;

public class MyTCPSegmentCallback implements TcpSegmentCallback {

	protected String sessionID;
	protected boolean addToResults;
	
	public MyTCPSegmentCallback(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}
	
	@Override
	public void onReceive(TcpSession session, TcpSegment tcpSegment) {
		TcpPacket tcpPacket = (TcpPacket)tcpSegment;
		if (addToResults) {
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null, tcpPacket));
		}
		
	}
	
	

	

}
