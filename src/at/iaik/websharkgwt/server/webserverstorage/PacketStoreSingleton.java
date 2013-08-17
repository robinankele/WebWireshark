package at.iaik.websharkgwt.server.webserverstorage;


public class PacketStoreSingleton {
	
	static private PacketStore packetStore;
	static public boolean sendAllowed = true;
	
	public static PacketStore getInstance() {
		if (packetStore == null) {
			packetStore = new PacketStore();
			packetStore.setQueueSize(-1);
		}
		return packetStore;
	}
	
}
