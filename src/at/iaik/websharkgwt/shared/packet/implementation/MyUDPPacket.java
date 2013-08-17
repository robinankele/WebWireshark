package at.iaik.websharkgwt.shared.packet.implementation;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyUdpPacket;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyUDPPacket extends MyIPv4Packet implements IMyUdpPacket {

	protected long sourcePort = 0;
	protected long destinationPort = 0;
	protected long length = 0;
	protected String checksum = null;
	
	public MyUDPPacket() {
		super("UDP");
	}
	
	public MyUDPPacket(String protocol) {
		super(protocol);
	}

	@Override
	public long getSourcePort() {
		return sourcePort;
	}

	@Override
	public void setSourcePort(long sourcePort) {
		this.sourcePort = sourcePort;
	}

	@Override
	public long getDestinationPort() {
		return destinationPort;
	}

	@Override
	public void setDestinationPort(long destinationPort) {
		this.destinationPort = destinationPort;
	}

	@Override
	public long getLength() {
		return length;
	}

	@Override
	public void setLength(long length) {
		this.length = length;
	}

	@Override
	public String getChecksum() {
		return checksum;
	}

	@Override
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
}
