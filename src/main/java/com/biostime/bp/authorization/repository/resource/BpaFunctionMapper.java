package com.biostime.bp.authorization.repository.resource;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.biostime.bp.authorization.bean.function.FunctionPermissionVo;
import com.biostime.bp.authorization.domain.resource.BpaFunction;
import com.biostime.bp.authorization.domain.role.BpaRole;

import tk.mybatis.mapper.common.Mapper;

public interface BpaFunctionMapper extends Mapper<BpaFunction> {
	List<FunctionPermissionVo> queryFunctionPermissionByRelationIdAndRole(@Param("relationId") String relationId,@Param("appCode")String appCode,@Param("roles") List<BpaRole> userRoles);
}