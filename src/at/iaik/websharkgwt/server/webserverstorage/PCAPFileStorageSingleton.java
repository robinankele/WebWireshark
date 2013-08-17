package at.iaik.websharkgwt.server.webserverstorage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

//import com.google.gwt.dev.util.collect.HashMap;


public class PCAPFileStorageSingleton {
	static private PCAPFileStorageSingleton pcapFileStore;
	public static PCAPFileStorageSingleton getInstance() {
		if (pcapFileStore == null) {
			pcapFileStore = new PCAPFileStorageSingleton();
		}
		return pcapFileStore;
	}
	
	HashMap<String, byte[]> sessionIdToFileDataMapper = new HashMap<String, byte[]>();
	
	protected PCAPFileStorageSingleton() {
	}
	
	public void addFile(String sessionID,byte[] fileData) {
		sessionIdToFileDataMapper.put(sessionID, fileData);
	}
	
	public void clearPacketStore(String sessionID) {
		sessionIdToFileDataMapper.remove(sessionID);
	}
	
	public InputStream getInputstream(String sessionID) {
		byte[] fileData = sessionIdToFileDataMapper.get(sessionID);
		if (fileData != null) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
			return byteArrayInputStream;
		} else {
			return null;
		}
	}
}
