package net.dagene.microbe.mapper;

import java.util.List;

import net.dagene.microbe.vo.QueryMicrobeParamVo;
import net.dagene.microbe.vo.QueryMicrobeResultVo;

public interface MicrobeMapper {
	public List<QueryMicrobeResultVo> queryMicrobeResult(QueryMicrobeParamVo queryMicrobeParamVo) throws Exception;
}
