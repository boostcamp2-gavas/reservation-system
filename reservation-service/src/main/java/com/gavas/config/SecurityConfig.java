package com.gavas.config;

import com.gavas.security.UserDetailsServiceImpl;
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
@ComponentScan(basePackages = "com.gavas.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private ApplicationContext context;
    private OAuth2ClientContextFilter oauth2ClientContextFilter;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(ApplicationContext context,
                          OAuth2ClientContextFilter oauth2ClientContextFilter,
                          UserDetailsServiceImpl userDetailsService) {
        this.context = context;
        this.oauth2ClientContextFilter = oauth2ClientContextFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/");
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
        http
                .csrf()
                .disable()
                .formLogin()
                .loginProcessingUrl("/logina")
                .and()
                .exceptionHandling()
                .and()
                .addFilterAfter(oauth2ClientContextFilter,
                        ExceptionTranslationFilter.class)
                .addFilterBefore((Filter) context.getBean("sso.filter"),
                        FilterSecurityInterceptor.class)
                .anonymous()
                .disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
    }
}
