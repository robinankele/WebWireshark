package at.iaik.websharkgwt.shared;

import at.iaik.websharkgwt.shared.packet.interfaces.*;

public class PacketCommand implements IPacketCommand {

	protected String packetType = "UDP";

	public String getPacketType() {
		return packetType;
	}

	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}
	

}
