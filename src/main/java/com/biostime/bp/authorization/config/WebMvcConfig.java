package com.biostime.bp.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
    	registry.addMapping("/**") //映射地址
		.allowedOrigins("*")//允许跨域地址
		.allowedHeaders("*")
		.allowCredentials(true)
	    .allowedMethods("GET", "POST")
	    .maxAge(3600);
    }
    
    

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
	}
}