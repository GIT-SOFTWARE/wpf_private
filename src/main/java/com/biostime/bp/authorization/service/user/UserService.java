package com.biostime.bp.authorization.service.user;

import java.util.List;
import java.util.Set;

import com.biostime.bp.authorization.bean.function.FunctionPermissionVo;
import com.biostime.bp.authorization.bean.menu.MenuVo;
import com.biostime.bp.authorization.bean.role.RoleVo;
import com.biostime.bp.authorization.bean.user.UserInfoVo;
import com.biostime.bp.authorization.bean.user.param.GetUserInfoParam;
import com.biostime.bp.authorization.bean.user.param.SearchUserInfoParam;
import com.biostime.bp.authorization.common.OperateType;
import com.biostime.bp.authorization.domain.user.BpaUser;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public UserInfoVo getUserInfo(GetUserInfoParam param);
	
	public BpaUser queryBpaUserByUid(Long uid);

	public Set<MenuVo> getUserMenuInfo(String appCode);

	public List<RoleVo> getUserRoleList(String appCode);

	public List<FunctionPermissionVo> getUserFunctionPermission(String relationId);
	
	public PageInfo<UserInfoVo> searchUserInfo(SearchUserInfoParam param);

	public void updateUser(BpaUser user, OperateType operateType, Long operater);
	
	public BpaUser queryBpaUserByAccount(String account);
	public BpaUser queryBpaUserByEmail(String email);
}
