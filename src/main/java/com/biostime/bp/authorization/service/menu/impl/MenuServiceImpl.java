package com.biostime.bp.authorization.service.menu.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biostime.bp.authorization.bean.menu.MenuBo;
import com.biostime.bp.authorization.bean.menu.MenuVo;
import com.biostime.bp.authorization.common.MenuConstans;
import com.biostime.bp.authorization.domain.role.BpaRole;
import com.biostime.bp.authorization.repository.resource.BpaCatalogueMapper;
import com.biostime.bp.authorization.service.menu.MenuService;
import com.biostime.bp.authorization.util.LanguageHelper;



@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private BpaCatalogueMapper bpaCatalogueMapper;

	@Override
	public Set<MenuVo> queryUserMenuInfoByAppCodeAndRole(List<BpaRole> userRoles, String appCode) {
		Set<MenuVo> roots = new LinkedHashSet<>();
		List<MenuBo> list = bpaCatalogueMapper.queryUserMenuInfoByAppCodeAndRole(appCode,userRoles,LanguageHelper.getLocaleFromRequest().toString());
		if(CollectionUtils.isNotEmpty(list)) {
			Map<Long,MenuVo> map = new LinkedHashMap<Long, MenuVo>();
			convertMenuTreeMap(map,list,roots);
			generateMenuTree(roots,map);
		}
		return roots;
	}
	

	public void convertMenuTreeMap(Map<Long,MenuVo> map,List<MenuBo> list,Set<MenuVo> roots){
		for(MenuBo menuBo:list){
			MenuVo menuVo = new MenuVo();
			menuVo.setId(menuBo.getId());
			menuVo.setName(menuBo.getName());
			menuVo.setUrl(menuBo.getUrl());
			menuVo.setParentId(menuBo.getParentId());
			menuVo.setIconUrl(menuBo.getIconUrl());
			menuVo.setStatus(menuBo.getStatus());
			menuVo.setWeight(menuBo.getWeight());
			if(menuVo.getId()!=null){
				map.put(menuVo.getId(), menuVo);
			}
			if(menuVo.getParentId()!=null&&menuVo.getParentId().equals(MenuConstans.ROOT_PARENT_ID)){
				roots.add(menuVo);
			}
			
		}
	}
	
	public void generateMenuTree(Set<MenuVo> roots, Map<Long, MenuVo> map) {
		for (MenuVo root : roots) {
			for (Entry<Long, MenuVo> entry : map.entrySet()) {
				MenuVo menuVo = entry.getValue();
				if (menuVo.getParentId() != null && menuVo.getParentId().equals(root.getId())) {
					root.getChildren().add(menuVo);
					root.setHasChildren(true);
					continue;
				}
				MenuVo parentMenuVo = map.get(menuVo.getParentId());
				if (parentMenuVo != null) {
					parentMenuVo.getChildren().add(menuVo);
					parentMenuVo.setHasChildren(true);
				}
			}
		}
	}




}
