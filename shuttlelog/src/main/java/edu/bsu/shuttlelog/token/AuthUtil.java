package edu.bsu.shuttlelog.token;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import edu.bsu.shuttlelog.resp.RespException;

public class AuthUtil {
	private static Map<String, Object> getClientLoginInfo(String token) throws Exception {
		Map<String, Object> r = new HashMap<String, Object>();
		String sessionId = token;
		if (sessionId != null) {
			r = decodeSession(sessionId);
			return r;
		}
		throw new RespException("403", "illegal Token",null);
	}

	public static Long getUserId(String token) throws Exception {
		return Long.valueOf((Integer) getClientLoginInfo(token).get("userId"));

	}
	
	public static Map<String, Object> getUser(String token) throws Exception {				
		return getClientLoginInfo(token);
	}
	
	public static boolean driverAuth(String token) throws Exception {	
		boolean auth = getClientLoginInfo(token).get("role").equals("DRIVER");
		if(!auth) {
			throw new RespException("403", "illegal Token",null);
		}
		return auth;
	}

	/**
	 * session解密
	 */
	public static Map<String, Object> decodeSession(String sessionId) {
		try {
			return JavaWebToken.verifyJavaWebToken(sessionId);
		} catch (Exception e) {
			System.err.println("");
			return null;
		}
	}
}