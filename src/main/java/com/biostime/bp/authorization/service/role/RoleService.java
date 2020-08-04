package com.biostime.bp.authorization.service.role;

import java.util.List;

import com.biostime.bp.authorization.domain.role.BpaRole;

public interface RoleService {
	
	/**
	 * 查询用户拥有的有效角色
	 * @param uid
	 * @param appCode
	 * @return
	 */
	List<BpaRole> getUserRolesByUidAndAppCode(Long uid, String appCode);

}
