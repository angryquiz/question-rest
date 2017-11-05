package com.question.rest.util;

import java.util.Map;

import javax.inject.Named;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.question.rest.exception.EntityException;

@Named
public class Utils {

	static Logger LOG = Logger.getLogger(Utils.class);
	
	public void validateToken(String token) {
		try {
			validateJWToken(token);
		} catch (Exception e) {
			throw new EntityException(Status.UNAUTHORIZED, 100, "JSON Web token is invalid.");
		}		
	}
	
	private void validateJWToken(String jwt) throws Exception {

		if (!StringUtils.isBlank(jwt)) {
			//Base64.decodeBase64(base64String)
			String secret = "d9HiW5JK6QkIIfQ21LV-8xiI1HQst4CpEE5_2FIOumJ5kkOHa9AddNTFIBdapcVl";
			JWTVerifier jwtVerifier = new JWTVerifier(Base64.decodeBase64(secret));
			final Map<String, Object> claims = jwtVerifier.verify(jwt);
			LOG.info(claims.toString());
		}

	}	
	
}
