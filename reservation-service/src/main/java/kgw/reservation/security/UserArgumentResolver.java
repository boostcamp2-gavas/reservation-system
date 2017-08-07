package kgw.reservation.security;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		LogginedUser logginedUser = parameter.getParameterAnnotation( LogginedUser.class );
        if(logginedUser== null)
            return false;
        else
            return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		LogginedUser logginedUser = parameter.getParameterAnnotation( LogginedUser.class );
		if(logginedUser == null) {
			 SecurityContext.loginUser.set(null);
			return WebArgumentResolver.UNRESOLVED;
		}
        return SecurityContext.loginUser.get();
	}

}
