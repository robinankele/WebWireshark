package at.iaik.websharkgwt.shared.packet.implementation;

import java.io.Serializable;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyPcapPacket;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyPcapPacket implements Serializable, IMyPcapPacket {
	
	protected int number = 0;
	protected String protocol = null;
	protected String time = null;
	protected int lengthOrig = 0;
	
	public MyPcapPacket() {
		this.protocol = "PCAPpacket";
		this.number = 20;
	}
	
	public MyPcapPacket(String protocol) {
		this.protocol = protocol;

	}

	@Override
	public String toString() {
		String result = "";
		return result;
	}
	
	@Override
	public int getNumber(){
		return number;
	}
	
	@Override
	public String getProtocol() {
		return protocol;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Override
	public String getTime() {
		return time;
	}

	@Override
	public int getLengthOrig() {
		return lengthOrig;
	}

	@Override
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public void setLengthOrig(int length) {
		this.lengthOrig = length;
	}

}
