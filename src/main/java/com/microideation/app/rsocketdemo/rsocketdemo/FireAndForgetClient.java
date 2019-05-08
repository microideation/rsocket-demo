package com.microideation.app.rsocketdemo.rsocketdemo;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

public class FireAndForgetClient {
	
	private final RSocket socket;

	public FireAndForgetClient() {
		
		this.socket = RSocketFactory
				.connect()
				.transport(TcpClientTransport.create("localhost",7000))
				.start()
				.block();
		
	}
	
	public void sendData(String data) {
	
		socket.fireAndForget(DefaultPayload.create(data)).block();
		
	}
	
	
}
