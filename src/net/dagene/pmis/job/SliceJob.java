package net.dagene.pmis.job;

import net.dagene.pmis.pathology.service.CsltService;

import org.springframework.beans.factory.annotation.Autowired;

public class SliceJob {
	@Autowired
	CsltService csltService;
	public void doCreateProgramFromMySQL() throws Exception{
		csltService.createProgramFromMySQL();
	}

}
