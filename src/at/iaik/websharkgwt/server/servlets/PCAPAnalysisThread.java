package at.iaik.websharkgwt.server.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.krakenapps.pcap.Protocol;
import org.krakenapps.pcap.decoder.dhcp.DhcpDecoder;
import org.krakenapps.pcap.decoder.ethernet.EthernetDecoder;
import org.krakenapps.pcap.decoder.ethernet.EthernetType;
import org.krakenapps.pcap.decoder.icmp.IcmpDecoder;
import org.krakenapps.pcap.decoder.ip.InternetProtocol;
import org.krakenapps.pcap.decoder.ip.IpDecoder;
import org.krakenapps.pcap.decoder.tcp.TcpDecoder;
import org.krakenapps.pcap.decoder.tcp.TcpPortProtocolMapper;
import org.krakenapps.pcap.decoder.tcp.TcpProtocolMapper;
import org.krakenapps.pcap.decoder.udp.UdpDecoder;
import org.krakenapps.pcap.decoder.udp.UdpPortProtocolMapper;
import org.krakenapps.pcap.packet.PcapPacket;

import at.iaik.websharkgwt.server.kraken.MyDHCPDecoder;
import at.iaik.websharkgwt.server.kraken.MyDHCPProcessor;
import at.iaik.websharkgwt.server.kraken.MyEthernetDecoder;
import at.iaik.websharkgwt.server.kraken.MyHTTPDecoder;
import at.iaik.websharkgwt.server.kraken.MyHTTPProcessor;
import at.iaik.websharkgwt.server.kraken.MyICMPDecoder;
import at.iaik.websharkgwt.server.kraken.MyICMPProcessor;
import at.iaik.websharkgwt.server.kraken.MyIPv4Decoder;
import at.iaik.websharkgwt.server.kraken.MyTCPDecoder;
import at.iaik.websharkgwt.server.kraken.MyTCPSegmentCallback;
import at.iaik.websharkgwt.server.kraken.MyUDPDecoder;
import at.iaik.websharkgwt.server.kraken.MyUDPProcessor;
import at.iaik.websharkgwt.server.kraken.PcapFileInputStreamWrapper;
import at.iaik.websharkgwt.server.webserverstorage.PCAPFileStorageSingleton;
import at.iaik.websharkgwt.server.webserverstorage.PacketStore;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;
import at.iaik.websharkgwt.server.webserverstorage.TaskControllerSingleton;
//import at.iaik.websharkgwt.shared.packet.implementations.MyFinalPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyFinalPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyPcapPacket;

public class PCAPAnalysisThread extends Thread {

	protected String sessionID;
	protected int msDelayBetweenPacket;
	
	public PCAPAnalysisThread(String sessionID,int msDelayBetweenPacket) {
		this.sessionID = sessionID; 
		this.msDelayBetweenPacket = msDelayBetweenPacket;
	}
	

	
	@Override
	public void run() {
		PacketStore packetStore = PacketStoreSingleton.getInstance();
		packetStore.clearPacketListOfSessionID(sessionID);
		
		TaskControllerSingleton taskController = TaskControllerSingleton.getInstance();
		PcapFileInputStreamWrapper is = null;
		try {
			InputStream inputStream = PCAPFileStorageSingleton.getInstance().getInputstream(sessionID);
			if (inputStream == null) {
				taskController.removeTask(sessionID);
				return;
			}

			is = new PcapFileInputStreamWrapper(inputStream);
			
			//Ethernet
			EthernetDecoder ethernetDecoder = new MyEthernetDecoder(sessionID,false);
			
			//IP
			IpDecoder ipDecoder = new MyIPv4Decoder(sessionID, false);
			ethernetDecoder.register(EthernetType.IPV4,ipDecoder);
			
			//ICMP
			IcmpDecoder icmpDecoder = new MyICMPDecoder(sessionID, false);
			ipDecoder.register(InternetProtocol.ICMP,icmpDecoder);
			MyICMPProcessor icmpProcessor = new MyICMPProcessor(sessionID, true);
			icmpDecoder.register(icmpProcessor);
			
			//UDP
			UdpDecoder udpDecoder = new MyUDPDecoder(sessionID, false, new UdpPortProtocolMapper());
			ipDecoder.register(InternetProtocol.UDP, udpDecoder);
			MyUDPProcessor myUDPProcessor = new MyUDPProcessor(sessionID, true);
			udpDecoder.registerUdpProcessor(myUDPProcessor);	//dump UDP Packet information
				
			//TCP
			TcpDecoder tcpDecoder = new MyTCPDecoder(sessionID,true,new TcpPortProtocolMapper());
			ipDecoder.register(InternetProtocol.TCP, tcpDecoder);
			MyTCPSegmentCallback myTCPSegmentCallback = new MyTCPSegmentCallback(sessionID, false); //dump TCP Packet information
			tcpDecoder.registerSegmentCallback(myTCPSegmentCallback);
		
			//HTTP
			MyHTTPDecoder myHTTPDecoder = new MyHTTPDecoder(sessionID, false);
			tcpDecoder.getProtocolMapper().register(Protocol.HTTP, myHTTPDecoder);
			// if true -> http packets enabled
			MyHTTPProcessor myHTTPProcessor = new MyHTTPProcessor(sessionID, true);
			myHTTPDecoder.register(myHTTPProcessor);
			
			//DHCP
			DhcpDecoder dhcpDecoder = new MyDHCPDecoder(sessionID, false);
			udpDecoder.getProtocolMapper().register(Protocol.DHCP, dhcpDecoder);
			// if true -> dhcp packets enabled
			MyDHCPProcessor myDHCPProcessor = new MyDHCPProcessor(sessionID, true);
			dhcpDecoder.register(myDHCPProcessor);
			
			while (true) {
				PcapPacket pcapPacket = null;
				if (!taskController.isTaskBeingStopped(sessionID)) {
					try {
						pcapPacket = is.getPacket();
						if (pcapPacket!=null) {
							ethernetDecoder.decode(pcapPacket);
							//System.out.println("packet added in packetstore by Thread");
						}
					} catch (Exception e) {
					}
				} else {
					packetStore.clearPacketListOfSessionID(sessionID);
				}
				if (pcapPacket == null) {
					MyFinalPacket finalPacket = new MyFinalPacket();
					//System.out.println("################ FINAL PACKET #################");
					packetStore.addPacket(sessionID,finalPacket);
					break;
				} else {
					if (msDelayBetweenPacket>0) {
						Thread.sleep(msDelayBetweenPacket);
					} 
				}	
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		taskController.removeTask(sessionID);
	}
	
}
