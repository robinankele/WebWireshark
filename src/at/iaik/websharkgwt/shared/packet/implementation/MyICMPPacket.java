package at.iaik.websharkgwt.shared.packet.implementation;

import at.iaik.websharkgwt.shared.packet.interfaces.IMyIcmpPacket;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

@SuppressWarnings("serial")
public class MyICMPPacket extends MyIPv4Packet implements IMyIcmpPacket {

	protected long type_icmp = 0;
	protected long code = 0;
	protected String checksum = null;
	protected long identifierBE = 0;
	protected long identifierLE = 0;
	protected long sequenceNumberBE = 0;
	protected long sequenceNumberLE = 0;
	protected String data = null;
	
	public MyICMPPacket() {
		super("ICMP");
	}
	
	public MyICMPPacket(String protocol) {
		super(protocol);
	}
	
	public long getTypeIcmp() {
		return type_icmp;
	}

	public void setTypeIcmp(long type) {
		this.type_icmp = type;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public long getIdentifierBE() {
		return identifierBE;
	}

	public void setIdentifierBE(long identifierBE) {
		this.identifierBE = identifierBE;
	}

	public long getIdentifierLE() {
		return identifierLE;
	}

	public void setIdentifierLE(long identifierLE) {
		this.identifierLE = identifierLE;
	}

	public long getSequenceNumberBE() {
		return sequenceNumberBE;
	}

	public void setSequenceNumberBE(long sequenceNumberBE) {
		this.sequenceNumberBE = sequenceNumberBE;
	}

	public long getSequenceNumberLE() {
		return sequenceNumberLE;
	}

	public void setSequenceNumberLE(long sequenceNumberLE) {
		this.sequenceNumberLE = sequenceNumberLE;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
