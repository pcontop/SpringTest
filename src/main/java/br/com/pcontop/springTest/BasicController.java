package br.com.pcontop.springTest;

import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Path("/control")
public class BasicController {
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping(){
		return "ok";
	}
	
	@RequestMapping(value = "/simplePutA", method = RequestMethod.POST)
	public SimpleBean simplePutA(){
		SimpleBean simpleBean = new SimpleBean();
		simpleBean.setText("aa");
		return simpleBean;
	}

	@RequestMapping(value = "/simplePutB", method = RequestMethod.POST)
	public SimpleBean simplePutB(@RequestParam(value="input") SimpleBean simpleBean){
		return simpleBean;
	}

	@RequestMapping(value = "/simplePutC", method = RequestMethod.POST)
	public String simplePutC(@RequestParam(value="input") String receive){
		return receive;
	}

	@RequestMapping(value = "/simplePutD", method = RequestMethod.POST)
	public SimpleBean simplePutD(@RequestBody SimpleBean receive){
		return receive;
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public SimpleBean simpleGet(@PathVariable("id") String id){
		SimpleBean simpleBean = new SimpleBean();
		simpleBean.setText("Automatic");
		simpleBean.setValue(Integer.parseInt(id));
		return simpleBean;
	}

}
