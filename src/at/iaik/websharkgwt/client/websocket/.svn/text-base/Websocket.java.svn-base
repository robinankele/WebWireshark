package at.iaik.websharkgwt.client.websocket;

import java.util.Vector;

import at.iaik.websharkgwt.client.json.*;
import at.iaik.websharkgwt.client.services.LoginService;
import at.iaik.websharkgwt.shared.packet.implementation.*;
import at.iaik.websharkgwt.shared.packet.interfaces.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.ListDataProvider;

import de.csenk.gwt.ws.client.WebSocketConnection;
import de.csenk.gwt.ws.client.js.JavaScriptWebSocket;
import de.csenk.gwt.ws.client.js.JavaScriptWebSocketFactory;
import de.csenk.gwt.ws.shared.Connection;
import de.csenk.gwt.ws.shared.Handler;

public class Websocket {

	protected Connection webSocketClient;
	protected Vector<IMyPcapPacket> packetList = new Vector<IMyPcapPacket>();
	public int count = 0;

	public Websocket(){
		//this.packetList = packetList;
		testIfWebsocketsAvaliable();
	}
	
	public void testIfWebsocketsAvaliable(){
		//let's check wheter the browser supports websockets... definetelty works with a new chrome version
		if (!JavaScriptWebSocket.IsSupported()) {
			Window.alert("Websockets are not avaliable at your current configuration!");
            return;
		}
		System.out.println("Websockets ok!");
	}
	
	public void startConnection(final ListDataProvider<IMyPcapPacket> smallQueueSizePacketProvider){
		System.out.println("start Connection!");
		//ok let's get the url of the websocket: looks something like that: ws://127.0.0.1:8080/webSocket
	    String webSocketURL = GWT.getModuleBaseURL().replace("http", "ws") + "webSocket"; 
        webSocketClient = new WebSocketConnection(webSocketURL, new Handler() {
			
			@Override
			public void onMessageReceived(Connection connection, Object message) throws Throwable {
				//handle incomming messages from the server
				System.out.println("Client: get new message from Server: "+message);
				
				final MyBeanFactory myPacketFactory = MyBeanFactory.getInstance();
				String jsonContent = message.toString();
				
				if(message.toString().contains("EthernetFrame"))
					packetList.add((IMyEthernetFrame)myPacketFactory.getPacket(MyEthernetFrame.class.getName(), jsonContent));
				else if(message.toString().contains("IPv4"))
					packetList.add((IMyIPv4Packet)myPacketFactory.getPacket(MyIPv4Packet.class.getName(), jsonContent));
				else if(message.toString().contains("UDP"))
					packetList.add((IMyUdpPacket)myPacketFactory.getPacket(MyUDPPacket.class.getName(), jsonContent));
				else if(message.toString().contains("TCP"))
					packetList.add((IMyTcpPacket)myPacketFactory.getPacket(MyTCPPacket.class.getName(), jsonContent));
				else if(message.toString().contains("ICMP"))
					packetList.add((IMyIcmpPacket)myPacketFactory.getPacket(MyICMPPacket.class.getName(), jsonContent));
				else if(message.toString().contains("HTTP"))
					packetList.add((IMyHttpPacket)myPacketFactory.getPacket(MyHTTPPacket.class.getName(), jsonContent));
				else if(message.toString().contains("DHCP"))
					packetList.add((IMyDhcpPacket)myPacketFactory.getPacket(MyDHCPPacket.class.getName(), jsonContent));
					
	    		if(packetList.lastElement().getTime() == null){
	    			packetList.remove(packetList.size()-1);
	    			if(count > 0)
	    			count--;
	    		}
				
	    		if(!packetList.isEmpty())
	    			packetList.lastElement().setNumber(count++);
	    		smallQueueSizePacketProvider.setList(packetList);
				//webSocketClient.send(LoginService.sessionID);
			}
			
			@Override
			public void onExceptionCaught(Connection connection, Throwable caught) {
				//handler when something goes wrong
				System.err.println("Something gone terrible wrong!");
			}
			
			@Override
			public void onConnectionOpened(Connection connection) throws Throwable {
				//initial request
				webSocketClient.send(LoginService.sessionID);
				packetList.clear();
			}
			
			@Override
			public void onConnectionClosed(Connection connection) throws Throwable {
				//handler when the connection is closed...
				System.out.println("CLIENT: close Websocket Connection");
				webSocketClient.close();
			}
		}, new JavaScriptWebSocketFactory());
	}
	
	public void stopConnection(){
		System.out.println("CLIENT: close Websocket Connection");
		webSocketClient.close();
	}
}
