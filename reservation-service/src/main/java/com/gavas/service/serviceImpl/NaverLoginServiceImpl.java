package com.gavas.service.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gavas.domain.User;
import com.gavas.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

@Service
public class NaverLoginServiceImpl implements LoginService {
    @Value("${open-api.naver.client-id}")
    private String clientId;
    @Value("${open-api.naver.client-secret}")
    private String clientSecret;

    @Override
    public Map<String, Object> getAcessToken(String token, String code) {
        String tokenGetUrl = "https://nid.naver.com/oauth2.0/token?client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=authorization_code&state=";
        try {
            String apiURL = tokenGetUrl + token + "&code=" + code;
            URL url = new URL(apiURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return jsonToMap(response.toString());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Map<String, String> getProfile(String token) {
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String header = "Bearer " + token;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);

            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            Map<String, String> profile = (Map<String, String>) jsonToMap(response.toString()).get("response");

            return profile;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    @Override
    public User getUserDto(Map<String, String> profile) {
        User user = new User();
        user.setUsername(profile.get("name"));
        user.setAdminFlag(1L);
        user.setEmail(profile.get("email"));
        user.setSnsId(profile.get("id"));
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        user.setNickname(profile.get("nickname"));
        user.setSnsProfile(profile.get("profile_image"));
        user.setSnsType("naver");
        return user;
    }

    private Map<String, Object> jsonToMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
    }
}
