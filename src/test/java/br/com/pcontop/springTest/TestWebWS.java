package br.com.pcontop.springTest;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Deprecated
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class TestWebWS {
	
	private WebTarget basicTarget;

	@Value("${local.server.port}")   
	  int port;

	
	@Before
	public void setup(){
        Client client = ClientBuilder.newClient();
        basicTarget = client.target("http://127.0.0.1:" + port + "/control");

	}
	
	@Test
	public void testPing(){
		String result = basicTarget.path("ping").request().get(String.class);
		assertEquals(result, "ok");
	}
	


}
