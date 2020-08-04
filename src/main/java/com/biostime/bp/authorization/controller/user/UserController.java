package com.biostime.bp.authorization.controller.user;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biostime.bp.authorization.bean.base.BaseResponse;
import com.biostime.bp.authorization.bean.base.ResponseEnum;
import com.biostime.bp.authorization.bean.function.FunctionPermissionVo;
import com.biostime.bp.authorization.bean.menu.MenuVo;
import com.biostime.bp.authorization.bean.role.RoleVo;
import com.biostime.bp.authorization.bean.user.UserInfoVo;
import com.biostime.bp.authorization.bean.user.param.GetUserInfoParam;
import com.biostime.bp.authorization.bean.user.param.SearchUserInfoParam;
import com.biostime.bp.authorization.common.CommonValidator;
import com.biostime.bp.authorization.common.GlobalVariable;
import com.biostime.bp.authorization.common.OperateType;
import com.biostime.bp.authorization.domain.user.BpaUser;
import com.biostime.bp.authorization.exception.BusinessException;
import com.biostime.bp.authorization.service.user.UserService;
import com.biostime.bp.authorization.util.MD5Util;
import com.biostime.bp.authorization.util.MailUtil;
import com.biostime.bp.authorization.util.RedisUtil;
import com.biostime.jwt.client.bpa.bean.user.JwtUserInfo;
import com.biostime.jwt.client.bpa.util.ClientUtil;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/userInfo")
	public BaseResponse<UserInfoVo> getUserInfo(@RequestBody @Valid GetUserInfoParam param) {
		return BaseResponse.buildSuccessResp(userService.getUserInfo(param));
	}

	@GetMapping("/userMenuInfo")
	public BaseResponse<Set<MenuVo>> getUserMenuInfo(String appCode) {
		return BaseResponse.buildSuccessResp(userService.getUserMenuInfo(appCode));
	}

	@GetMapping("/userRoleList")
	public BaseResponse<List<RoleVo>> getUserRoleList(String appCode) {
		return BaseResponse.buildSuccessResp(userService.getUserRoleList(appCode));
	}

	@GetMapping("/function/permission")
	public BaseResponse<List<FunctionPermissionVo>> getUserFunctionPermission(String relationId) {
		if (relationId == null) {
			throw new BusinessException("201", "relationId");
		}
		return BaseResponse.buildSuccessResp(userService.getUserFunctionPermission(relationId));
	}
	
	@RequestMapping(value="/search",method= {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<PageInfo<UserInfoVo>> search(SearchUserInfoParam param) {
		if (null == param || CommonValidator.anyBlank(param.getSeqNo())) {
			return BaseResponse.error(param.getSeqNo(), ResponseEnum.INPUT_PARAM_ERROR);
		}
		return BaseResponse.success(param.getSeqNo(), userService.searchUserInfo(param));
	}

	/**
	 * 方法描述: <b>账户操作</b>
	 *
	 * @param custId
	 * @param operationType 1-停用,2-启用
	 * @return
	 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
	 * @createDate 2019年5月22日 上午10:36:29
	 */
	@GetMapping("/accountOperation")
	public BaseResponse<String> accountOperation(String seqNo, Long custId, int operationType) {
		if (CommonValidator.anyBlank(seqNo) || CommonValidator.anyNaEN(custId)) {
			return BaseResponse.error(seqNo, ResponseEnum.INPUT_PARAM_ERROR);
		}
		BpaUser user = userService.queryBpaUserByUid(custId);
		if (null == user) {
			return BaseResponse.error(seqNo, ResponseEnum.build(ResponseEnum.CUSTOMER_NOT_EXISTS, custId));
		}
		if (1 == operationType && UserInfoVo.Status.EFFECTIVE.getValue() != user.getStatus()) {
			return BaseResponse.error(seqNo, ResponseEnum.NOT_ALLOW_OPERATOR);
		}
		if (2 == operationType && UserInfoVo.Status.DISCONTINUE.getValue() != user.getStatus()
				&& UserInfoVo.IsLock.LOCKED.getValue() != user.getIsLock()) {
			return BaseResponse.error(seqNo, ResponseEnum.NOT_ALLOW_OPERATOR);
		}

		switch (operationType) {
			case 1 :
				user.setStatus(UserInfoVo.Status.DISCONTINUE.getValue());
				userService.updateUser(user, OperateType.DISCONTINUE_ACCOUNT, ClientUtil.getLoginUser().getUid());
				break;
			case 2 :
				user.setStatus(UserInfoVo.Status.EFFECTIVE.getValue());
				user.setIsLock(UserInfoVo.IsLock.UNLOCKED.getValue());
				userService.updateUser(user, OperateType.ENABLE_ACCOUNT, ClientUtil.getLoginUser().getUid());
				break;
			default :
				return BaseResponse.error(seqNo, ResponseEnum.NOT_ALLOW_OPERATOR);
		}

		return BaseResponse.success(seqNo, null);
	}

	@Autowired
	private MailUtil mailUtil;
	@GetMapping("/adminChangePwd")
	public BaseResponse<String> adminChangePwd(String seqNo, Long custId, String newPwd) {
		if (CommonValidator.anyBlank(seqNo, newPwd) || CommonValidator.anyNaEN(custId)) {
			return BaseResponse.error(seqNo, ResponseEnum.INPUT_PARAM_ERROR);
		}
		BpaUser user = userService.queryBpaUserByUid(custId);
		if (null == user) {
			return BaseResponse.error(seqNo, ResponseEnum.build(ResponseEnum.CUSTOMER_NOT_EXISTS, custId));
		}
		user.setPassword(MD5Util.md5(newPwd));
		user.setUpdateTime(new Date());
		JwtUserInfo userInfo = ClientUtil.getLoginUser();
		user.setUpdateBy(String.valueOf(userInfo.getUid()));
		userService.updateUser(user, OperateType.ADMIN_CHANGE_USER_PASSWORD, userInfo.getUid());
		return BaseResponse.success(seqNo, null);
	}

	@GetMapping("/changePwd")
	public BaseResponse<String> changePwd(String seqNo, String oldPwd, String newPwd) {
		if (CommonValidator.anyBlank(seqNo, oldPwd, newPwd)) {
			return BaseResponse.error(seqNo, ResponseEnum.INPUT_PARAM_ERROR);
		}
		JwtUserInfo userInfo = ClientUtil.getLoginUser();
		BpaUser user = userService.queryBpaUserByUid(userInfo.getUid());
		if (MD5Util.md5(oldPwd).equalsIgnoreCase(user.getPassword())) {
			user.setPassword(MD5Util.md5(newPwd));
			user.setUpdateTime(new Date());
			user.setUpdateBy(String.valueOf(userInfo.getUid()));
			userService.updateUser(user, OperateType.USER_CHANGE_PASSWORD, userInfo.getUid());
		} else {
			return BaseResponse.error(seqNo, ResponseEnum.ERROR_OLD_PWD);
		}
		return BaseResponse.success(seqNo, null);
	}

	@Autowired
	private RedisUtil redisUtil;
	@Value("${repwdEmail.expire}")
	private Long expire;
	@Value("${repwdEmail.url}")
	private String url;
	@GetMapping("/applyRePwd")
	public BaseResponse<String> applyRePwd(String seqNo, String userName, String email, String validateCode, String verifySeq) {
		if (CommonValidator.anyBlank(seqNo, userName, email, validateCode)) {
			return BaseResponse.error(seqNo, ResponseEnum.INPUT_PARAM_ERROR);
		}
		String verifySeqKey = String.format(GlobalVariable.REDIS_KEY_REPWD_VALIDATECODE, verifySeq);
		
		if (validateCode.equalsIgnoreCase(String.valueOf(redisUtil.get(verifySeqKey)))) {
			//验证码使用完毕后删除
			redisUtil.del(verifySeqKey);
			
			BpaUser user = userService.queryBpaUserByAccount(userName);
			if (null == user) {
				return BaseResponse.error(seqNo, ResponseEnum.INVALID_USER_MSG);
			}
			BpaUser userEmail = userService.queryBpaUserByEmail(email);
			if (null == userEmail) {
				return BaseResponse.error(seqNo, ResponseEnum.INVALID_EMAIL);
			}
			if (!userEmail.getEmail().equals(user.getEmail())) {
				return BaseResponse.error(seqNo, ResponseEnum.INVALID_USER_OR_EMAIL);
			}
			String token = MD5Util.md5(seqNo + System.currentTimeMillis());
			Map<String, Object> redisData = new HashMap<>();
			redisData.put("account", user.getAccount());
			redisData.put("token", token);
			String sysTime = String.valueOf(System.currentTimeMillis());
			redisUtil.hmset(String.format(GlobalVariable.REDIS_KEY_REPWD_EMAIL_TOKEN, sysTime), redisData, expire);
			//redisUtil.hmset(GlobalVariable.REDIS_KEY_REPWD_EMAIL_TOKEN, redisData, expire);
			Map<String, String> data = new HashMap<>();
			data.put("toUser", user.getEmail());
			data.put("userName", user.getPersonName());
			data.put("url", MessageFormat.format(url, token, sysTime));// TODO /#/login?changePwd=true&token=xxxx&verifySeq=xxx
			mailUtil.sendAntifake(data);
		} else {
			return BaseResponse.error(seqNo, ResponseEnum.VALIDATECODE_ERROR);
		}
		return BaseResponse.success(seqNo, null);
	}
	
	@GetMapping("/isRePwdEffective")
	public BaseResponse<Boolean> isRePWDEffective(String seqNo, String token, String verifySeq) {
		if (CommonValidator.anyBlank(seqNo, token, verifySeq)) {
			return BaseResponse.success(seqNo, false);
		}
		Map<Object, Object> map = redisUtil.hmget(String.format(GlobalVariable.REDIS_KEY_REPWD_EMAIL_TOKEN, verifySeq));
		if (null == map || null == map.get("token")) {
			return BaseResponse.success(seqNo, false);
		}
		return BaseResponse.success(seqNo, true);
	}

	@GetMapping("/rePwd")
	public BaseResponse<String> rePwd(String seqNo, String token, String newPwd, String verifySeq) {
		if (CommonValidator.anyBlank(seqNo, token, newPwd)) {
			return BaseResponse.error(seqNo, ResponseEnum.INPUT_PARAM_ERROR);
		}
		//Map<Object, Object> map = redisUtil.hmget(GlobalVariable.REDIS_KEY_REPWD_EMAIL_TOKEN);
		Map<Object, Object> map = redisUtil.hmget(String.format(GlobalVariable.REDIS_KEY_REPWD_EMAIL_TOKEN, verifySeq));
		if (null == map || !token.equals(String.valueOf(map.get("token")))) {
			return BaseResponse.error(seqNo, ResponseEnum.ERROR);
		} else {
			String account = String.valueOf(map.get("account"));
			BpaUser user = userService.queryBpaUserByAccount(account);
			newPwd.chars().anyMatch(c->Character.isDigit(c));
			newPwd.chars().anyMatch(c->Character.isLetter(c));
			if (newPwd.length() < 6
					|| !(newPwd.chars().anyMatch(c -> Character.isDigit(c)) && 
						newPwd.chars().anyMatch(c -> Character.isLetter(c)))) {
				return BaseResponse.error(seqNo, ResponseEnum.INVALID_PWD);
			}
			if (null != user) {
				user.setPassword(MD5Util.md5(newPwd));
				user.setUpdateTime(new Date());
				user.setUpdateBy(String.valueOf(user.getUid()));
				userService.updateUser(user, OperateType.USER_CHANGE_PASSWORD, user.getUid());
			} else {
				return BaseResponse.error(seqNo, ResponseEnum.ERROR);
			}
		}

		return BaseResponse.buildSuccessResp(null);
	}
}
