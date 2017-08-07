package kgw.reservation.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.github.scribejava.core.model.OAuth2AccessToken;

import kgw.reservation.oauth.naver.NaverApiBO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	private NaverApiBO naverApiBO;
	@Autowired
	public LoginCheckInterceptor(NaverApiBO naverApiBO) {
		this.naverApiBO = naverApiBO ;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if( session.getAttribute("loginInfo") == null ) {
				response.sendRedirect("/login");
				return false;
		}
		
		Date current = new Date();
		// access_token 만기 시, refresh함. 
		if ( current.compareTo( (Date) session.getAttribute("oauthTokenExpires")) > 0 ) {
			session.setAttribute("oauthToken", naverApiBO.reqRefreshAccessTocken((OAuth2AccessToken)session.getAttribute("oauthToken")));
			return true;
		}
		return true;
	}
}