package com.biostime.bp.authorization.controller.login;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biostime.bp.authorization.bean.base.BaseResponse;
import com.biostime.bp.authorization.bean.login.JwtAccessTokenVo;
import com.biostime.bp.authorization.bean.login.param.LoginParam;
import com.biostime.bp.authorization.common.GlobalVariable;
import com.biostime.bp.authorization.common.VerifyType;
import com.biostime.bp.authorization.exception.BusinessException;
import com.biostime.bp.authorization.service.login.LoginService;
import com.biostime.bp.authorization.util.RandomValidateCodeUtil;
import com.biostime.bp.authorization.util.RedisUtil;

@Slf4j
@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private RedisUtil redisUtil;
	
	
	@PostMapping("/login")
	public BaseResponse<JwtAccessTokenVo> login(@RequestBody @Valid LoginParam param){
		return BaseResponse.buildSuccessResp(loginService.login(param));
	}
	
	@GetMapping("/verify/{type}/{verifySeq}")
	public void verify(HttpServletRequest request, HttpServletResponse response,@PathVariable(required=true,value="type")int type,@PathVariable(required=true,value="verifySeq")String verifySeq) {
		try {
			if(StringUtils.isBlank(verifySeq)||verifySeq.length()>18||verifySeq.length()<8){
				throw new BusinessException("208");
			}
			String key = null;
			if(type==VerifyType.RESET_PASSWORD.getType()) {
				key = String.format(GlobalVariable.REDIS_KEY_REPWD_VALIDATECODE, verifySeq);
			}else if(type==VerifyType.LOGIN.getType()) {
				key = String.format(GlobalVariable.REDIS_KEY_LOGIN_VALIDATECODE, verifySeq);
			}else {
				throw new BusinessException("207");
			}
			RandomValidateCodeUtil codeUtil = new RandomValidateCodeUtil();
			BufferedImage image = null;
			String code = null;
			if (redisUtil.exists(key)) {
				String verifyCode = String.valueOf(redisUtil.get(key));
				if (StringUtils.isNotBlank(verifyCode)) {
					image = codeUtil.randImage(verifyCode);
					code = codeUtil.getRandCode();
				}
			} else {
				image = codeUtil.randImage();
				code = codeUtil.getRandCode();
				redisUtil.set(key, code, 600L);
			}
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (BusinessException e) {
			log.error("get verify code BusinessException error:{}", e.toString());
			throw e;
		}catch (Exception e) {
			log.error("get verify code error:{}", e.toString());
		}
	}
	

}
