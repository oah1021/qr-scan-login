package com.oah.qrscanlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author JUNHAO
 * @date 2023/2/6
 * @Description TODO
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter getCorsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 哪些方法可以跨域，比如：GET,POST （多个方法中间以逗号分隔）
        corsConfiguration.addAllowedMethod("*");
        // 允许哪个请求进行跨域 * 表示所有，可以具体指定 http://localhost:8601表示只允许http://localhost:8601跨域
        corsConfiguration.addAllowedOriginPattern("*");
        // 所有头信息全部放行
        corsConfiguration.addAllowedHeader("*");
        // 允许跨域发送 cookie
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // 哪些路径可以跨域
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}