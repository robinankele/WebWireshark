package at.iaik.websharkgwt.shared.packet.interfaces;

import java.util.Vector;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

public interface IMyDhcpPacket extends IMyUdpPacket{
	public long getMessageType();
	public void setMessageType(long messageType);
	public String getHardwareType();
	public void setHardwareType(String hardwareType);
	public long getHardwareAddressLength();
	public void setHardwareAddressLength(long hardwareAddressLength);
	public long getHops();
	public void setHops(long hops);
	public String getTransactionID();
	public void setTransactionID(String transactionID);
	public long getSecondsElapsed();
	public void setSecondsElapsed(long secondsElapsed);
	public String getBootpFlags();
	public void setBootpFlags(String bootpFlags);
	public String getClientIPAddress();
	public void setClientIPAddress(String clientIPAddress);
	public String getYourClientIPAddress();
	public void setYourClientIPAddress(String yourClientIPAddress);
	public String getNextServerIPAddress();
	public void setNextServerIPAddress(String nextServerIPAddress);
	public String getRelayAgentIPAddress();
	public void setRelayAgentIPAddress(String relayAgentIPAddress);
	public String getClientMacAddress();
	public void setClientMacAddress(String clientMacAddress);
	public long getClientHardwareAddressPadding();
	public void setClientHardwareAddressPadding(long clientHardwareAddressPadding);
	public String getServerHostname();
	public void setServerHostname(String serverHostname);
	public String getBootFilename();
	public void setBootFilename(String bootFilename);
	public String getMagicCookie();
	public void setMagicCookie(String magicCookie);
	/*public Vector<String> getOptions();
	public void setOptions(Vector<String> options);*/
}
