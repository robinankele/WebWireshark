package at.iaik.websharkgwt.shared.packet.interfaces;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

public interface IMyIPv4Packet extends IMyEthernetFrame{
	public long getVersion();
	public void setVersion(long version);
	public long getHeaderLength();
	public void setHeaderLength(long headerLength);
	public String getDifferentiatedServiceField();
	public void setDifferentiatedServiceField(String differentiatedServiceField);
	public long getTotalLength();
	public void setTotalLength(long totalLength);
	public String getIdentification();
	public void setIdentification(String identification);
	public String getFlags();
	public void setFlags(String flags);
	public long getFragmentOffset();
	public void setFragmentOffset(long fragmentOffset);
	public long getTimeToLive();
	public void setTimeToLive(long timeToLive);
	public String getIPProtocol();
	public void setIPProtocol(String protocol);
	public String getHeaderChecksum();
	public void setHeaderChecksum(String headerChecksum);
	public String getSourceIPAddress();
	public void setSourceIPAddress(String sourceIPAddress);
	public String getDestinationIPAddress();
	public void setDestinationIPAddress(String destinationIPAddress);
}
