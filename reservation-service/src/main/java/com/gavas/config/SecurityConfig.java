package com.gavas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Client
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    ApplicationContext context;

//    @Autowired
//    PasswordEncoder passwordEncoder;

//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    FacebookAuthenticationProvider facebookAuthenticationProvider;

//    @Override
//    public void configure(WebSecurity web) throws Exception
//    {
//        // 예를들어 이런식으로 인증할것들을 풀어주는겁니다. (주로 리소스)
//        web.ignoring().antMatchers("/css/**", "/script/**", "/");
//    }
//

//    @Override
//    public void configure(HttpSecurity http) throws Exception
//    {
//        http.authorizeRequests()
//                .antMatchers("/accounts").authenticated()
//                .antMatchers("/**").permitAll();
//
//        http.authorizeRequests()
//                // 어드민 권한으로만 접근할 수 있는 경로.
//                .antMatchers("/admin/**").access("ROLE_ADMIN");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//        auth.authenticationProvider(facebookAuthenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http
//                // 강의 특성상 전부 허용으로 작업하겠습니다.
//                .authorizeRequests()
//                .antMatchers("/**")
//                .permitAll();

//        http.addFilterBefore((Filter)context.getBean("sso.filter"), BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/reservations").hasRole("ADMIN")
                .antMatchers("/**").permitAll();
//.authenticated()
//        http.authorizeRequests()
//                // 어드민 권한으로만 접근할 수 있는 경로.
//                .antMatchers("/admin/**").access("ROLE_ADMIN");

//        http.formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error");
//        http.addFilterAfter(new FacebookAuthenticationFilter(), FilterSecurityInterceptor.class)
//                .addFilterBefore(new OAuth2ClientContextFilter(), ExceptionTranslationFilter.class);

        http
                .logout()
                 // /logout 을 호출할 경우 로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // 로그아웃이 성공했을 경우 이동할 페이지
                .logoutSuccessUrl("/");

//        http.formLogin()
//                .loginProcessingUrl("/login");
                // 로그인 페이지 : 컨트롤러 매핑을 하지 않으면 기본 제공되는 로그인 페이지가 뜬다.


//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/accounts/**").hasRole("USER")
//                .antMatchers(HttpMethod.PUT,"/accounts/**").hasRole("USER")
//                .anyRequest().permitAll();
    }


}
