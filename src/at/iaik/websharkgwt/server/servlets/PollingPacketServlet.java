package at.iaik.websharkgwt.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import at.iaik.websharkgwt.server.webserverstorage.PacketStore;
import at.iaik.websharkgwt.server.webserverstorage.PacketStoreSingleton;
import at.iaik.websharkgwt.shared.packet.implementation.MyPcapPacket;

public class PollingPacketServlet extends HttpServlet{

	protected Gson gson;

	public void init() throws ServletException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);	//THIS is needed to serialize LONG variables in a way that GWT understands.. (as string)
		gson = gsonBuilder.create();
		super.init();
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("PollingPacketServlet called here");
		PacketStore packetStore = PacketStoreSingleton.getInstance();
		String sessionID = SessionManager.getSessionIdFormCookie(req.getCookies());
		
		MyPcapPacket my_pcap = null;
		while(true)
		{
			if(packetStore.isPacketAnalysisFinished(sessionID))
			{
				System.err.println(packetStore.getPacketList(sessionID).size());
				my_pcap = packetStore.getNextPacket(sessionID);
				System.err.println(my_pcap.getProtocol());
			}
			else if(packetStore.getPacketListSize(sessionID) > 5)
			{
				System.err.println(packetStore.getPacketList(sessionID).size());
				my_pcap = packetStore.getNextPacket(sessionID);
			}

			if(my_pcap != null && my_pcap.getProtocol().equals("FinalPacket"))
			{
				System.out.println("*****FINAL PACKET*****");
				resp.setStatus(302);
				return;
			}else 
			if(my_pcap != null)
			{
				//if(PacketStoreSingleton.sendAllowed){
				System.out.println("########### StartAnalysisservlet: " + my_pcap.getProtocol() + "Robin is scheiss:" + packetStore.getQueueSize());
				String jsonString = gson.toJson(my_pcap);
				resp.setContentType("application/json");
				resp.setHeader("JsonClass", my_pcap.getClass().getName());
				PrintWriter writer = resp.getWriter();
				writer.println(jsonString);
				writer.close();
				resp.setStatus(200);
				//}
				return;
			}
		}
		
	}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
}
