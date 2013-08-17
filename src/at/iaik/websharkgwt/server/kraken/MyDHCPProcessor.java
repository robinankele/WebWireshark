package at.iaik.websharkgwt.server.kraken;

//import org.krakenapps.pcap.decoder.dhcp
import org.krakenapps.pcap.decoder.dhcp.DhcpMessage;
import org.krakenapps.pcap.decoder.dhcp.DhcpProcessor;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;
import at.iaik.websharkgwt.shared.packet.implementation.MyPcapPacket;

public class MyDHCPProcessor implements DhcpProcessor {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyDHCPProcessor(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void process(DhcpMessage dhcpPacket) {
		if (addToResults) {
			System.out.println("DHCP PRocessor ");
			PacketStoreSingleton.sendAllowed = false;
			MyPcapPacket udpPacket = PacketStoreSingleton.getInstance().getLastPacket(sessionID);
			System.err.println("balbal ich bins so blšd"+PacketStoreSingleton.getInstance().getPacketList(sessionID).size());
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null,dhcpPacket, udpPacket));
			PacketStoreSingleton.sendAllowed = true;
		}
	}	
}
