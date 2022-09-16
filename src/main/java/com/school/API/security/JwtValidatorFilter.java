package com.school.API.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtValidatorFilter extends BasicAuthenticationFilter {

    @Value("${jwt.header.attribute}")
    private static String HEADER_ATTR;
    @Value("${jwt.attribute.prefix}")
    private static String ATTR_PREFIX;

    public JwtValidatorFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String attr = request.getHeader(HEADER_ATTR);

        if (attr == null || !attr.startsWith(ATTR_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        String token = attr.replace(ATTR_PREFIX, "");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

        String username = JWT.require(Algorithm.HMAC512(JwtAuthFilter.TOKEN_SECRET))
                .build()
                .verify(token)
                .getSubject();

        if(username == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
