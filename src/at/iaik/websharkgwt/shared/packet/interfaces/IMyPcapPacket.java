package at.iaik.websharkgwt.shared.packet.interfaces;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

public interface IMyPcapPacket {	
	public int getNumber();
	public void setNumber(int number);
	public String getProtocol();
	public void setProtocol(String protocol);
	public String getTime();
	public void setTime(String time);
	public int getLengthOrig();
	public void setLengthOrig(int length);

}
