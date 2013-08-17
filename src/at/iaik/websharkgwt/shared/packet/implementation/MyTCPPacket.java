package at.iaik.websharkgwt.shared.packet.implementation;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyTcpPacket;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyTCPPacket extends MyIPv4Packet implements IMyTcpPacket {
	
	protected long sourcePort = 0;
	protected long destinationPort = 0;
	protected long sequenceNumber = 0;
	protected long acknowlegmentNumber = 0;
	//protected long headerLength;
	//protected String flags;
	protected long windowSizeValue = 0;
	protected String checksum = null;
	protected String options;
	
	public MyTCPPacket() {
		super("TCP");
	}
	
	public MyTCPPacket(String protocol) {
		super(protocol);
	}
	
	public long getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(long sourcePort) {
		this.sourcePort = sourcePort;
	}

	public long getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(long destinationPort) {
		this.destinationPort = destinationPort;
	}

	public long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public long getAcknowlegmentNumber() {
		return acknowlegmentNumber;
	}

	public void setAcknowlegmentNumber(long acknowlegmentNumber) {
		this.acknowlegmentNumber = acknowlegmentNumber;
	}

	@Override
	public long getHeaderLength() {
		return headerLength;
	}

	@Override
	public void setHeaderLength(long headerLength) {
		this.headerLength = headerLength;
	}

	@Override
	public String getFlags() {
		return flags;
	}

	@Override
	public void setFlags(String flags) {
		this.flags = flags;
	}

	public long getWindowSizeValue() {
		return windowSizeValue;
	}

	public void setWindowSizeValue(long windowSizeValue) {
		this.windowSizeValue = windowSizeValue;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	
	public String getOptions(){
		return options;
	}
	
	public void setOptions(String options){
		this.options = options;
	}
}
