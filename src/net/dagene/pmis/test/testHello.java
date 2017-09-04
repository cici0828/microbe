package net.dagene.pmis.test;

import static org.junit.Assert.*;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import net.dagene.pmis.webservice.PMISWebService;

public class testHello {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(PMISWebService.class);
		factory.setAddress("http://localhost:8080/ttt/ws/recResult?wsdl");
		PMISWebService greetingService = (PMISWebService) factory.create();
		System.out.println("invoke webservice...");
		System.out.println("message context is:"
				+ greetingService.recResult("fengwei"));
	}

}
