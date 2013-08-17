package at.iaik.websharkgwt.shared.packet.implementation;



import java.util.Vector;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyDhcpPacket;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyDHCPPacket extends MyUDPPacket implements IMyDhcpPacket {
	
	protected long messageType = 0;
	protected String hardwareType = null;
	protected long hardwareAddressLength = 0;
	protected long hops = 0;
	protected String transactionID = null;
	protected long secondsElapsed = 0;
	protected String bootpFlags = null;
	protected String clientIPAddress = null;
	protected String yourClientIPAddress = null;
	protected String nextServerIPAddress = null;
	protected String relayAgentIPAddress = null;
	protected String clientMacAddress = null;
	protected long clientHardwareAddressPadding = 0;
	protected String serverHostname = null;
	protected String bootFilename = null;
	protected String magicCookie = null;
	//protected Vector<String> options = new Vector<String>();
	
	public MyDHCPPacket(MyUDPPacket udpPacket) {
		super.arrivalTime = udpPacket.getArrivalTime();
		super.captureLength = udpPacket.getCaptureLength();
		super.checksum = udpPacket.getChecksum();
		super.destinationAddress = udpPacket.getDestinationAddress();
		super.destinationIPAddress = udpPacket.getDestinationIPAddress();
		super.destinationPort = udpPacket.getDestinationPort();
		super.differentiatedServiceField = udpPacket.getDifferentiatedServiceField();
		super.epochTime = udpPacket.getEpochTime();
		super.flags = udpPacket.getFlags();
		super.fragmentOffset = udpPacket.getFragmentOffset();
		super.frameLength = udpPacket.getFrameLength();
		super.frameNumber = udpPacket.getFrameNumber();
		super.headerChecksum = udpPacket.getHeaderChecksum();
		super.headerLength = udpPacket.getHeaderLength();
		super.identification = udpPacket.getIdentification();
		super.length = udpPacket.getLength();
		super.number = udpPacket.getNumber();
		super.protocol = "DHCP";
		super.protocolIP = udpPacket.getIPProtocol();
		super.sourceAddress = udpPacket.getSourceAddress();
		super.sourceIPAddress = udpPacket.getSourceIPAddress();
		super.sourcePort = udpPacket.getSourcePort();
		super.time = udpPacket.getTime();
		super.timeToLive = udpPacket.getTimeToLive();
		super.totalLength = udpPacket.getTotalLength();
		super.type = udpPacket.getType();
		super.version = udpPacket.getVersion();
	}
	
	public MyDHCPPacket() {
		super("DHCP");
	}
	
	public MyDHCPPacket(String protocol) {
		super(protocol);
	}


	public long getMessageType() {
		return messageType;
	}


	public void setMessageType(long messageType) {
		this.messageType = messageType;
	}


	public String getHardwareType() {
		return hardwareType;
	}


	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}


	public long getHardwareAddressLength() {
		return hardwareAddressLength;
	}


	public void setHardwareAddressLength(long hardwareAddressLength) {
		this.hardwareAddressLength = hardwareAddressLength;
	}


	public long getHops() {
		return hops;
	}


	public void setHops(long hops) {
		this.hops = hops;
	}


	public String getTransactionID() {
		return transactionID;
	}

	
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}


	public long getSecondsElapsed() {
		return secondsElapsed;
	}


	public void setSecondsElapsed(long secondsElapsed) {
		this.secondsElapsed = secondsElapsed;
	}


	public String getBootpFlags() {
		return bootpFlags;
	}


	public void setBootpFlags(String bootpFlags) {
		this.bootpFlags = bootpFlags;
	}


	public String getClientIPAddress() {
		return clientIPAddress;
	}


	public void setClientIPAddress(String clientIPAddress) {
		this.clientIPAddress = clientIPAddress;
	}


	public String getYourClientIPAddress() {
		return yourClientIPAddress;
	}


	public void setYourClientIPAddress(String yourClientIPAddress) {
		this.yourClientIPAddress = yourClientIPAddress;
	}

	public String getNextServerIPAddress() {
		return nextServerIPAddress;
	}


	public void setNextServerIPAddress(String nextServerIPAddress) {
		this.nextServerIPAddress = nextServerIPAddress;
	}


	public String getRelayAgentIPAddress() {
		return relayAgentIPAddress;
	}


	public void setRelayAgentIPAddress(String relayAgentIPAddress) {
		this.relayAgentIPAddress = relayAgentIPAddress;
	}


	public String getClientMacAddress() {
		return clientMacAddress;
	}


	public void setClientMacAddress(String clientMacAddress) {
		this.clientMacAddress = clientMacAddress;
	}


	public long getClientHardwareAddressPadding() {
		return clientHardwareAddressPadding;
	}


	public void setClientHardwareAddressPadding(
			long clientHardwareAddressPadding) {
		this.clientHardwareAddressPadding = clientHardwareAddressPadding;
	}


	public String getServerHostname() {
		return serverHostname;
	}


	public void setServerHostname(String serverHostname) {
		this.serverHostname = serverHostname;
	}


	public String getBootFilename() {
		return bootFilename;
	}


	public void setBootFilename(String bootFilename) {
		this.bootFilename = bootFilename;
	}


	public String getMagicCookie() {
		return magicCookie;
	}


	public void setMagicCookie(String magicCookie) {
		this.magicCookie = magicCookie;
	}


/*	public Vector<String> getOptions() {
		return options;
	}


	public void setOptions(Vector<String> options) {
		this.options = options;
	}*/
}
