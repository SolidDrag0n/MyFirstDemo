package com.sony.mts.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 * 
 * @author 黄龙
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	// 静态资源与静态页面地址
	private static final String[] STATIC_RESOURCE_LOCATIONS = { "/", "/login/detail", "/static/**", "/css/**",
			"/img/**", "/js/**", "/error/**" };

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 配置拦截器的作用路径，放行静态资源与页面
		 */
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
				.excludePathPatterns(STATIC_RESOURCE_LOCATIONS);
	}

}
