package br.com.pcontop.springTest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

@Deprecated
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class TestWeb{

	 @Value("${local.server.port}")   
	  int port;
		 
	 @Before
	    public void setUp() {
	        RestAssured.port = port;
	    }

	@Test
	public void testCollectPing(){
		when().
		get("ping").
		then().
		statusCode(HttpStatus.SC_OK).
		contentType(ContentType.TEXT).
		body(is("ok"));
	}

	@Test
	public void simplePost(){
		 when()
		.post("simpleBeanA")
		.then()
		.statusCode(HttpStatus.SC_OK)
		.contentType(ContentType.JSON)
		.body("text",is("aa"));
		//System.out.println(post("simpleBeanA").getBody().as(SimpleBean.class).getText());
		 //System.out.println(post("simpleBeanA").asString());
		 	}
	
	@Test
	public void testSimpleBeanSend(){
		SimpleBean simpleBean = new SimpleBean();
		simpleBean.setText("AA");
		simpleBean.setValue(9);
		
		SimpleBean bean = with().
				body(simpleBean).
		post("simpleBeanD").getBody().as(SimpleBean.class);
		
		assertNotNull(bean);		
	}

	
	@Test
	public void testSimpleBeanSendString(){
			
		given().
		formParam("input", "AA").
		when().
		post("simpleBeanC").
		then().
		statusCode(HttpStatus.SC_OK)
		.body(is("AA"));
	}

}
