package at.iaik.websharkgwt.shared.packet.implementation;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyFinalPacket;

@SuppressWarnings("serial")
public class MyFinalPacket extends MyPcapPacket implements IMyFinalPacket {

	public MyFinalPacket() {
		super("FinalPacket");
	}
	
	public MyFinalPacket(String protocol) {
		super(protocol);
	}
}
