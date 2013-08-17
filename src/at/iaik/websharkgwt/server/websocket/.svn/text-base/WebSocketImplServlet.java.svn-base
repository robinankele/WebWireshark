package at.iaik.websharkgwt.server.websocket;

import at.iaik.websharkgwt.server.servlets.PCAPAnalysisThread;
import at.iaik.websharkgwt.server.webserverstorage.PacketStore;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;
import at.iaik.websharkgwt.server.webserverstorage.TaskControllerSingleton;
import at.iaik.websharkgwt.shared.packet.implementation.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

@SuppressWarnings("serial")
public class WebSocketImplServlet extends WebSocketServlet{
	protected WebSocket webSocket;
	protected WebSocket.Connection webSocketConnection;
	protected Gson gson;
	protected int queueSize = 200;
	protected int msDelayBetweenPacket = 100;
	
	@Override
	public void init() throws ServletException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);	//THIS is needed to serialize LONG variables in a way that GWT understands.. (as string)
		gson = gsonBuilder.create();
		super.init();
	}
	
	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		webSocket = new WebSocket.OnTextMessage() {
			@Override
			public void onClose(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onOpen(Connection arg0) {
				webSocketConnection = arg0;
			}

			@Override
			public void onMessage(String message) {
				System.out.println("Your session id:" + message);
					//wait for 1 second
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String sessionID = message;
					
					PacketStore packetStore = PacketStoreSingleton.getInstance();
					packetStore.setQueueSize(queueSize);
					TaskControllerSingleton taskStore = TaskControllerSingleton.getInstance();
					if (taskStore.isTaskRunning(sessionID)) {
						
					} else {
						PCAPAnalysisThread pcapAnalysisThread = new PCAPAnalysisThread(sessionID, msDelayBetweenPacket);
						taskStore.runTask(sessionID,pcapAnalysisThread);
					}
					
					
					System.out.println("Server: Analysis Starting for SessionID: " + sessionID);
					
					MyPcapPacket my_pcap = null;
					String jsonString = "";
					
					while(true)
					{
						if(packetStore.isPacketAnalysisFinished(sessionID)){
							my_pcap = packetStore.getNextPacket(sessionID);
						}
						else if(packetStore.getPacketListSize(sessionID) > 5){
							my_pcap =packetStore.getNextPacket(sessionID);
						}

						if(my_pcap != null && my_pcap.getProtocol().toString().equals("FinalPacket"))
						{
							System.out.println("Server-----> last packet");
							webSocketConnection.close();
							return;
						}else 
						if(my_pcap != null)
						{

							System.out.println("########### WebSocketImpServlet: " + my_pcap.getProtocol() + "Robin is scheiss:" + packetStore.getQueueSize());
							 jsonString = gson.toJson(my_pcap);
							 try {
								webSocketConnection.sendMessage(jsonString);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

				}
			}
			
		};
		return webSocket;
	}
}
