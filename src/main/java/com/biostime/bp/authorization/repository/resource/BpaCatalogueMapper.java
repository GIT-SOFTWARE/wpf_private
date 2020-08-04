package com.biostime.bp.authorization.repository.resource;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biostime.bp.authorization.bean.menu.MenuBo;
import com.biostime.bp.authorization.domain.resource.BpaCatalogue;
import com.biostime.bp.authorization.domain.role.BpaRole;

import tk.mybatis.mapper.common.Mapper;

public interface BpaCatalogueMapper extends Mapper<BpaCatalogue> {
	List<MenuBo> queryUserMenuInfoByAppCodeAndRole(@Param("appCode")String appCode, @Param("userRoles")List<BpaRole> userRoles,@Param("languageType") String languageType);
}