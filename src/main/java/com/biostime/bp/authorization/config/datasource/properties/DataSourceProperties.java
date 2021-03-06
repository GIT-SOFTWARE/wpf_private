package com.biostime.bp.authorization.config.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 
* 类功能描述: TODO
*
* @version 1.0
* @author 1394
 */
@ConfigurationProperties(prefix="spring.datasource")
public class DataSourceProperties {
	
    private String url;
    
    private String username;
    
    private String password;
    
    private String driverClassName;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    @Override
    public String toString()
    {
        return "DataSourceProperties [url="
                + url + ", username=" + username + ", password=" + password + ", driverClassName=" + driverClassName + "]";
    }
    
  
}
