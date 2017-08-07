package kgw.reservation.oauth.naver;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import kgw.reservation.dto.NaverBlogResult;
import kgw.reservation.dto.NaverLoginProfile;

public class NaverApiBO {
	
	//client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
	//response_type: 인증 과정에 대한 구분값. code로 값이 고정돼 있습니다.
	//redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩). 애플리케이션을 등록할 때 Callback URL에 설정한 정보입니다.
	//state: 애플리케이션이 생성한 상태 토큰
	private final static String CLIENT_ID = "9xywjQhEim1nZVIa1xZc";
	private final static String CLIENT_SECRET = "K5uiklulf0";
	private final static String REDIRECT_URI = "http://127.0.0.1:8080/login/callback";
	// 프로필 조회 API URL
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	// 블로그 글쓰기 API URL 	
	private final static String BLOG_POST_URL ="https://openapi.naver.com/blog/writePost.json";
	
	private OAuth20Service oauthService; 
	// 네이버 아이디로 인증  URL 생성  Method 
	public String getAuthorizationUrl(String oauthState) {        
	    
	    // Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 
	    OAuth20Service oauthService = new ServiceBuilder()                                                   
	            .apiKey(CLIENT_ID)
	            .apiSecret(CLIENT_SECRET)
	            .callback(REDIRECT_URI)
	            .state(oauthState) // 생성한 난수값을 인증 URL생성시 사용함
	            .build(NaverLoginApi.instance());
	    return oauthService.getAuthorizationUrl();
	}
	
	// 네이버아이디로 Callback 처리 및  AccessToken 획득 Method 
	public OAuth2AccessToken reqAccessToken(String oauthState, String code, String state) throws IOException{
	
	    // Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 
	    if(StringUtils.pathEquals(oauthState, state)){
	
	        oauthService = new ServiceBuilder()
	                .apiKey(CLIENT_ID)
	                .apiSecret(CLIENT_SECRET)
	                .callback(REDIRECT_URI)
	                .state(state)
	                .build(NaverLoginApi.instance());
	
	        // Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득
	        OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
	        return accessToken;
	    }
	    return null;
	}
	
	// Access Token을 이용하여 네이버 사용자 프로필 API를 호출 
	public NaverLoginProfile getUserProfile(OAuth2AccessToken oauthToken) throws IOException{
		
	    OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
	    oauthService.signRequest(oauthToken, request);
	    Response response = request.send();
	    
	    //read JSON like DOM Parser
	    //ObjectMapper instance 만들기 
  		ObjectMapper objectMapper = new ObjectMapper();
  		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    JsonNode rootNode = objectMapper.readTree(response.getBody());
	    
	    JsonNode responseNode = rootNode.path("response");
	    NaverLoginProfile naverLoginProfile = objectMapper.treeToValue(responseNode, NaverLoginProfile.class);
	    return naverLoginProfile;
	}
	// Access Token을 이용하여 네이버 블로그 글 게시API 호출 
	public NaverBlogResult postBlogContent(OAuth2AccessToken oauthToken, String title, String contents) throws IOException {

		OAuthRequest request = new OAuthRequest(Verb.POST, BLOG_POST_URL, oauthService);
		request.addHeader("Authorization", "Bearer "+oauthToken.getAccessToken());
		request.addBodyParameter("title", title);
		request.addBodyParameter("contents", contents);
		Response response = request.send();
		
		//read JSON like DOM Parser
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
	    JsonNode rootNode = objectMapper.readTree(response.getBody());
	    JsonNode responseNode = rootNode.path("message").path("result");
	    
	    NaverBlogResult naverBlogResult = objectMapper.treeToValue(responseNode, NaverBlogResult.class);
	    return naverBlogResult;
		
	}
	public OAuth2AccessToken reqRefreshAccessTocken(OAuth2AccessToken oauthToken) throws IOException {
		OAuth2AccessToken newAccessToken = oauthService.refreshAccessToken(oauthToken.getRefreshToken());
		
		return new OAuth2AccessToken(newAccessToken.getAccessToken(), oauthToken.getTokenType(), oauthToken.getExpiresIn(), oauthToken.getRefreshToken(), null, "NaverApi");
	}
}

