package com.gavas.oauth;

import com.gavas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
@PropertySource("classpath:/application.properties")
public class OAuth2ClientConfig
{
    @Qualifier("oauth2ClientContext")
    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private Environment env;

    @Autowired
    CategoryService categoryService;

    public AuthorizationCodeResourceDetails facebook() {
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

    public AuthorizationCodeResourceDetails naver() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(env.getProperty("naver.client.clientId"));
        details.setClientSecret(env.getProperty("naver.client.clientSecret"));
        details.setAccessTokenUri(env.getProperty("naver.client.accessTokenUri"));
        details.setUserAuthorizationUri(env.getProperty("naver.client.userAuthorizationUri"));
        details.setTokenName(env.getProperty("naver.client.tokenName"));
        String commaSeparatedScopes = env.getProperty("naver.client.scope");
        details.setScope(parseScopes(commaSeparatedScopes));
        details.setAuthenticationScheme(AuthenticationScheme.query);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        return details;
    }

    private List<String> parseScopes(String commaSeparatedScopes) {
        String[] scopeArr = commaSeparatedScopes.split(",");
        List<String> list = new ArrayList<>();
        for (String scope : scopeArr ) {
            list.add(scope);
        }
        return list;
    }

    protected Collection<GrantedAuthority> generateAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Bean("sso.filter")
    Filter ssoFilter()
    {
        if(oauth2ClientContext == null) {
            System.out.println("asfsafasfads");
        } else {
            System.out.println("not null");
        }
        List<Filter> filters = new ArrayList<>();

        OAuth2ClientAuthenticationProcessingFilter facebook
                = new OAuth2ClientAuthenticationProcessingFilter("/facebook_login");

        facebook.setRestTemplate(new OAuth2RestTemplate(facebook(), oauth2ClientContext));
        facebook.setTokenServices(new UserTokenService(env.getProperty("facebook.resource.userInfoUri"),env.getProperty("facebook.client.clientId")));
        facebook.setAuthenticationSuccessHandler(new OAuth2SuccessHandler("facebook"));
        filters.add(facebook);

        OAuth2ClientAuthenticationProcessingFilter naver
            = new OAuth2ClientAuthenticationProcessingFilter("/logins");
        naver.setRestTemplate(new OAuth2RestTemplate(naver(), oauth2ClientContext));
        naver.setTokenServices(new UserTokenService(env.getProperty("naver.resource.userInfoUri"),env.getProperty("naver.client.clientId")));
        naver.setAuthenticationSuccessHandler(new OAuth2SuccessHandler("naver"));
        filters.add(naver);

        CompositeFilter filter = new CompositeFilter();
        filter.setFilters(filters);
        return filter;
    }
}
