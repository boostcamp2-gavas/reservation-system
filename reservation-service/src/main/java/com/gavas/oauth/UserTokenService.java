package com.gavas.oauth;

public class UserTokenService extends UserTokenServices
{
    public UserTokenService(String userInfoEndpointUrl, String clientId)
    {
        super(userInfoEndpointUrl, clientId);
    }
}