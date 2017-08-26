package com.gavas.config;

import com.gavas.oauth.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@PropertySource("classpath:/application.properties")
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = "com.gavas.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    ApplicationContext context;

    @Autowired
    private Environment env;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private OAuth2ClientContextFilter oauth2ClientContextFilter;

//    @Autowired
//    private CategoryService categoryService;


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

    @Bean
    public OAuth2ProtectedResourceDetails authorizationCodeResource() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(env.getProperty("facebook.client.clientId"));
        details.setClientSecret(env.getProperty("facebook.client.clientSecret"));
        details.setAccessTokenUri(env.getProperty("facebook.client.accessTokenUri"));
        details.setUserAuthorizationUri(env.getProperty("facebook.client.userAuthorizationUri"));
        details.setTokenName(env.getProperty("facebook.client.tokenName"));
        String commaSeparatedScopes = env.getProperty("facebook.client.scope");
        details.setScope(parseScopes(commaSeparatedScopes));
        details.setAuthenticationScheme(AuthenticationScheme.query);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        return details;
    }

    private List<String> parseScopes(String commaSeparatedScopes) {
        List<String> scopes = new LinkedList<>();
        Collections.addAll(scopes, commaSeparatedScopes.split(","));
        return scopes;
    }

//    private List<String> parseScopes(String commaSeparatedScopes) {
//        String[] scopeArr = commaSeparatedScopes.split(",");
//        List<String> list = new ArrayList<>();
//        for (String scope : scopeArr ) {
//            list.add(scope);
//        }
//        return list;
//    }

    @Bean
    public OAuth2ClientAuthenticationProcessingFilter oauth2ClientAuthenticationProcessingFilter() {
        // Used to obtain access token from authorization server (AS)
        OAuth2RestOperations restTemplate = new OAuth2RestTemplate(authorizationCodeResource(), oauth2ClientContext);
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter("/facebook_login");
        filter.setRestTemplate(restTemplate);
//        new GoogleUserInfoTokenServices(env.getProperty("facebook.resource.userInfoUri"),env.getProperty("facebook.client.clientId")
        filter.setTokenServices(new UserTokenService(env.getProperty("facebook.resource.userInfoUri"),env.getProperty("facebook.client.clientId")));
//        filter.setAuthenticationSuccessHandler(new OAuth2SuccessHandler("facebook"));
        return filter;
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
//        auth.userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder);
//        FacebookAuthenticationProvider facebookAuthenticationProvider = new FacebookAuthenticationProvider();
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

//

//        exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint())
//                .and().

//        http.addFilterAfter(
//                oauth2ClientContextFilter,
//                ExceptionTranslationFilter.class)
//                .addFilterBefore(
//                        oauth2ClientAuthenticationProcessingFilter(),
//                        FilterSecurityInterceptor.class)
//                .anonymous()
//                .disable();
//
//        http.authorizeRequests()
//                .antMatchers("/reservations").hasRole("OAUTH")
//                .antMatchers("/**").permitAll();

//        http.authorizeRequests()
//                .antMatchers("/details").hasRole("USER")
//                .antMatchers("/**").permitAll();;


        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
			/* No need for form-based login or basic authentication
			.and()
				.formLogin()
					.loginPage("...")
					.loginProcessingUrl("...")
			.and()
				.httpBasic()
			 */
                .and()
                .addFilterAfter(
                        oauth2ClientContextFilter,
                        ExceptionTranslationFilter.class)
                .addFilterBefore(
                        (Filter)context.getBean("sso.filter"),
                        FilterSecurityInterceptor.class)
                .anonymous()
                .disable();

//        oauth2ClientAuthenticationProcessingFilter(),

        http
                .logout()
                // /logout 을 호출할 경우 로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // 로그아웃이 성공했을 경우 이동할 페이지
                .logoutSuccessUrl("/");






//        http.addFilterBefore((Filter)context.getBean("facebook.filter") , BasicAuthenticationFilter.class);
//        http.addFilterBefore((Filter)context.getBean("oauth2ClientContextFilter"), WebAsyncManagerIntegrationFilter.class);
//        http.addFilterBefore((Filter)context.getBean("sso.filter"), BasicAuthenticationFilter.class);
//

//.authenticated()
//        http.authorizeRequests()
//                // 어드민 권한으로만 접근할 수 있는 경로.
//                .antMatchers("/admin/**").access("ROLE_ADMIN");

//        http.formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error");
//        http.addFilterAfter(new FacebookAuthenticationFilter(), FilterSecurityInterceptor.class)
//                .addFilterBefore(new OAuth2ClientContextFilter(), ExceptionTranslationFilter.class);

//        http.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint())
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//			/* No need for form-based login or basic authentication
//			.and()
//				.formLogin()
//					.loginPage("...")
//					.loginProcessingUrl("...")
//			.and()
//				.httpBasic()
//			 */
//                .and()
//                .addFilterAfter(
//                        oauth2ClientContextFilter,
//                        ExceptionTranslationFilter.class)
//                .addFilterBefore(
//                        oauth2ClientAuthenticationProcessingFilter()
//                        ,
//                        FilterSecurityInterceptor.class)
//                .anonymous()
//                // anonymous login must be disabled,
//                // otherwise an anonymous authentication will be created,
//                // and the UserRedirectRequiredException will not be thrown,
//                // and the user will not be redirected to the authorization server
//                .disable();

//        oauth2ClientAuthenticationProcessingFilter()


//        http.formLogin()
//                .loginProcessingUrl("/login");
                // 로그인 페이지 : 컨트롤러 매핑을 하지 않으면 기본 제공되는 로그인 페이지가 뜬다.


//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/accounts/**").hasRole("USER")
//                .antMatchers(HttpMethod.PUT,"/accounts/**").hasRole("USER")
//                .anyRequest().permitAll();
    }


}
