package net.dagene.microbe.service;

import java.util.List;

import net.dagene.microbe.vo.QueryMicrobeParamVo;
import net.dagene.microbe.vo.QueryMicrobeResultVo;

public interface MicrobeService {
	public String getResult(QueryMicrobeParamVo p) throws Exception;
}
