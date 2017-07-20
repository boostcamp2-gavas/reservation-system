package connect.reservation.service.impl;

import org.springframework.stereotype.Service;

import connect.reservation.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
/*	private final static String CLIENT_ID = "eGDuy2NMeDv1C1QCsPGF";
	private final static String CLIENT_SECRET = "hw2sty6mby";
	private final static String REDIRECT_URI = "http://localhost:8080/";
	private final static String SESSION_STATE = "oauth_state";
	
	
	 네아로 인증  URL 생성  Method 
	public String getAuthorizationUrl(HttpSession session) {

		 세션 유효성 검증을 위하여 난수를 생성 
		String state = generateRandomString();
		 생성한 난수 값을 session에 저장 
		setSession(session,state);

		 Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI)
				.state(state) //앞서 생성한 난수값을 인증 URL생성시 사용함
				.build(NaverLoginApi.instance());

		return oauthService.getAuthorizationUrl();
	}

	 네아로 Callback 처리 및  AccessToken 획득 Method 
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
		
		 Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 
		String sessionState = getSession(session);
		if(StringUtils.equals(sessionState, state)){
		
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI)
					.state(state)
					.build(NaverLoginApi.instance());
					
			 Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}
	
	 세션 유효성 검증을 위한 난수 생성기 
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}
	
	 http session에 데이터 저장 
	private void setSession(HttpSession session,String state){
		session.setAttribute(SESSION_STATE, state);		
	}

	 http session에서 데이터 가져오기 	
	private String getSession(HttpSession session){
		return (String) session.getAttribute(SESSION_STATE);
	}*/
}
