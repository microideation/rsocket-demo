package com.microideation.app.rsocketdemo.rsocketdemo;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReqResClient {
	
	private final RSocket socket;
	
	public ReqResClient() {
		this.socket = RSocketFactory
				.connect()
				.transport(TcpClientTransport.create("127.0.0.1",7000))
				.start()
				.block();
	}
	
	public String callBlocking(String string) {

		log.info("callBlocking -> Sending request: " + string);
		return socket.requestResponse(DefaultPayload.create(string)).map(Payload::getDataUtf8).block();
	
	}

	
	public void dispose() {
		socket.dispose();
	}
	
	
}
