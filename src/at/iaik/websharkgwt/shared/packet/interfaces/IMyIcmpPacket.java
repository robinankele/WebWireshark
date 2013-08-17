package at.iaik.websharkgwt.shared.packet.interfaces;

/**
 * @author Robin Ankele
 * @email  robin.ankele@student.tugraz.at
 */

public interface IMyIcmpPacket extends IMyIPv4Packet{
	public long getTypeIcmp();
	public void setTypeIcmp(long type);
	public long getCode();
	public void setCode(long code);
	public String getChecksum();
	public void setChecksum(String checksum);
	public long getIdentifierBE();
	public void setIdentifierBE(long identifierBE);
	public long getIdentifierLE();
	public void setIdentifierLE(long identifierLE);
	public long getSequenceNumberBE();
	public void setSequenceNumberBE(long sequenceNumberBE);
	public long getSequenceNumberLE();
	public void setSequenceNumberLE(long sequenceNumberLE);
	public String getData();
	public void setData(String data);
}
