package net.dagene.pmis.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;


@WebService
public interface PMISWebService {	
	public String recResult(@WebParam(name="text") String text); 
}
