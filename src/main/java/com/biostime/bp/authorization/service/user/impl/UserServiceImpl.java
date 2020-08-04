package com.biostime.bp.authorization.service.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.biostime.bp.authorization.bean.function.FunctionPermissionVo;
import com.biostime.bp.authorization.bean.menu.MenuVo;
import com.biostime.bp.authorization.bean.role.RoleVo;
import com.biostime.bp.authorization.bean.user.UserInfoVo;
import com.biostime.bp.authorization.bean.user.param.GetUserInfoParam;
import com.biostime.bp.authorization.bean.user.param.SearchUserInfoParam;
import com.biostime.bp.authorization.common.AccountType;
import com.biostime.bp.authorization.common.GlobalVariable;
import com.biostime.bp.authorization.common.OperateType;
import com.biostime.bp.authorization.domain.role.BpaRole;
import com.biostime.bp.authorization.domain.user.BpaUser;
import com.biostime.bp.authorization.exception.BusinessException;
import com.biostime.bp.authorization.repository.user.BpaUserMapper;
import com.biostime.bp.authorization.service.function.FunctionService;
import com.biostime.bp.authorization.service.log.OperateLogService;
import com.biostime.bp.authorization.service.menu.MenuService;
import com.biostime.bp.authorization.service.role.RoleService;
import com.biostime.bp.authorization.service.user.UserService;
import com.biostime.bp.authorization.util.BeanUtils;
import com.biostime.jwt.client.bpa.bean.user.JwtUserInfo;
import com.biostime.jwt.client.bpa.util.ClientUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;



@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private BpaUserMapper bpaUserMapper;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private FunctionService functionService;
	
	
	@Override
	public UserInfoVo getUserInfo(GetUserInfoParam param) {
		JwtUserInfo jwtUserInfo = ClientUtil.getLoginUser();
		BpaUser bpaUser = queryBpaUserByUid(jwtUserInfo.getUid());
		if(bpaUser==null) {
			throw new BusinessException("400");
		}
		UserInfoVo userInfo = new UserInfoVo();
		BeanUtils.copyProperties(bpaUser,userInfo);
		return userInfo;
	}
	
	@Override
	public BpaUser queryBpaUserByUid(Long uid) {
		Example exa = new Example(BpaUser.class);
		Criteria cia = exa.createCriteria();
		cia.andEqualTo("uid", uid);
		cia.andIn("status",Arrays.asList(GlobalVariable.STATUS_ENABLE_INT,GlobalVariable.STATUS_DISABLE_INT));
		return bpaUserMapper.selectOneByExample(exa);
	}

	@Override
	public Set<MenuVo> getUserMenuInfo(String appCode) {
		JwtUserInfo jwtUserInfo = ClientUtil.getLoginUser();
		if(StringUtils.isBlank(appCode)){
			appCode = jwtUserInfo.getAppCode();
		}
		List<BpaRole> userRoles = roleService.getUserRolesByUidAndAppCode(jwtUserInfo.getUid(),appCode); 
		if(CollectionUtils.isEmpty(userRoles)){
			throw new BusinessException("402");
		}
		Set<MenuVo> menus = menuService.queryUserMenuInfoByAppCodeAndRole(userRoles,appCode);
		if(CollectionUtils.isEmpty(menus)) {
			throw new BusinessException("403");
		}
		return menus;
	}

	@Override
	public List<RoleVo> getUserRoleList(String appCode) {
		List<RoleVo> roles = new ArrayList<RoleVo>();
		JwtUserInfo jwtUserInfo = ClientUtil.getLoginUser();
		if(StringUtils.isBlank(appCode)){
			appCode = jwtUserInfo.getAppCode();
		}
		List<BpaRole> userRoles = roleService.getUserRolesByUidAndAppCode(jwtUserInfo.getUid(),appCode); 
		if(CollectionUtils.isNotEmpty(userRoles)) {
			for(BpaRole bpaRole:userRoles){
				RoleVo vo = new RoleVo();
				BeanUtils.copyProperties(bpaRole, vo);
				roles.add(vo);
			}
		}
		return roles;
	}


	@Override
	public List<FunctionPermissionVo> getUserFunctionPermission(String relationId) {
		JwtUserInfo jwtUserInfo = ClientUtil.getLoginUser();
		List<BpaRole> userRoles = roleService.getUserRolesByUidAndAppCode(jwtUserInfo.getUid(),jwtUserInfo.getAppCode());
		return functionService.getUserFunctionPermission(jwtUserInfo,relationId,userRoles);
	}

	@Override
	public PageInfo<UserInfoVo> searchUserInfo(SearchUserInfoParam param) {
		Example ex = new Example(BpaUser.class);
		ex.orderBy("createTime").desc();
		Criteria criteria = ex.createCriteria();
		RowBounds row = null;
		if (null != param) {
			if (!StringUtils.isBlank(param.getUserName())) {
				criteria.andLike("account", "%" + param.getUserName() + "%");
			}
			if (!StringUtils.isBlank(param.getNickName())) {
				criteria.andLike("personName", "%" + param.getNickName() + "%");
			}
			if(StringUtils.isNotBlank(param.getAccount())) {
				criteria.andEqualTo("account",param.getAccount());				
			}
			if (null != param.getStatus() && param.getStatus().size() > 0) {
				List<Integer> userStatus = new ArrayList<> ();
				param.getStatus().forEach(s->{
					switch (s) {
						case 0 :
						case 1 :
							userStatus.add(s);
							break;
						default :
							break;
					}
				});
				if (!param.getStatus().contains(-1) && userStatus.size() > 0) {
					criteria.andIn("status", userStatus);
				}
				if (param.getStatus().contains(2)) {
					criteria.andEqualTo("isLock", UserInfoVo.IsLock.LOCKED.getValue());
				}
			}
			row = new RowBounds(param.getPageSize() * (param.getPageNum() - 1), param.getPageSize());
		}
		row = null == row ? new RowBounds(0, 20) : row;
		
		//默认过滤掉 cilent账户
		criteria.andNotEqualTo("accountType", AccountType.CLIENT.getType());
		
		Page<BpaUser> data = (Page<BpaUser>) bpaUserMapper.selectByExampleAndRowBounds(ex, row);

		List<UserInfoVo> result = new ArrayList<>();
		data.stream().forEach(u -> {
			UserInfoVo uv = new UserInfoVo();
			BeanUtils.copyProperties(u, uv);
			result.add(uv);
		});
		PageInfo<UserInfoVo> list = new PageInfo<UserInfoVo>(result);
		list.setTotal(bpaUserMapper.selectCountByExample(ex));
		list.setPageNum(param.getPageNum());
		list.setPageSize(param.getPageSize());
		
		return list;
	}

	@Override
	@Transactional
	public void updateUser(BpaUser user, OperateType operateType, Long operater) {
		bpaUserMapper.updateByPrimaryKeySelective(user);
		operateLogService.logOperateLog(operateType, user, null, operater);
	}

	@Override
	public BpaUser queryBpaUserByAccount(String account) {
		Example ex = new Example(BpaUser.class);
		Criteria criteria = ex.createCriteria();
		criteria.andEqualTo("account", account);
		return bpaUserMapper.selectOneByExample(ex);
	}

	@Override
	public BpaUser queryBpaUserByEmail(String email) {
		BpaUser user = null;
		Example ex = new Example(BpaUser.class);
		Criteria criteria = ex.createCriteria();
		criteria.andEqualTo("email", email);
		List<BpaUser> list = bpaUserMapper.selectByExample(ex);
		if(CollectionUtils.isNotEmpty(list)) {
			 user = list.get(0);
		}
		return user;
	}
}
