package com.school.API.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.API.data.AdminDetailsData;
import com.school.API.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.expiration}")
    protected static String TOKEN_EXPIRATION;

    @Value("${jwt.expiration.secret}")
    protected static String TOKEN_SECRET;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            Admin admin = new ObjectMapper()
                    .readValue(request.getInputStream(), Admin.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    admin.getUsername(),
                    admin.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Fail to authenticate user", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        AdminDetailsData adminData = (AdminDetailsData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(adminData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Integer.parseInt(TOKEN_EXPIRATION)))
                .sign(Algorithm.HMAC512(TOKEN_SECRET));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
