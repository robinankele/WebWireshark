package at.iaik.websharkgwt.server.kraken.packetconstructor;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.krakenapps.pcap.decoder.dhcp.DhcpMessage;
import org.krakenapps.pcap.decoder.ethernet.EthernetFrame;
import org.krakenapps.pcap.decoder.http.HttpRequest;
import org.krakenapps.pcap.decoder.http.HttpResponse;
import org.krakenapps.pcap.decoder.icmp.IcmpPacket;
import org.krakenapps.pcap.decoder.ip.IpPacket;
import org.krakenapps.pcap.decoder.tcp.TcpPacket;
import org.krakenapps.pcap.decoder.udp.UdpPacket;
import org.krakenapps.pcap.packet.PcapPacket;

import at.iaik.websharkgwt.shared.packet.implementation.MyDHCPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyEthernetFrame;
import at.iaik.websharkgwt.shared.packet.implementation.MyHTTPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyICMPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyIPv4Packet;
import at.iaik.websharkgwt.shared.packet.implementation.MyPcapPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyTCPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyUDPPacket;

public class MyPacketConstructor {
	//you can use that as a basis...
	public static int counter = 1;
	
	
	public static MyPcapPacket construct(MyDHCPPacket myDHCPPacket, DhcpMessage dhcpPacket, MyPcapPacket udpPacket) {
		System.out.println(udpPacket.getProtocol());
	  if (myDHCPPacket==null) {
		myDHCPPacket = new MyDHCPPacket((MyUDPPacket)udpPacket);
		System.out.println("protocol: "+ myDHCPPacket.getProtocol());
	  }
	  
	  myDHCPPacket.setBootFilename(dhcpPacket.getBootFileName());
	  myDHCPPacket.setClientIPAddress(dhcpPacket.getClientAddress().toString());
	  myDHCPPacket.setClientMacAddress(dhcpPacket.getClientMac().toString());
	  myDHCPPacket.setBootpFlags(String.valueOf(dhcpPacket.getFlags()));
	  myDHCPPacket.setHardwareAddressLength(dhcpPacket.getHardwareAddressLength());
	  myDHCPPacket.setHardwareType(String.valueOf(dhcpPacket.getHardwareType()));
	  myDHCPPacket.setHops(dhcpPacket.getHops());
	  myDHCPPacket.setMessageType(dhcpPacket.getMessageType());
	  myDHCPPacket.setNextServerIPAddress(dhcpPacket.getNextServerAddress().toString());
	  
	 /* Vector<String> buffer = new Vector<String>();
	  for(int counter = 0; counter < dhcpPacket.getOptions().size(); counter++)
	  {
		  if(dhcpPacket.getOption(counter) != null)
			  buffer.add(dhcpPacket.getOption(counter).toString());
			//  System.out.println(dhcpPacket.getOption(counter).toString());
	  }
	  
	  myDHCPPacket.setOptions(buffer);*/
	  myDHCPPacket.setSecondsElapsed(dhcpPacket.getSecs());
	  myDHCPPacket.setServerHostname(dhcpPacket.getServerName());
	  myDHCPPacket.setTransactionID(String.valueOf(dhcpPacket.getTransactionId()));
	  myDHCPPacket.setYourClientIPAddress(dhcpPacket.getYourAddress().toString());

	  //dhcpPacket.getGatewayAddress()
	  return (MyPcapPacket)myDHCPPacket;
	}
	
