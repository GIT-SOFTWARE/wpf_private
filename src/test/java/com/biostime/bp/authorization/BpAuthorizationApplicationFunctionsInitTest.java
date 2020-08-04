package com.biostime.bp.authorization;


import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.biostime.bp.authorization.domain.permission.BpaPermission;
import com.biostime.bp.authorization.domain.permission.BpaPermissionUnifyRs;
import com.biostime.bp.authorization.domain.resource.BpaFunction;
import com.biostime.bp.authorization.domain.role.BpaRolePermissionRs;
import com.biostime.bp.authorization.repository.permission.BpaPermissionMapper;
import com.biostime.bp.authorization.repository.permission.BpaPermissionUnifyRsMapper;
import com.biostime.bp.authorization.repository.resource.BpaFunctionMapper;
import com.biostime.bp.authorization.repository.role.BpaRolePermissionRsMapper;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BpAuthorizationApplicationFunctionsInitTest {
	
	@Autowired
	private BpaFunctionMapper bpaFunctionMapper;
	
	@Autowired
	private BpaPermissionMapper bpaPermissionMapper;
	
	@Autowired
	private  BpaPermissionUnifyRsMapper  bpaPermissionUnifyRsMapper;
	
	@Autowired
	private BpaRolePermissionRsMapper bpaRolePermissionRsMapper;
	
	private static final Map<String,String> function = new HashMap<>();
	
	static {
		function.put("create", "新增");
		function.put("edit", "编辑");
		function.put("delete", "删除");
		function.put("query", "查询");
		function.put("modify", "修改");
		function.put("export", "导出");
		function.put("download", "下载");
		function.put("audit", "审核");
		function.put("see", "查看");
		function.put("distribution", "分配");
		function.put("changepwd", "修改密码");
		function.put("enable", "启用");
		function.put("disable", "停用");
	}
	
	@Test
	public void test() {
		Long menuId = 9L;
		String appCode = "HH_GAS";
		String[] functions = new String[] {"query","changepwd","enable","disable"};
		for(String key:functions) {
		    BpaFunction bpaFunction = new BpaFunction();
			bpaFunction.setAppCode(appCode);
			bpaFunction.setFunctionCode(key);
			bpaFunction.setName(function.get(key));
			bpaFunction.setWeight(0);
			bpaFunction.setType(1);
			bpaFunction.setRelationId(menuId);
			bpaFunction.setStatus(1);
			bpaFunction.setCreateBy("system");
			bpaFunction.setCreateTime(new Date());
			bpaFunctionMapper.insert(bpaFunction);
			bpaFunction.getId().toString();
			
			//创建权限
			BpaPermission bpaPermission = new  BpaPermission();
			bpaPermission.setAppCode(appCode);
			bpaPermission.setType(2);
			bpaPermission.setCreateBy("system");
			bpaPermission.setCreateTime(new Date());
			bpaPermissionMapper.insert(bpaPermission);
			
			//插入中间表
			BpaPermissionUnifyRs bpaPermissionUnifyRs = new BpaPermissionUnifyRs();
			bpaPermissionUnifyRs.setPermissionId(bpaPermission.getId());
			bpaPermissionUnifyRs.setType(2);
			bpaPermissionUnifyRs.setRelationId(bpaFunction.getId());
			bpaPermissionUnifyRsMapper.insert(bpaPermissionUnifyRs);
			
			//绑定角色id
			BpaRolePermissionRs bpaRolePermissionRs = new BpaRolePermissionRs();
			bpaRolePermissionRs.setPermissionId(bpaPermission.getId());
			bpaRolePermissionRs.setRoleId(1L);
			bpaRolePermissionRsMapper.insert(bpaRolePermissionRs);
			
		}
		
		
		
		
	}

}
