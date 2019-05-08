package com.microideation.app.rsocketdemo.rsocketdemo;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Slf4j
public class Server {
	
	private final Disposable server;

	public Server() {
	
		this.server = RSocketFactory
				.receive()
				.acceptor((setupPayload,reactiveSocket) -> Mono.just(new RSocketImpl()))
				.transport(TcpServerTransport.create("127.0.0.1",7000))
				.start()
				.subscribe();
		
	}
	
	public void dispose() {
		this.server.dispose();
	}
	
	private class RSocketImpl extends AbstractRSocket{
		
		@Override
		public Mono<Payload> requestResponse(Payload payload) {
			try {

				log.info("requestResponse -> Received request: " + payload.getDataUtf8());
				Payload response = DefaultPayload.create("Hello " +payload.getDataUtf8() +"!");
				return Mono.just(response);
				
			} catch (Exception e ) {
				return Mono.error(e);
			}
		}
		
		@Override
		public Mono<Void> fireAndForget(Payload payload) {
			try {
			
				log.info("fireAndForget -> Received f&f event: " + payload.getDataUtf8());
				
			} catch (Exception e ) {
				e.printStackTrace();
			}
			
			return Mono.empty();
		}
	}
}