	public static MyPcapPacket construct(MyHTTPPacket myHTTPPacket, HttpRequest request, HttpResponse response, MyPcapPacket tcpPacket, String sessionID) {
		if (myHTTPPacket==null) {
			myHTTPPacket = new MyHTTPPacket((MyTCPPacket)tcpPacket);
		}
		
		myHTTPPacket.setRequestMethod(request.getMethod().toString());
		myHTTPPacket.setRequestURL(request.getURL().toString());
		myHTTPPacket.setRequestVersion(request.getHttpVersion().toString());
		
		if(response == null)
		{
			HashMap<String, String> header = new HashMap<String, String>();
			for(Iterator<String> iter = request.getHeaderKeys().iterator(); iter.hasNext();)
			{
				String key = iter.next();
				header.put(key, request.getHeader(key));
			}
			myHTTPPacket.setHeader(header);
		}
	
		/*request.getHeader(sessionID).
		request.getFile(sessionID)
		request.getLocalAddress()
		request.getMimeMessage()
		request.getParameter(sessionID)
		request.getQueryString()
		request.getRemoteAddress()
		request.getTextContent()*/
		
		if(response != null)
		{

			myHTTPPacket.setStatusCode(response.getStatusCode());
			
			HashMap<String, String> header = new HashMap<String, String>();
			for(Iterator<String> iter = request.getHeaderKeys().iterator(); iter.hasNext();)
			{
				String key = iter.next();
				header.put(key, request.getHeader(key));
			}
			myHTTPPacket.setHeader(header);
			
			/*response.getContent()
			response.getInputStream()
			response.getStatusLine()*/
		}
		
		return (MyPcapPacket)myHTTPPacket;
	}
	
	
	public static MyPcapPacket construct(MyICMPPacket myICMPPacket, IcmpPacket icmpPacket) {
		if (myICMPPacket==null) {
			myICMPPacket = new MyICMPPacket();
		}
		
		myICMPPacket.setType(icmpPacket.getType());
		myICMPPacket.setCode(icmpPacket.getCode());
		myICMPPacket.setChecksum(String.valueOf(icmpPacket.getChecksum()));
		myICMPPacket.setIdentifierBE(icmpPacket.getId());
		myICMPPacket.setSequenceNumberBE(icmpPacket.getSeq());
		myICMPPacket.setData(icmpPacket.getData().toString());
		
		/*
		icmpPacket.getDestination()
		icmpPacket.getSource()
		*/
		
		if (icmpPacket.getIpPacket()!=null) {
			return construct(myICMPPacket,icmpPacket.getIpPacket());
		} else {
			return myICMPPacket;
		}
	}
	
	
	public static MyPcapPacket construct(MyTCPPacket myTCPPacket, TcpPacket tcpPacket) {
		if (myTCPPacket==null) {
			myTCPPacket = new MyTCPPacket();
		}
		
		myTCPPacket.setSourcePort(tcpPacket.getSourcePort());
		myTCPPacket.setDestinationPort(tcpPacket.getDestinationPort());
		myTCPPacket.setSequenceNumber(tcpPacket.getSeq());
		myTCPPacket.setAcknowlegmentNumber(tcpPacket.getRelativeAck());
		//myTCPPacket.setHeaderLength();
		myTCPPacket.setFlags(String.valueOf(tcpPacket.getFlags()));
		myTCPPacket.setWindowSizeValue(tcpPacket.getWindow());
		myTCPPacket.setChecksum(String.valueOf(tcpPacket.getChecksum()));	
		
		/*tcpPacket.getDataLength()
		tcpPacket.getDataOffset()
		tcpPacket.getDestination()
		tcpPacket.getDestinationAddress()
		tcpPacket.getDirection()
		tcpPacket.getOptions()
		tcpPacket.getPadding()
		tcpPacket.getReassembledLength()
		tcpPacket.getRelativeSeq()
		tcpPacket.getSessionKey()
		tcpPacket.getSource()
		tcpPacket.getSourceAddress()
		tcpPacket.getTotalLength()
		tcpPacket.getUrgentPointer()*/
		
		return construct(myTCPPacket,tcpPacket.getIpPacket());
	}
	
	public static MyPcapPacket construct(MyUDPPacket myUDPPacket, UdpPacket udpPacket) {
		if (myUDPPacket==null) {
			myUDPPacket = new MyUDPPacket();
		}
	    myUDPPacket.setSourcePort(udpPacket.getSourcePort());
	    myUDPPacket.setDestinationPort(udpPacket.getDestinationPort());
	    myUDPPacket.setLength(udpPacket.getLength());
	    myUDPPacket.setChecksum(String.valueOf(udpPacket.getChecksum()));
	    
	    /*udpPacket.getDestination()
	    udpPacket.getSource()*/
	    
		return construct(myUDPPacket,udpPacket.getIpPacket());
	}
	
	public static MyPcapPacket construct(MyIPv4Packet myIPv4Packet, IpPacket ipPacket) {
		if (myIPv4Packet==null) {
			myIPv4Packet = new MyIPv4Packet();
		}
		myIPv4Packet.setVersion(ipPacket.getVersion());
		myIPv4Packet.setDestinationIPAddress(ipPacket.getDestinationAddress().toString());
		myIPv4Packet.setSourceIPAddress(ipPacket.getSourceAddress().toString());
		//ipPacket.getL2Frame();
		
		return construct(myIPv4Packet,(EthernetFrame)ipPacket.getL2Frame());
	}
	
	public static MyPcapPacket construct(MyEthernetFrame myEthernetFrame,EthernetFrame ethernetFrame) {
		if (myEthernetFrame == null) {
			myEthernetFrame = new MyEthernetFrame();
		}
		myEthernetFrame.setDestinationAddress(ethernetFrame.getDestination().toString());
		myEthernetFrame.setSourceAddress(ethernetFrame.getSource().toString());
		myEthernetFrame.setType(ethernetFrame.getType());
		
		return (construct(myEthernetFrame,ethernetFrame.getPcapPacket()));
	}
	
	public static MyPcapPacket construct(MyPcapPacket myPcapPacket,PcapPacket pcapPacket) {
		if (myPcapPacket == null) {
			myPcapPacket = new MyPcapPacket();
		}
		
		myPcapPacket.setLengthOrig(pcapPacket.getPacketHeader().getInclLen());
		//pcapPacket.getPacketHeader().getInclLen()
		Date timestamp = new Date(pcapPacket.getPacketHeader().getTsSec());
		myPcapPacket.setTime(timestamp.toString()/* + timestamp.getTime()*/);
		//pcapPacket.getPacketHeader().getTsUsec()
		
		return myPcapPacket;
	}
}
