//package com.puneet.config;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.crypto.SecretKey;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class JwtValidator extends OncePerRequestFilter {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtValidator.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
//
//        if (jwt != null && jwt.startsWith("Bearer ")) {
//            jwt = jwt.substring(7);
//            try {
//                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//
//                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//
//                String email = String.valueOf(claims.get("email"));
//                String authorities = String.valueOf(claims.get("authorities"));
//
//                List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
//                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            } catch (ExpiredJwtException e) {
//                logger.error("JWT expired", e);
//                throw new BadCredentialsException("JWT expired", e);
//            } catch (UnsupportedJwtException e) {
//                logger.error("JWT is unsupported", e);
//                throw new BadCredentialsException("JWT is unsupported", e);
//            } catch (MalformedJwtException e) {
//                logger.error("JWT is malformed", e);
//                throw new BadCredentialsException("JWT is malformed", e);
//            } catch (SignatureException e) {
//                logger.error("JWT signature does not match", e);
//                throw new BadCredentialsException("JWT signature does not match", e);
//            } catch (IllegalArgumentException e) {
//                logger.error("JWT is illegal or cannot be constructed", e);
//                throw new BadCredentialsException("JWT is illegal or cannot be constructed", e);
//            } catch (Exception e) {
//                logger.error("An error occurred while processing the JWT", e);
//                throw new BadCredentialsException("An error occurred while processing the JWT", e);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
package com.puneet.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = request.getHeader(JwtConstant.JWT_HEADER);

		if (jwt != null && jwt.startsWith("Bearer ")) {

			jwt = jwt.substring(7);
			try {
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

				String email = String.valueOf(claims.get("email"));

				String authorities = String.valueOf(claims.get("authorities"));

				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);

				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception e) {
				throw new BadCredentialsException("invalid token from jwt validatorrrrrrr");
			}

		}
		filterChain.doFilter(request, response);

	}

}