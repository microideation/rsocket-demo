package com.microideation.app.rsocketdemo.rsocketdemo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarterConfig implements ApplicationListener<ApplicationReadyEvent> {
	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		
		Server server = new Server();
	}
}
