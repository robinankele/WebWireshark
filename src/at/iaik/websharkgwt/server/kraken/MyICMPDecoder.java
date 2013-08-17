package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.icmp.IcmpDecoder;
import org.krakenapps.pcap.decoder.ip.Ipv4Packet;

public class MyICMPDecoder extends IcmpDecoder {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyICMPDecoder(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void process(Ipv4Packet arg0) {
		super.process(arg0);
	}

		
}
