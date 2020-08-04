package com.biostime.bp.authorization.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biostime.bp.authorization.domain.role.BpaRole;
import com.biostime.bp.authorization.repository.role.BpaRoleMapper;
import com.biostime.bp.authorization.service.role.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private BpaRoleMapper bpaRoleMapper;
	
	@Override
	public List<BpaRole> getUserRolesByUidAndAppCode(Long uid, String appCode) {
		return bpaRoleMapper.queryUserRolesByUidAndAppCode(uid,appCode);
	}

}
