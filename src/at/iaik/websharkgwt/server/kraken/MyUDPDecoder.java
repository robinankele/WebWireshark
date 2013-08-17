package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.ip.Ipv4Packet;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Packet;
import org.krakenapps.pcap.decoder.udp.UdpDecoder;
import org.krakenapps.pcap.decoder.udp.UdpProtocolMapper;

public class MyUDPDecoder extends UdpDecoder {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyUDPDecoder(String sessionID,boolean addToResults,UdpProtocolMapper protocolMapper) {
		super(protocolMapper);
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void process(Ipv4Packet packet) {
		super.process(packet);
	}

	@Override
	public void process(Ipv6Packet p) {
		super.process(p);
	}
	
	
	

}
