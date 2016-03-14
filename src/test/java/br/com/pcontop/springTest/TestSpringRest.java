package br.com.pcontop.springTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class TestSpringRest {

	@Value("${local.server.port}")   
	int port;
	
	private RestTemplate restTemplate;
	private String startUri;
	
	@Before
	public void config(){
		restTemplate = new RestTemplate();
		startUri = "http://localhost:" + this.port;
	}
	
	
	@Test 
	public void testPing(){
		String body = restTemplate
				.getForObject(startUri + "/ping", String.class);
		assertEquals(body, "ok");

	}
	
	@Test
	public void testPut(){
		SimpleBean simpleBean = new SimpleBean();
		simpleBean.setText("aaa");
		simpleBean.setValue(99);
		SimpleBean simpleBeanReturned = restTemplate.postForEntity(startUri + "/simplePutD", simpleBean, SimpleBean.class).getBody();
		assertNotNull(simpleBeanReturned);
	}
	
	@Test 
	public void testGet(){
		
		SimpleBean simpleBean = restTemplate.getForEntity(startUri + "/get/99", SimpleBean.class).getBody();
		assertNotNull(simpleBean);
		assertEquals(simpleBean.getValue(), 99);
	}

}
