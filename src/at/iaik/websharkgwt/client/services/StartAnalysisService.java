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

public class StartAnalysisService {
	
	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";
	
	String baseUrl = GWT.getModuleBaseURL();
	String serviceUrl;
	
	public StartAnalysisService(String service_url) {
		serviceUrl = baseUrl + service_url ;
	}
	
	public void startAnalyse(final int delayInMsBetweenPackets, final int queueSize,  final ListDataProvider<IMyPcapPacket> smallQueueSizePacketProvider)
	{
		System.out.println("+++Client: StartAnalyseService called");
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(serviceUrl));
		
		builder.setHeader("Analyse", "start");
		builder.setHeader("delayTime", String.valueOf(delayInMsBetweenPackets));
		builder.setHeader("queueSize", String.valueOf(queueSize));
		
		try {
			builder.sendRequest(null, new RequestCallback() {
			   
				public void onError(Request request, Throwable exception) {
			    }
//
			    public void onResponseReceived(Request request, Response response) {
			    	if(200 == response.getStatusCode())
			    	{	
			    		System.out.println("++++Client: Hey analysis started");
			    		PacketPollingService.doPacketPolling(delayInMsBetweenPackets, queueSize, smallQueueSizePacketProvider);
			    	}
			    	if(204 == response.getStatusCode())
				    	  System.out.println("Analyse not started");
			    	if(302 == response.getStatusCode())
			    		System.out.println("Analyse finished");
			    	if(403 == response.getStatusCode())
			    	{
			    		System.out.println("+++client: Session Invalid [TIME OUT]");
			    		PacketDisplayTable.logout();
			    	}
			    }       
			});
		} catch (RequestException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
}
