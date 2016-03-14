package br.com.pcontop.springTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Deprecated
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestHomeController {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).
				build();
	}
	
	@Test
	public void ping() throws Exception {
		this.mockMvc.perform(get("/ping"))
		.andDo(print())
		.andExpect(status().isOk())
	    .andExpect(content().string("ok"));
	}
	
	@Test
	public void put() throws Exception {
		SimpleBean simpleBean = new SimpleBean();
		simpleBean.setText("AAA");
		simpleBean.setValue(9);
		
		this.mockMvc.perform(
				post("/simpleBeanD")
				.contentType(IntegrationUtil.APPLICATION_JSON_UTF8)
                .content(IntegrationUtil.convertObjectToJsonBytes(simpleBean))
                )
		.andExpect(status().isOk());
	}

}