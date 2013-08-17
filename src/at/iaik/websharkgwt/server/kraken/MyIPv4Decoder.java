package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.ethernet.EthernetFrame;
import org.krakenapps.pcap.decoder.ip.IpDecoder;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;

public class MyIPv4Decoder extends IpDecoder {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyIPv4Decoder(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void process(EthernetFrame ethernetFrame) {
		super.process(ethernetFrame);
		if (addToResults) {
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null, ethernetFrame));
		}
	}
	
	
}
