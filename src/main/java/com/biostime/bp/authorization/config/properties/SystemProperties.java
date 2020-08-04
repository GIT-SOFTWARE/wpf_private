package com.biostime.bp.authorization.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "hh.bp.authorization")
@Data
@Component
public class SystemProperties {
	
	/**
	 * 用户密码允许错误次数
	 */
	private int userPasswordAllowErrorCount;
}
