package at.iaik.websharkgwt.shared.packet.implementation;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyIPv4Packet;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyIPv4Packet extends MyEthernetFrame implements IMyIPv4Packet {
	
	protected long version = 0;
	protected long headerLength = 0;
	protected String differentiatedServiceField = null;
	protected long totalLength = 0;
	protected String identification = null;
	protected String flags = null;
	protected long fragmentOffset = 0;
	protected long timeToLive = 0;
	protected String protocolIP = null;
	protected String headerChecksum = null;
	protected String sourceIPAddress = null;
	protected String destinationIPAddress;
	
	public MyIPv4Packet() {
		super("IPv4");
	}
	
	public MyIPv4Packet(String protocol) {
		super(protocol);
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getHeaderLength() {
		return headerLength;
	}

	public void setHeaderLength(long headerLength) {
		this.headerLength = headerLength;
	}

	public String getDifferentiatedServiceField() {
		return differentiatedServiceField;
	}

	public void setDifferentiatedServiceField(String differentiatedServiceField) {
		this.differentiatedServiceField = differentiatedServiceField;
	}

	public long getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(long totalLength) {
		this.totalLength = totalLength;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public long getFragmentOffset() {
		return fragmentOffset;
	}

	public void setFragmentOffset(long fragmentOffset) {
		this.fragmentOffset = fragmentOffset;
	}

	public long getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public String getIPProtocol() {
		return protocol;
	}

	public void setIPProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public String getHeaderChecksum() {
		return headerChecksum;
	}

	public void setHeaderChecksum(String headerChecksum) {
		this.headerChecksum = headerChecksum;
	}


	public String getSourceIPAddress() {
		return sourceIPAddress;
	}


	public void setSourceIPAddress(String sourceIPAddress) {
		this.sourceIPAddress = sourceIPAddress;
	}


	public String getDestinationIPAddress() {
		return destinationIPAddress;
	}

	public void setDestinationIPAddress(String destinationIPAddress) {
		this.destinationIPAddress = destinationIPAddress;
	}

}
