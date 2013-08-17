package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.ethernet.EthernetDecoder;
import org.krakenapps.pcap.packet.PcapPacket;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;

public class MyEthernetDecoder extends EthernetDecoder {

	protected String sessionID;
	protected boolean addToResults;
	
	public MyEthernetDecoder(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}
	
	@Override
	public void decode(PcapPacket pcapPacket) {
		super.decode(pcapPacket);
		if (addToResults) {
			System.out.println("Ethernet ");
		    //System.out.println(pcapPacket.getPacketHeader());
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null, pcapPacket));
		}
	}

}
