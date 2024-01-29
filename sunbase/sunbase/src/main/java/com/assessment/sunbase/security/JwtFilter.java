package com.assessment.sunbase.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter{

    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsServiceImpl usersService;

    public JwtFilter(
            JwtTokenUtil jwtTokenUtil,
            UserDetailsServiceImpl usersService
    ) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName = null;
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            try {
                userName = jwtTokenUtil.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            System.out.println("JWT Token does not begin with Bearer String");
        }

        if(userName != null &&
                SecurityContextHolder.getContext().getAuthentication() == null){
                 System.out.println("userName = " + userName);
                UserDetails userDetails = usersService.loadUserByUsername(userName);
                if(jwtTokenUtil.validateToken(token,userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        } else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }

}
