package com.biostime.bp.authorization.service.function.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biostime.bp.authorization.bean.function.FunctionPermissionVo;
import com.biostime.bp.authorization.domain.role.BpaRole;
import com.biostime.bp.authorization.repository.resource.BpaFunctionMapper;
import com.biostime.bp.authorization.service.function.FunctionService;
import com.biostime.jwt.client.bpa.bean.user.JwtUserInfo;

@Service
public class FunctionServiceImpl implements FunctionService{
	
	@Autowired
	private BpaFunctionMapper bpaFunctionMapper;
	

	@Override
	public List<FunctionPermissionVo> getUserFunctionPermission(JwtUserInfo jwtUserInfo, String relationId,
			List<BpaRole> userRoles) {
		return bpaFunctionMapper.queryFunctionPermissionByRelationIdAndRole(relationId,jwtUserInfo.getAppCode(),userRoles);
	}

}
