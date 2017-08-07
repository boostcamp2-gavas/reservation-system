package kgw.reservation.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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
import kgw.reservation.security.ReservationFormArgumentResolver;
import kgw.reservation.security.UserArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kgw.reservation.controller"})
public class ServletContextConfig extends WebMvcConfigurerAdapter {
		@Value("${naverest.imageMaxSize}")
		private Long imageMaxSize;
		@Value("${naverest.imagePath}")
		private String imagePath;
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
			   registry.addResourceHandler("/imgresources/**").addResourceLocations(imagePath);
			   registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");

			   								
		}
	    @Bean
	    public MultipartResolver multipartResolver() {
	        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
	        multipartResolver.setMaxUploadSize(imageMaxSize); // 1024 * 1024  = 1MB;
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
	        registry.addInterceptor(loginCheckInterceptor()).addPathPatterns("/users/**","/blog/**", "/products/reservation/**"
	        		, "/reviews/form");
	    }
	    
	    @Override
	    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	        argumentResolvers.add(new ReservationFormArgumentResolver());
	        argumentResolvers.add(new UserArgumentResolver());

	    }



}
