package at.iaik.websharkgwt.shared.packet.implementation;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyEthernetFrame;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyEthernetFrame extends MyPcapPacket implements IMyEthernetFrame {
	protected String arrivalTime = null;
	protected double epochTime = 0;
	protected long frameNumber = 0;
	protected long frameLength = 0;
	protected long captureLength = 0;
	protected String sourceAddress = null;
	protected String destinationAddress = null;
	protected long type = 0;
	
	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public MyEthernetFrame() {
		super("EthernetFrame");
	}
	
	public MyEthernetFrame(String protocol) {
		super(protocol);
	}

	@Override
	public String getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public double getEpochTime() {
		return epochTime;
	}

	@Override
	public void setEpochTime(double epochTime) {
		this.epochTime = epochTime;
	}

	@Override
	public long getFrameNumber() {
		return frameNumber;
	}

	@Override
	public void setFrameNumber(long frameNumber) {
		this.frameNumber = frameNumber;
	}

	@Override
	public long getFrameLength() {
		return frameLength;
	}

	@Override
	public void setFrameLength(long frameLenght) {
		this.frameLength = frameLenght;
	}

	@Override
	public long getCaptureLength() {
		return captureLength;
	}

	@Override
	public void setCaptureLength(long captureLength) {
		this.captureLength = captureLength;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

}
