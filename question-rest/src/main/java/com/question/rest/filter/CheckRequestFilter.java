package com.question.rest.filter;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.question.rest.exception.EntityException;

public class CheckRequestFilter implements ContainerRequestFilter {

//	private void validateJWToken(String jwt) throws Exception {
//		// Base64.decodeBase64(base64String)
//		String secret = "d9HiW5JK6QkIIfQ21LV-8xiI1HQst4CpEE5_2FIOumJ5kkOHa9AddNTFIBdapcVl";
//		JWTVerifier jwtVerifier = new JWTVerifier(Base64.decodeBase64(secret));
//		final Map<String, Object> claims = jwtVerifier.verify(jwt);
//		System.out.println(claims.toString());
//
//	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
//		try {
//			String token = requestContext.getHeaders().get("Authorization").get(0).toString();
//			if(StringUtils.isBlank(token)) {
//				throw new EntityException(Status.UNAUTHORIZED, 98, "Authorization header is required.");
//			}
//			if(token.contains("Bearer")) {
//				String[] tokens = token.split(" ");
//				token = tokens[1];
//			}
//			validateJWToken(token);
//		} catch (Exception e) {
//			throw new EntityException(Status.UNAUTHORIZED, 99, "Authorization header is invalid.");
//		}		
	}

}