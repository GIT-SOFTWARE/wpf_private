package com.biostime.bp.authorization.service.menu;

import java.util.List;
import java.util.Set;

import com.biostime.bp.authorization.bean.menu.MenuVo;
import com.biostime.bp.authorization.domain.role.BpaRole;

public interface MenuService {

	Set<MenuVo> queryUserMenuInfoByAppCodeAndRole(List<BpaRole> userRoles, String appCode);

}
