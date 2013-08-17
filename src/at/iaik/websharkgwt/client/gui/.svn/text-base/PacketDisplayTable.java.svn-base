package at.iaik.websharkgwt.client.gui;


import java.util.List;
import java.util.Map;
import java.util.Vector;

//import at.iaik.websharkgwt.client.packetpolling.SinglePacketPoller;
//import at.iaik.websharkgwt.client.services.PCAPAnalysisControllerServiceAsync;
//import at.iaik.websharkgwt.shared.ServletLocations;
import at.iaik.websharkgwt.client.services.LoginService;
import at.iaik.websharkgwt.client.services.FileUploadService;
import at.iaik.websharkgwt.client.services.StartAnalysisService;
import at.iaik.websharkgwt.client.services.StopAnalysisService;
import at.iaik.websharkgwt.client.websocket.Websocket;
import at.iaik.websharkgwt.shared.ServletLocations;
import at.iaik.websharkgwt.shared.packet.implementation.*;
import at.iaik.websharkgwt.shared.packet.interfaces.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class PacketDisplayTable extends Composite {

	private static PacketDisplayTableUiBinder uiBinder = GWT.create(PacketDisplayTableUiBinder.class);
	protected ListDataProvider<IMyPcapPacket> smallQueueSizePacketProvider = new ListDataProvider<IMyPcapPacket>();
	protected Vector<IMyPcapPacket> localPacketListStore = new Vector<IMyPcapPacket>();
	public SingleSelectionModel<IMyPcapPacket> smallTablesingleSelectionModel;
	private Websocket webSocket;
	
	@UiField(provided=true) CellTable<IMyPcapPacket> smallQueuePacketCellTable = new CellTable<IMyPcapPacket>();
	@UiField FileUpload fileUpload;
	@UiField FormPanel formPanel;
	@UiField Button submitButton;
	@UiField TextArea packetDetailsSmallQueueTextArea;
	@UiField Button startWebsocketDataButton;
	@UiField Button startPollingDataButton;
	@UiField SimplePager smallQueueSimplePager;
	@UiField Button stopPollingDataButton;
	@UiField IntegerBox delayInMsIntegerBox2;

	public static TextArea textArea;
	public static void logout()
	{
		System.out.println("Thank you for your visit... till next time!");
		Window.alert("[Time out] Session is invalid");
		Window.Location.reload();
	}
	
	
	public static void ceatePacketTrafficDisplay()
	{
		textArea = new TextArea();
	    textArea.ensureDebugId("cwBasicText-textarea");
	    textArea.setVisibleLines(5);
		RootPanel.get("nav").add(textArea);
	}
	interface PacketDisplayTableUiBinder extends UiBinder<Widget, PacketDisplayTable> {
	}

	public PacketDisplayTable() {

		//GWT code for unpacking the XML definition of the GUI
		initWidget(uiBinder.createAndBindUi(this));
		
		//GUI inits
		//init the file upload widget
		createFileUploadGUI();
		
		//init the table (the right one)
		createSmallQueueGUI();
		
		//ceatePacketTrafficDisplay();
	}
	
	protected void createFileUploadGUI() {
		//create an HTML form for file upload
		fileUpload.setName("pcapFileUpload");
		formPanel.addSubmitHandler(new SubmitHandler() {
			
			@Override
			public void onSubmit(SubmitEvent event) {
			}
			
		});		
		
		formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {
	
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				//nothing here, we could pop up a message here..
				
				if(event.getResults().equals("403"))
				{
					System.out.println("+++++Cleint: timeout");
					logout();
				}else
				{
					System.out.println("+++client: fileUpload Completed");
					Window.alert("File Sucessfully uploadet!");
					//counter = 0;
				}
				System.out.println("+++client: fileUpload Completed");
				//Window.alert("File Sucessfully uploadet!");
			}
			
		});
		
		//setup file upload widget
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART); 
		formPanel.setMethod(FormPanel.METHOD_POST); 
		formPanel.setAction(ServletLocations.FILE_UPLOAD_SERVLET);
		
		//submit button
		submitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("+++Cleint: FileUpload Button clicked");
				formPanel.submit();
			}
		});
	}

	
	//PACKET POLLING...
	//here, we assume that the server loads the PCAP file, but has a limited queue, the packets are lost on the server, if they are not retrieved within time from the client
	protected void createSmallQueueGUI() {
		
		TextColumn<IMyPcapPacket> noColumn = new TextColumn<IMyPcapPacket>() {
			@Override
			public String getValue(IMyPcapPacket packet){
				return packet.getNumber() + "";
			}
		};
		TextColumn<IMyPcapPacket> timeColumn = new TextColumn<IMyPcapPacket>() {
			@Override
			public String getValue(IMyPcapPacket packet) {
				return packet.getTime() +"";
			}
		};
		TextColumn<IMyPcapPacket> sourceColumn = new TextColumn<IMyPcapPacket>() {
			@Override
			public String getValue(IMyPcapPacket packet) {
				if(((IMyIPv4Packet)packet).getSourceIPAddress() != null)
					return ((IMyIPv4Packet)packet).getSourceIPAddress().substring(1)+"";
				return null;
			}
		};TextColumn<IMyPcapPacket> destinationColumn = new TextColumn<IMyPcapPacket>() {
			@Override
			public String getValue(IMyPcapPacket packet) {
				if(((IMyIPv4Packet)packet).getDestinationIPAddress() != null)
					return ((IMyIPv4Packet)packet).getDestinationIPAddress().substring(1)+"";
				return null;
			}
		};TextColumn<IMyPcapPacket> protocolColumn = new TextColumn<IMyPcapPacket>() {
			@Override
			public String getValue(IMyPcapPacket packet) {
				return packet.getProtocol()+"";
			}
		};TextColumn<IMyPcapPacket> lengthColumn = new TextColumn<IMyPcapPacket>() {
			@Override
			public String getValue(IMyPcapPacket packet) {
				return packet.getLengthOrig() +"";
			}
		};
	
		smallQueuePacketCellTable.addColumn(noColumn,"No.");
		smallQueuePacketCellTable.addColumn(timeColumn,"Time.");
		smallQueuePacketCellTable.addColumn(sourceColumn,"Source.");
		smallQueuePacketCellTable.addColumn(destinationColumn,"Destination.");
		smallQueuePacketCellTable.addColumn(protocolColumn,"Protocol.");
		smallQueuePacketCellTable.addColumn(lengthColumn,"Length.");
		smallQueuePacketCellTable.setRowCount(10);
		smallQueuePacketCellTable.setVisibleRange(0, 10);
		smallQueueSizePacketProvider.addDataDisplay(smallQueuePacketCellTable);
		smallTablesingleSelectionModel = new SingleSelectionModel<IMyPcapPacket>();
		smallTablesingleSelectionModel.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				//IMyPcapPacket selectedObject = smallTablesingleSelectionModel.getSelectedObject();
				//System.out.println(smallTablesingleSelectionModel.getSelectedObject().getClass().toString());
				String details = "";
				
				if(smallTablesingleSelectionModel.getSelectedObject().getProtocol().contains("DHCP"))
					details = getDhcpDetails((IMyDhcpPacket)smallTablesingleSelectionModel.getSelectedObject());
				else if(smallTablesingleSelectionModel.getSelectedObject().getProtocol().contains("HTTP"))
					details = getHttpDetails((IMyHttpPacket)smallTablesingleSelectionModel.getSelectedObject());
				else if(smallTablesingleSelectionModel.getSelectedObject().getProtocol().contains("ICMP"))
					details = getIcmpDetails((IMyIcmpPacket)smallTablesingleSelectionModel.getSelectedObject());
				else if(smallTablesingleSelectionModel.getSelectedObject().getProtocol().contains("UDP"))
					details = getUdpDetails((IMyUdpPacket)smallTablesingleSelectionModel.getSelectedObject());
				else if(smallTablesingleSelectionModel.getSelectedObject().getProtocol().contains("TCP"))
					details = getTcpDetails((IMyTcpPacket)smallTablesingleSelectionModel.getSelectedObject());
				else if(smallTablesingleSelectionModel.getSelectedObject().getProtocol().contains("IPv4"))
					details = getIpv4Details((IMyIPv4Packet)smallTablesingleSelectionModel.getSelectedObject());
				else if(smallTablesingleSelectionModel.getSelectedObject().getProtocol().contains("EthernetFrame"))
					details = getEthernetDetails((IMyEthernetFrame)smallTablesingleSelectionModel.getSelectedObject());
				else
					details = "";
				
				packetDetailsSmallQueueTextArea.setText(details);
			}
		});
		
		/*TextArea textArea = new TextArea();
	    textArea.ensureDebugId("cwBasicText-textarea");
	    textArea.setVisibleLines(5);
		*/
		smallQueuePacketCellTable.setSelectionModel(smallTablesingleSelectionModel);
		smallQueueSimplePager.setPageSize(10);
		smallQueueSimplePager.setDisplay(smallQueuePacketCellTable);
		
		
		
	}	
	
	//button which starts the PCAP analysis on the server. The server puts the packets in its queue and the client retrieves them by AJAX polling
	@UiHandler("startPollingDataButton")
	void onstartPollingDataButtonClick(ClickEvent event) {
		localPacketListStore.clear();
		smallQueueSizePacketProvider.setList(localPacketListStore);
		StartAnalysisService startPollingDataService = new StartAnalysisService("startAnalysisServlet");
		int delayInMsBetweenPackets = delayInMsIntegerBox2.getValue();
		int queueSize = 200;
		startPollingDataService.startAnalyse(delayInMsBetweenPackets, queueSize , smallQueueSizePacketProvider);
	}
	
	@UiHandler("startWebsocketDataButton")
	void onstartWebsocketDataButtonClick(ClickEvent event) {
		//use a Websocket to poll for data
		localPacketListStore.clear();
		smallQueueSizePacketProvider.setList(localPacketListStore);
		this.webSocket = new Websocket();
		webSocket.startConnection(smallQueueSizePacketProvider);
		//smallQueueSizePacketProvider.setList(localPacketListStore);
	}
	
	//stop analysis
	@UiHandler("stopPollingDataButton")
	void onstopPollingDataButtonClick(ClickEvent event) {
		StopAnalysisService stopPollingDataService = new StopAnalysisService("stopAnalysisServlet");
		stopPollingDataService.stopAnalyse();
		if(webSocket != null)
			webSocket.stopConnection();
	}

	public ListDataProvider<IMyPcapPacket> getSmallQueueSizePacketProvider() {
		return smallQueueSizePacketProvider;
	}
	
	public String getEthernetDetails(IMyEthernetFrame myEthernetFrame){
		String ethernetDetails = "Frame:\n";
		ethernetDetails += "  Arrival Time: "+ myEthernetFrame.getArrivalTime() + "\n";
		ethernetDetails += "  Epoch Time: " + myEthernetFrame.getEpochTime() + "\n";
		ethernetDetails += "  Frame Number: " + myEthernetFrame.getFrameNumber() + "\n";
		ethernetDetails += "  Frame Length: " + myEthernetFrame.getFrameLength() + "\n";
		ethernetDetails += "  Capture Length: " + myEthernetFrame.getCaptureLength() + "\n";
		ethernetDetails += "Ethernet: \n";
		ethernetDetails += "  Destination: " + myEthernetFrame.getDestinationAddress() + "\n";
		ethernetDetails += "  Source: " + myEthernetFrame.getSourceAddress() + "\n";
		return ethernetDetails;
	}
	
	public String getIpv4Details(IMyIPv4Packet myIPv4Packet){
		String ipv4Details = "Internet Protocol:\n";
		ipv4Details += "  Version: "+ myIPv4Packet.getVersion() + "\n";
		ipv4Details += "  Header Length: "+ myIPv4Packet.getHeaderLength() + "\n";
		ipv4Details += "  Differentiated Services Field: "+ myIPv4Packet.getDifferentiatedServiceField() + "\n";
		ipv4Details += "  Total Length: "+ myIPv4Packet.getTotalLength() + "\n";
		ipv4Details += "  Identification: "+ myIPv4Packet.getIdentification() + "\n";
		ipv4Details += "  Flags: "+ myIPv4Packet.getFlags() + "\n";
		ipv4Details += "  Frame offset: "+ myIPv4Packet.getFragmentOffset()+ "\n";
		ipv4Details += "  Time to live: "+ myIPv4Packet.getTimeToLive() + "\n";
		ipv4Details += "  Protocol: "+ myIPv4Packet.getProtocol() + "\n";
		ipv4Details += "  Header checksum: "+ myIPv4Packet.getHeaderChecksum() + "\n";
		ipv4Details += "  Source: "+ myIPv4Packet.getSourceIPAddress() + "\n";
		ipv4Details += "  Destination: "+ myIPv4Packet.getDestinationIPAddress() + "\n";
		return getEthernetDetails(myIPv4Packet) + ipv4Details;
	}
	
	public String getTcpDetails(IMyTcpPacket myTcpPacket){
		String tcpDetails = "Transmission Control Protocol:\n";
		tcpDetails += "  Source Port: "+ myTcpPacket.getSourcePort() + "\n";
		tcpDetails += "  Destination Port: "+ myTcpPacket.getDestinationPort() + "\n";
		tcpDetails += "  Sequence Number: "+ myTcpPacket.getSequenceNumber() + "\n";
		tcpDetails += "  Header length: "+ myTcpPacket.getHeaderLength()+ "\n";
		tcpDetails += "  Flags: "+ myTcpPacket.getFlags() + "\n";
		tcpDetails += "  Window size value: "+ myTcpPacket.getWindowSizeValue() + "\n";
		tcpDetails += "  Checksum: "+ myTcpPacket.getChecksum() + "\n";
		tcpDetails += "  Options: "+ myTcpPacket.getOptions() + "\n";
		return getIpv4Details(myTcpPacket) + tcpDetails;
	}
	
	public String getUdpDetails(IMyUdpPacket myUdpPacket){
		String udpDetails = "User Datagram Protocol:\n";
		udpDetails += "  Source Port: "+ myUdpPacket.getSourcePort() + "\n";
		udpDetails += "  Destination Port: "+ myUdpPacket.getDestinationPort() + "\n";
		udpDetails += "  Length: "+ myUdpPacket.getLength() + "\n";
		udpDetails += "  Checksum: "+ myUdpPacket.getChecksum() + "\n";
		return getIpv4Details(myUdpPacket) + udpDetails;
	}
	
	public String getIcmpDetails(IMyIcmpPacket myIcmpPacket){
		String icmpDetails = "Internet Control Message Protocol:\n";
		icmpDetails += "  Type: "+ myIcmpPacket.getType() + "\n";
		icmpDetails += "  Code: "+ myIcmpPacket.getCode() + "\n";
		icmpDetails += "  Checksum: "+ myIcmpPacket.getChecksum() + "\n";
		icmpDetails += "  Identifier (BE): "+ myIcmpPacket.getIdentifierBE() + "\n";
		icmpDetails += "  Identifier (LE): "+ myIcmpPacket.getIdentifierLE() + "\n";
		icmpDetails += "  Sequence Numer (BE): "+ myIcmpPacket.getSequenceNumberBE() + "\n";
		icmpDetails += "  Sequence Number (LE): "+ myIcmpPacket.getSequenceNumberLE() + "\n";
		icmpDetails += "  Data: "+ myIcmpPacket.getData() + "\n";
		return getIpv4Details(myIcmpPacket) + icmpDetails;
	}
	
	public String getDhcpDetails(IMyDhcpPacket myDhcpPacket){
		String dhcpDetails = "Bootstrap Protocol:\n";
		dhcpDetails += "  Message type: "+ myDhcpPacket.getMessageType() + "\n";
		dhcpDetails += "  Hardware type: "+ myDhcpPacket.getHardwareType() + "\n";
		dhcpDetails += "  Hardware address length: "+ myDhcpPacket.getHardwareAddressLength() + "\n";
		dhcpDetails += "  Hops: "+ myDhcpPacket.getHops() + "\n";
		dhcpDetails += "  Transaction ID: "+ myDhcpPacket.getTransactionID() + "\n";
		dhcpDetails += "  Seconds elapsed: "+ myDhcpPacket.getSecondsElapsed() + "\n";
		dhcpDetails += "  Bootp flags: "+ myDhcpPacket.getBootpFlags() + "\n";
		dhcpDetails += "  Client IP address: "+ myDhcpPacket.getClientIPAddress() + "\n";
		dhcpDetails += "  Your (client) IP address: "+ myDhcpPacket.getYourClientIPAddress() + "\n";
		dhcpDetails += "  Next server IP address: "+ myDhcpPacket.getNextServerIPAddress() + "\n";
		dhcpDetails += "  Relay agent IP address: "+ myDhcpPacket.getRelayAgentIPAddress() + "\n";
		dhcpDetails += "  Client MAC address: "+ myDhcpPacket.getClientMacAddress() + "\n";
		dhcpDetails += "  Client hardware address padding: "+ myDhcpPacket.getClientHardwareAddressPadding() + "\n";
		dhcpDetails += "  Server host name: "+ myDhcpPacket.getServerHostname() + "\n";
		dhcpDetails += "  Boot file name: "+ myDhcpPacket.getBootFilename() + "\n";
		dhcpDetails += "  Magic cookie: "+ myDhcpPacket.getMagicCookie() + "\n";
		//dhcpDetails += "  Options: "+ myDhcpPacket.getOptions() + "\n";
		return getUdpDetails(myDhcpPacket) + dhcpDetails;
	}
	
	public String getHttpDetails(IMyHttpPacket myHttpPacket){
		String httpDetails = "Hyper Text Transfer Protocol:\n";
		
		if(myHttpPacket.getStatusCode() == 0)
		{
			httpDetails += " Request Method: " + myHttpPacket.getRequestMethod()+ "\n";
			httpDetails += " Request URI: " +  myHttpPacket.getRequestURL()+ "\n";
		}
		httpDetails += " Request Version: " +  myHttpPacket.getRequestVersion()+ "\n";
		if(myHttpPacket.getStatusCode() != 0)
			httpDetails += " Status Code: " +  myHttpPacket.getStatusCode()+ "\n";
		
		Map<String, String> headers = myHttpPacket.getHeader();
		
		for(String key : headers.keySet())
	    {
			httpDetails += " " + key + ": " + headers.get(key) + "\n";
	    }
			
		return getTcpDetails(myHttpPacket) + httpDetails;
	}
}
