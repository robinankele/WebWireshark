package at.iaik.websharkgwt.shared.packet.interfaces;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

public interface IMyTcpPacket extends IMyIPv4Packet{
	public long getSourcePort();
	public void setSourcePort(long sourcePort);
	public long getDestinationPort();
	public void setDestinationPort(long destinationPort);
	public long getSequenceNumber();
	public void setSequenceNumber(long sequenceNumber);
	public long getAcknowlegmentNumber();
	public void setAcknowlegmentNumber(long acknowlegmentNumber);
	public long getHeaderLength();
	public void setHeaderLength(long headerLength);
	public String getFlags();
	public void setFlags(String flags);
	public long getWindowSizeValue();
	public void setWindowSizeValue(long windowSizeValue);
	public String getChecksum();
	public void setChecksum(String checksum);
	public String getOptions();
	public void setOptions(String options);
}
