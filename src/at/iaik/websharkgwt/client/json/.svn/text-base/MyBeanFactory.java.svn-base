package at.iaik.websharkgwt.client.json;

import at.iaik.websharkgwt.shared.packet.interfaces.*;
import at.iaik.websharkgwt.shared.packet.implementation.*;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

public class MyBeanFactory {
	protected static MyBeanFactory myPacketFactory = null;
	protected MyAutobeanFactory autoBeanFactory;
	
	public static MyBeanFactory getInstance() {
		if (myPacketFactory == null) {
			myPacketFactory = new MyBeanFactory();
		}
		return myPacketFactory;
	}
	
	private MyBeanFactory() {
		autoBeanFactory = GWT.create(MyAutobeanFactory.class);
	}

	public IMyPcapPacket getPacket(String currentClass, String jsonString) {		
		if (MyEthernetFrame.class.getName().equals(currentClass)) {
			AutoBean<IMyEthernetFrame> decodedBean = AutoBeanCodex.decode(autoBeanFactory, IMyEthernetFrame.class, jsonString);
			return decodedBean.as();
		} 
		else if (MyIPv4Packet.class.getName().equals(currentClass)) {
			AutoBean<IMyIPv4Packet> decodedBean = AutoBeanCodex.decode(autoBeanFactory, IMyIPv4Packet.class, jsonString);
			return decodedBean.as();
		} 
		else if (MyTCPPacket.class.getName().equals(currentClass)) {
			AutoBean<IMyTcpPacket> decodedBean = AutoBeanCodex.decode(autoBeanFactory, IMyTcpPacket.class, jsonString);
			return decodedBean.as();
		} 
		else if (MyUDPPacket.class.getName().equals(currentClass)) {
			AutoBean<IMyUdpPacket> decodedBean = AutoBeanCodex.decode(autoBeanFactory, IMyUdpPacket.class, jsonString);
			return decodedBean.as();
		} 
		else if (MyICMPPacket.class.getName().equals(currentClass)) {
			AutoBean<IMyIcmpPacket> decodedBean = AutoBeanCodex.decode(autoBeanFactory, IMyIcmpPacket.class, jsonString);
			return decodedBean.as();
		} 
		else if (MyHTTPPacket.class.getName().equals(currentClass)) {
			AutoBean<IMyHttpPacket> decodedBean = AutoBeanCodex.decode(autoBeanFactory, IMyHttpPacket.class, jsonString);
			return decodedBean.as();
		} 
		else if (MyDHCPPacket.class.getName().equals(currentClass)) {
			System.err.println("Hallo");
			AutoBean<IMyDhcpPacket> decodedBean = AutoBeanCodex.decode(autoBeanFactory, IMyDhcpPacket.class, jsonString);
			return decodedBean.as();
		} 
		else {
			return null;
		}
	}
	
	public IPacketCommand getPacketCommand() {
		// Construct the AutoBean
	    AutoBean<IPacketCommand> packetCommand = autoBeanFactory.getCommand();
	    
	    // Return the Person interface shim
	    return packetCommand.as();
	}
}
