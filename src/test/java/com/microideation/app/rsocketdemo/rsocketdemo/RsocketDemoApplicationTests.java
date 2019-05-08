package com.microideation.app.rsocketdemo.rsocketdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RsocketDemoApplicationTests {

	@Test
	public void testRequestResponse() {
	
		ReqResClient client = new ReqResClient();
		String string = "John";
		String response = client.callBlocking(string);
		log.info("testRequestResponse : Response received: " + response);
	
	}
	
	@Test
	public void testFireAndForget() throws InterruptedException {
		
		FireAndForgetClient client = new FireAndForgetClient();
		String string = "John";
		client.sendData(string);
		
		// Wait for 5 seconds to have the server receieve and respond as otherwise
		// the session will be killed
		Thread.sleep(5000l);
		
		
	}
}
