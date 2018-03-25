package edu.bsu.shuttlelog.token;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import edu.bsu.shuttlelog.resp.RespException;

public class AuthUtil {
	public static final String MANAGER_CODE = "MANAGER";
	public static final String DRIVER_CODE = "DRIVER";

	private static Map<String, Object> getClientLoginInfo(String token) throws Exception {
		Map<String, Object> r = new HashMap<String, Object>();
		String sessionId = token;
		if (sessionId != null) {
			r = decodeSession(sessionId);
			return r;
		}
		throw new RespException("403", "illegal Token", null);
	}

	public static Long getUserId(String token) throws Exception {
		return Long.valueOf((Integer) getClientLoginInfo(token).get("userId"));

	}

	public static Map<String, Object> getUser(String token) throws Exception {
		return getClientLoginInfo(token);
	}

	public static boolean userAuth(String token,String Role) throws Exception {
		try {
			boolean auth = getClientLoginInfo(token).get("role").equals(Role);
			if (!auth) {
				throw new RespException("403", "User Not Authorized", null);
			}
			return auth;
		} catch (Exception e) {
			throw new RespException("403", "Wrone Token", null);
		}
	}

	/**
	 * session decode
	 */
	public static Map<String, Object> decodeSession(String sessionId) throws Exception {

		return JavaWebToken.verifyJavaWebToken(sessionId);

	}
}