package com.biostime.bp.authorization.service.function;

import java.util.List;

import com.biostime.bp.authorization.bean.function.FunctionPermissionVo;
import com.biostime.bp.authorization.domain.role.BpaRole;
import com.biostime.jwt.client.bpa.bean.user.JwtUserInfo;

public interface FunctionService {


	List<FunctionPermissionVo> getUserFunctionPermission(JwtUserInfo jwtUserInfo, String relationId,
			List<BpaRole> userRoles);

}
