package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.dhcp.DhcpDecoder;
import org.krakenapps.pcap.decoder.udp.UdpPacket;

public class MyDHCPDecoder extends DhcpDecoder {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyDHCPDecoder(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void process(UdpPacket arg0) {
		super.process(arg0);
	}

		
}
