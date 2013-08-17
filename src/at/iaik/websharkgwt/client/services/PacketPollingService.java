package at.iaik.websharkgwt.client.services;

import java.util.List;
import java.util.Vector;

import at.iaik.websharkgwt.client.gui.PacketDisplayTable;
import at.iaik.websharkgwt.client.json.MyBeanFactory;
import at.iaik.websharkgwt.shared.packet.implementation.MyDHCPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyEthernetFrame;
import at.iaik.websharkgwt.shared.packet.implementation.MyFinalPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyHTTPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyICMPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyIPv4Packet;
import at.iaik.websharkgwt.shared.packet.implementation.MyPcapPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyUDPPacket;
import at.iaik.websharkgwt.shared.packet.implementation.MyTCPPacket;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyDhcpPacket;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyEthernetFrame;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyHttpPacket;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyIPv4Packet;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyIcmpPacket;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyPcapPacket;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyUdpPacket;
import at.iaik.websharkgwt.shared.packet.interfaces.IMyTcpPacket;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.ListDataProvider;

public class PacketPollingService {
	
	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";
	
	static String baseUrl = GWT.getModuleBaseURL();
	static String pollingUrl =  baseUrl + "pollingPacketServlet";
	
	protected static Vector<IMyPcapPacket> packetList = new Vector<IMyPcapPacket>();
	public static int count = 0;
	/*public PacketPollingService(String service_url) {
		pollingUrl = baseUrl + "pollingPacketServlet";
	}*/
	
	public static void doPacketPolling(final int delayInMsBetweenPackets, final int queueSize,  final ListDataProvider<IMyPcapPacket> smallQueueSizePacketProvider)
	{
		System.out.println("+++Client: StartAnalyseService called");
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(pollingUrl));
		
		builder.setHeader("Analyse", "start");
		builder.setHeader("delayTime", String.valueOf(delayInMsBetweenPackets));
		builder.setHeader("queueSize", String.valueOf(queueSize));

		RequestBuilder polling_builder = new RequestBuilder(RequestBuilder.GET, URL.encode(pollingUrl));	    		
			    		
    		try {
				polling_builder.sendRequest(null, new RequestCallback() {
					
					@Override
					public void onResponseReceived(Request request, Response response) {
				    	if(200 == response.getStatusCode())
				    	{
				    		final MyBeanFactory myPacketFactory = MyBeanFactory.getInstance();
				    		String jsonContent = response.getText();
				    		String jsonClass = response.getHeader("JsonClass");
				    		System.out.println("+++Client: I got a Jason Packet: " + jsonContent);
				    		
				    		if(jsonContent.toString().contains("HTTP")){
								packetList.add((IMyHttpPacket)myPacketFactory.getPacket(jsonClass, jsonContent));
								System.out.println("CLIENT: new HttpPacket received");
				    		}
				    		else if(jsonContent.toString().contains("DHCP")){
								packetList.add((IMyDhcpPacket)myPacketFactory.getPacket(jsonClass, jsonContent));
								System.out.println("CLIENT: new Dhcp Packet received");
								//System.err.println(packetList.lastElement());
				    		}
				    		else if(jsonContent.toString().contains("EthernetFrame")){
								packetList.add((IMyEthernetFrame)myPacketFactory.getPacket(jsonClass, jsonContent));
								System.out.println("CLIENT: new EthernetFrame received");
				    		}
				    		else if(jsonContent.toString().contains("IPv4")){
								packetList.add((IMyIPv4Packet)myPacketFactory.getPacket(jsonClass, jsonContent));
								System.out.println("CLIENT: new IPv4Packet received");
				    		}
				    		else if(jsonContent.toString().contains("UDP")){
								packetList.add((IMyUdpPacket)myPacketFactory.getPacket(jsonClass, jsonContent));
								System.out.println("CLIENT: new UdpPacket received");
				    		}
				    		else if(jsonContent.toString().contains("TCP")){
								packetList.add((IMyTcpPacket)myPacketFactory.getPacket(jsonClass, jsonContent));
								System.out.println("CLIENT: new TcpPacket received");
				    		}
				    		else if(jsonContent.toString().contains("ICMP")){
								packetList.add((IMyIcmpPacket)myPacketFactory.getPacket(jsonClass, jsonContent));
								System.out.println("CLIENT: new IcmpPacket received");
				    		}
							
				    		if(packetList.lastElement().getTime() == null){
				    			packetList.remove(packetList.size()-1);
				    			if(count > 0)
				    			count--;
				    		}

				    		//System.err.println(packetList.lastElement());
				    		if(!packetList.isEmpty())
				    			packetList.lastElement().setNumber(count++);

				    		smallQueueSizePacketProvider.setList(packetList);
				    		doPacketPolling(delayInMsBetweenPackets, queueSize, smallQueueSizePacketProvider);
				    	}else 
				    	if(302 == response.getStatusCode())
			    		{	
				    		System.out.println("######<<<<<<--->>>>>>LOVELY---Analyse finished---->>>###");
				    		return;
			    		}

						
					}
					
					@Override
					public void onError(Request request, Throwable exception) {
						// TODO Auto-generated method stub
						
					}
				});
			} catch (RequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
}
