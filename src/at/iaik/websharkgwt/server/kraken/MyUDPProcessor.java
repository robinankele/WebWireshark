package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.udp.UdpPacket;
import org.krakenapps.pcap.decoder.udp.UdpProcessor;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;

public class MyUDPProcessor implements UdpProcessor {

	protected String sessionID;
	protected boolean addToResults;
	
	public MyUDPProcessor(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}
	
	@Override
	public void process(UdpPacket udpPacket) {
		if (addToResults) {
			System.out.println("udp PRocessor ");
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null, udpPacket));
			System.out.println("Add udp Packet to packet store");
		}
	}

}
