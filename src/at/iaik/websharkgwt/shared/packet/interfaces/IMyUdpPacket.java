package at.iaik.websharkgwt.shared.packet.interfaces;

import at.iaik.websharkgwt.shared.packet.implementation.MyUDPPacket;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

public interface IMyUdpPacket extends IMyIPv4Packet{
	public long getSourcePort();
	public void setSourcePort(long sourcePort);
	public long getDestinationPort();
	public void setDestinationPort(long destinationPort);
	public long getLength();
	public void setLength(long length);
	public String getChecksum();
	public void setChecksum(String checksum);
}
