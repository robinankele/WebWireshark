package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.icmp.IcmpPacket;
import org.krakenapps.pcap.decoder.icmp.IcmpProcessor;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;

public class MyICMPProcessor implements IcmpProcessor {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyICMPProcessor(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void process(IcmpPacket icmpPacket) {
		if (addToResults) {
			System.out.println("icmp PRocessor ");
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null,icmpPacket));
		}
		
	}

	
		
}
