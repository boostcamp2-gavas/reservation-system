package com.gavas.service;

import com.gavas.domain.User;

import java.util.Map;

public interface LoginService {
    Map<String, Object> getAcessToken(String token, String code);
    Map<String, String> getProfile(String token);
    String generateState();
    User getUserDto(Map<String, String> profile);
}
