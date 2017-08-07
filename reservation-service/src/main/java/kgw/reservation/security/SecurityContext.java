package kgw.reservation.security;

import kgw.reservation.domain.User;

public class SecurityContext {
	public static ThreadLocal<User> loginUser = new ThreadLocal<User>();
}
