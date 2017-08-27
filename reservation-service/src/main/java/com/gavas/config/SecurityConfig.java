package com.gavas.config;

import com.gavas.oauth.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@PropertySource("classpath:/application.properties")
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = "com.gavas.oauth")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    ApplicationContext context;

    @Autowired
    private OAuth2ClientContextFilter oauth2ClientContextFilter;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/logins");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                // 로그인 페이지 : 컨트롤러 매핑을 하지 않으면 기본 제공되는 로그인 페이지가 뜬다.
        .loginProcessingUrl("/logina");
//
        http.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint())
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//			 No need for form-based login or basic authentication
//			.and()
//				.formLogin()
//					.loginPage("/aa")
//					.loginProcessingUrl("/aa")
//			.and()
//				.httpBasic()
                .and()
                .addFilterAfter(
                        oauth2ClientContextFilter,
                        ExceptionTranslationFilter.class)
                .addFilterBefore(
                        (Filter)context.getBean("sso.filter"),
                        FilterSecurityInterceptor.class)
                .anonymous()
                .disable();

        http
                .logout()
                // /logout 을 호출할 경우 로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // 로그아웃이 성공했을 경우 이동할 페이지
                .logoutSuccessUrl("/");
    }
}
