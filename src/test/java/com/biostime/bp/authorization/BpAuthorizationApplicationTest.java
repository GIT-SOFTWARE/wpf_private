package com.biostime.bp.authorization;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.test.context.junit4.SpringRunner;

import com.biostime.bp.authorization.bean.user.UserInfoVo;
import com.biostime.bp.authorization.domain.log.BpaOperateLog;
import com.biostime.bp.authorization.domain.resource.BpaFunction;
import com.biostime.bp.authorization.repository.log.BpaOperateLogMapper;
import com.biostime.bp.authorization.repository.resource.BpaFunctionMapper;
import com.biostime.bp.authorization.util.MailUtil;
import com.biostime.jwt.client.bpa.config.JwtOkHttpProperties;
import com.biostime.jwt.client.bpa.config.JwtProperties;
import com.biostime.jwt.client.bpa.config.JwtServerServiceProperties;
import com.biostime.jwt.client.bpa.service.user.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BpAuthorizationApplicationTest {

	private static final Object UserInfoVo = null;
	@Autowired
	private MailUtil mailUtil;

	/**
	 * 获取所有 内部人员 ou=Internal,ou=People
	 */
	@Test
	public void listUsers() {
		Map<String, String> data = new HashMap<>();
		data.put("toUser", "zenglin@hh.global");
		data.put("userName", "admin");
		data.put("url", "test");
		mailUtil.testSendEmail(data);
	/*	AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectClass", "person"));
		System.out.println(ldapTemplate);
		boolean authenticate = ldapTemplate.authenticate("", "(sAMAccountName=" + "1407300" + ")", "ssotest123");
		System.out.println("是否登录成功：" + authenticate); */
//查询所有内部人员
     /*   List<LdapUser> users = ldapTemplate.search("ou=Internal,ou=People", filter.encode(), new LdapUserAttributeMapper());
        for (LdapUser user: users ) {
            System.out.println(user);
        }*/
		// 查询所有内部人员

//    Assert.assertEquals(3056, users.size());
	}
	/*
	 * @Autowired private JwtOkHttpProperties jwtOkHttpProperties;
	 * 
	 * @Autowired private JwtServerServiceProperties jwtServerServiceProperties;
	 * 
	 * @Autowired private JwtProperties jwtProperties;
	 * 
	 * @Value("${jwt.client.okhttp.maxIdleConnections}") private String test;
	 */

	@Autowired
	private BpaFunctionMapper bpaFunctionMapper;

	@Autowired
	private BpaOperateLogMapper bpaOperateLogMapper;

	@Test
	public void test() {
		/*
		 * Long menuId = 3L; String appCode = "HH_GAS"; BpaFunction bpaFunction = new
		 * BpaFunction(); bpaFunction.setAppCode(appCode);
		 * bpaFunction.setFunctionCode("query"); bpaFunction.setName("查询");
		 * bpaFunction.setWeight(0); bpaFunction.setType(1);
		 * bpaFunction.setRelationId(menuId); bpaFunction.setStatus(1);
		 * bpaFunction.setCreateBy("system"); bpaFunction.setCreateTime(new Date());
		 * bpaFunctionMapper.insert(bpaFunction);
		 */

		/*
		 * BpaOperateLog log = new BpaOperateLog(); log.setOperateContent("测试日志插入");
		 * log.setOperateTime(new Date()); bpaOperateLogMapper.insert(log);
		 */
		/*
		 * UserInfoVo userInfoVo = null;
		 * 
		 * redisTemplate.opsForValue().set("hbatttttttttt",
		 * userInfoVo,60,TimeUnit.SECONDS);
		 * 
		 * Object object = redisTemplate.opsForValue().get("hbatttttttttt");
		 * if(object!=null) { UserInfoVo dd =(UserInfoVo)object; System.out.println(dd);
		 * }else { System.out.println("========="+object); }
		 */

		/*
		 * System.out.println("======="+test);
		 * System.out.println("======="+jwtProperties);
		 * System.out.println("======="+jwtOkHttpProperties);
		 * System.out.println("======="+jwtServerServiceProperties);
		 */
	}

}
