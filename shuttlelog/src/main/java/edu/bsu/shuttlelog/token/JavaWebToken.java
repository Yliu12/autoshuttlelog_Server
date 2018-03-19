package edu.bsu.shuttlelog.token;

import java.security.Key;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.xml.bind.DatatypeConverter;


//  http://blog.csdn.net/jack__frost/article/details/64964208
public class JavaWebToken {

	private static Logger log = Logger.getLogger(JavaWebToken.class);
	private static final String SECRET = "010000010101000001010000";


	private static Key getKeyInstance() {
		// return MacProvider.generateKey();
		// We will sign our JavaWebToken with our ApiKey secret
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		return signingKey;
	}

	public static String createJavaWebToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
	}

	public static Map<String, Object> verifyJavaWebToken(String jwt) {
		try {

			Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
			return jwtClaims;
		} catch (Exception e) {
			log.error("json web token verify failed");
			return null;
		}
	}

}