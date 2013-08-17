package at.iaik.websharkgwt.server.webserverstorage;

import java.util.HashMap;
import java.util.Vector;

import at.iaik.websharkgwt.shared.packet.implementation.MyPcapPacket;

public class PacketStore {
	protected HashMap<String,Vector<MyPcapPacket>> sessionIDToPacketListMap= new HashMap<String, Vector<MyPcapPacket>>();
	protected int queueSize = -1; //unlimitedQueue
	protected HashMap<String, Boolean> isPacketAnalyseFinish= new HashMap<String, Boolean>();
	
	public int getPacketListSize(String sessionID)
	{
		if(sessionIDToPacketListMap.get(sessionID) != null)
			return sessionIDToPacketListMap.get(sessionID).size();
		
		return 0;
	}
	
	public boolean isPacketAnalysisFinished(String sessionID)
	{
		return isPacketAnalyseFinish.containsKey(sessionID);
	}
	
	public int isPacketAnalyserNotifyListSize()
	{
		return isPacketAnalyseFinish.size();
	}
	//package protected, only the same package may use constructor
	PacketStore() {
	}
	
	public int getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
		if (queueSize>0) {
			//check for to big queues...
			for (String sessionID : sessionIDToPacketListMap.keySet()) {
				Vector<MyPcapPacket> packetList = sessionIDToPacketListMap.get(sessionID);
				if (packetList.size()>queueSize) {
					int endIndex = packetList.size()-1;
					int fromIndex = endIndex-queueSize;
					for (int i=0;i<fromIndex;i++) {
						packetList.remove(0);
					}
					sessionIDToPacketListMap.put(sessionID, packetList);
				}
			}
		}
	}

	public Vector<MyPcapPacket> getPacketList(String sessionID) {
		return sessionIDToPacketListMap.get(sessionID);
	}
	
	synchronized public MyPcapPacket getNextPacket(String sessionID) {
		Vector<MyPcapPacket> packetList = getPacketList(sessionID);
		if ((packetList != null) && (packetList.size()>0)) {
			MyPcapPacket packet = packetList.get(0);
			packetList.remove(0);
			return packet;
		} else {
			return null;
		}
	}
	
	synchronized public MyPcapPacket getLastPacket(String sessionID) {
		Vector<MyPcapPacket> packetList = getPacketList(sessionID);
		if ((packetList != null) && (packetList.size()>0)) {
			MyPcapPacket packet = packetList.lastElement();
			packetList.remove(packetList.size()-1);
			return packet;
		} else {
			return null;
		}
	}
	
	synchronized public void addPacket(String sessionID,MyPcapPacket packet) {
		Vector<MyPcapPacket> packetList = getPacketList(sessionID);
		if (packetList==null) {
			packetList = new Vector<MyPcapPacket>();
			sessionIDToPacketListMap.put(sessionID, packetList);
		}
		if (queueSize>0) {
				if (packetList.size()==queueSize) {
					packetList.remove(0);
				}
		}
		if(packet.getProtocol().contains("FinalPacket"))
			isPacketAnalyseFinish.put(sessionID, true);
		packetList.add(packet);
		
	}
	synchronized public void clearMyPackeOfSessionID(String sessionID) {
		sessionIDToPacketListMap.remove(sessionID);
		//sessionIDToPacketListMap.put(sessionID, null);
	}
	
	synchronized public void clearPacketListOfSessionID(String sessionID) {
		//sessionIDToPacketListMap.remove(sessionID);
		sessionIDToPacketListMap.put(sessionID, null);
	}
	
	synchronized public void clearAllPacketLists() {
	    sessionIDToPacketListMap= new HashMap<String, Vector<MyPcapPacket>>();
	}
}
