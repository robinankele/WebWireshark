package at.iaik.websharkgwt.shared.packet.interfaces;

/**
 * @author Robin Ankele
 * @email robin.ankele@student.tugraz.at
 */

public interface IMyEthernetFrame extends IMyPcapPacket{
	public long getType();
	public void setType(long type);
	public String getArrivalTime();
	public void setArrivalTime(String arrivalTime);
	public double getEpochTime();
	public void setEpochTime(double epochTime);
	public long getFrameNumber();
	public void setFrameNumber(long frameNumber);
	public long getFrameLength();
	public void setFrameLength(long frameLenght);
	public long getCaptureLength();
	public void setCaptureLength(long captureLength);
	public String getDestinationAddress();
	public void setDestinationAddress(String destinationAddress);
	public String getSourceAddress();
	public void setSourceAddress(String sourceAddress);
}
