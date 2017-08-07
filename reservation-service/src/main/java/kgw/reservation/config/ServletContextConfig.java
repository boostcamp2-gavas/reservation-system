package kgw.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import kgw.reservation.interceptor.LoginCheckInterceptor;
import kgw.reservation.oauth.naver.NaverApiBO;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kgw.reservation.controller"})
public class ServletContextConfig extends WebMvcConfigurerAdapter {
		@Bean
	    public ViewResolver viewResolver() {
	         InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/views/");
	        viewResolver.setSuffix(".jsp");
	        return viewResolver;
	    }
		@Override
		   public void addResourceHandlers(ResourceHandlerRegistry registry) {
		       registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");  //   webapp/resources 경로를 의미
		}
	    @Bean
	    public MultipartResolver multipartResolver() {
	        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
	        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
	        return multipartResolver;
	    }
	    @Bean
	    public NaverApiBO naverApiBO() {
	    		return new NaverApiBO();
	    }
	    @Bean
	    LoginCheckInterceptor loginCheckInterceptor() {
	         return new LoginCheckInterceptor(naverApiBO());
	    }
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(loginCheckInterceptor()).addPathPatterns("/users/**","/blog/**", "/products/reservation/**");
	    }


}
