package com.biostime.bp.authorization.repository.role;

import java.util.List;

import com.biostime.bp.authorization.domain.role.BpaRole;
import tk.mybatis.mapper.common.Mapper;

public interface BpaRoleMapper extends Mapper<BpaRole> {
	List<BpaRole> queryUserRolesByUidAndAppCode(Long uid, String appCode);
}