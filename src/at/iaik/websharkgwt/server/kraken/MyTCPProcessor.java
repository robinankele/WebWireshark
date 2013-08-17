package at.iaik.websharkgwt.server.kraken;

import org.krakenapps.pcap.decoder.tcp.DefaultTcpProcessor;
import org.krakenapps.pcap.decoder.tcp.TcpProcessor;
import org.krakenapps.pcap.decoder.tcp.TcpSessionKey;
import org.krakenapps.pcap.util.Buffer;

public class MyTCPProcessor extends DefaultTcpProcessor implements TcpProcessor {

	protected String sessionID;
	protected boolean addToResults;
	
	public MyTCPProcessor(String sessionID,boolean addToResults) {
		this.sessionID = sessionID;
		this.addToResults = addToResults;
	}

	@Override
	public void handleRx(TcpSessionKey session, Buffer data) {
		super.handleRx(session, data);
	}

	@Override
	public void handleTx(TcpSessionKey session, Buffer data) {
		super.handleTx(session, data);
	}

	@Override
	public void onEstablish(TcpSessionKey key) {
		super.onEstablish(key);
	}

	@Override
	public void onFinish(TcpSessionKey key) {
		super.onFinish(key);
	}

	@Override
	public void onReset(TcpSessionKey key) {
		super.onReset(key);
	}
	
	
	
	

}
