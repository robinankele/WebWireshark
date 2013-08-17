package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.ip.Ipv4Packet;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Packet;
import org.krakenapps.pcap.decoder.tcp.TcpDecoder;
import org.krakenapps.pcap.decoder.tcp.TcpPacket;
import org.krakenapps.pcap.decoder.tcp.TcpPortProtocolMapper;
import org.krakenapps.pcap.decoder.tcp.TcpSessionImpl;

import at.iaik.websharkgwt.server.kraken.packetconstructor.MyPacketConstructor;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;

public class MyTCPDecoder extends TcpDecoder {
	protected String sessionID;
	protected boolean addToResults;
	
	public MyTCPDecoder(String sessionID,boolean addToResults,TcpPortProtocolMapper protocolMapper) {
		super(protocolMapper);
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void process(Ipv4Packet ipv4Packet) {
		super.process(ipv4Packet);
	}

	@Override
	public void process(Ipv6Packet ipv6Packet) {
		super.process(ipv6Packet);
	}

	@Override
	public void dispatchNewTcpSegment(TcpSessionImpl session, TcpPacket segment) {
		if (addToResults) {
			//System.out.println("tcp PRocessor ");
			PacketStoreSingleton.getInstance().addPacket(sessionID, MyPacketConstructor.construct(null, segment));
		}
		super.dispatchNewTcpSegment(session, segment);
	}
	
	
	
	
	
	
	

}
