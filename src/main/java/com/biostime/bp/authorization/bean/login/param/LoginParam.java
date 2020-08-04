package com.biostime.bp.authorization.bean.login.param;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginParam {
	@NotBlank(message="308")
	private String password;
	@NotBlank(message="309")
	private String account;
	@NotBlank(message="310")
	private String appCode;
	@NotBlank(message="311")
	private String validateCode;
	@NotBlank(message="201")
	private String verifySeq;

}
