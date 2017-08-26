package com.gavas.security;

import com.gavas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.*;

@Configuration
@EnableOAuth2Client
@ComponentScan("com.gavas.service")
@PropertySource("classpath:/application.properties")
public class OAuth2ClientConfig {
    @Qualifier("oauth2ClientContext")
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;
    private Environment environment;
    private UserService userService;

    @Autowired
    public OAuth2ClientConfig(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    private AuthorizationCodeResourceDetails facebook() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(environment.getProperty("facebook.client.clientId"));
        details.setClientSecret(environment.getProperty("facebook.client.clientSecret"));
        details.setAccessTokenUri(environment.getProperty("facebook.client.accessTokenUri"));
        details.setUserAuthorizationUri(environment.getProperty("facebook.client.userAuthorizationUri"));
        details.setTokenName(environment.getProperty("facebook.client.tokenName"));
        String commaSeparatedScopes = environment.getProperty("facebook.client.scope");
        details.setScope(parseScopes(commaSeparatedScopes));
        details.setAuthenticationScheme(AuthenticationScheme.query);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        return details;
    }

    public AuthorizationCodeResourceDetails naver() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(environment.getProperty("naver.client.clientId"));
        details.setClientSecret(environment.getProperty("naver.client.clientSecret"));
        details.setAccessTokenUri(environment.getProperty("naver.client.accessTokenUri"));
        details.setUserAuthorizationUri(environment.getProperty("naver.client.userAuthorizationUri"));
        details.setTokenName(environment.getProperty("naver.client.tokenName"));
        String commaSeparatedScopes = environment.getProperty("naver.client.scope");
        details.setScope(parseScopes(commaSeparatedScopes));
        details.setAuthenticationScheme(AuthenticationScheme.query);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        return details;
    }

    private List<String> parseScopes(String commaSeparatedScopes) {
        String[] scopeArr = commaSeparatedScopes.split(",");
        List<String> list = new ArrayList<>();
        for (String scope : scopeArr) {
            list.add(scope);
        }
        return list;
    }

    @Bean("sso.filter")
    public Filter ssoFilter() {
        List<Filter> filters = new ArrayList<>();

        OAuth2ClientAuthenticationProcessingFilter facebook
                = new OAuth2ClientAuthenticationProcessingFilter("/facebook_login");
        facebook.setRestTemplate(new OAuth2RestTemplate(facebook(), oauth2ClientContext));
        facebook.setTokenServices(new UserTokenService(environment.getProperty("facebook.resource.userInfoUri"), environment.getProperty("facebook.client.clientId")));
        facebook.setAuthenticationSuccessHandler(new OAuth2SuccessHandler("facebook", userService));
        filters.add(facebook);

        OAuth2ClientAuthenticationProcessingFilter naver
                = new OAuth2ClientAuthenticationProcessingFilter("/logins");
        naver.setRestTemplate(new OAuth2RestTemplate(naver(), oauth2ClientContext));
        naver.setTokenServices(new UserTokenService(environment.getProperty("naver.resource.userInfoUri"), environment.getProperty("naver.client.clientId")));
        naver.setAuthenticationSuccessHandler(new OAuth2SuccessHandler("naver", userService));
        filters.add(naver);

        CompositeFilter filter = new CompositeFilter();
        filter.setFilters(filters);
        return filter;
    }
}
