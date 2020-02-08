package com.sliit.research.blockchainbasedapplication.security;

import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class TransactionFilter implements Filter {

    @Autowired
    UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("Token");
        String path = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        if ("POST".equals(method)){
            if ("/auth/signup".equals(path) || "/auth/login".equals(path)){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                if (userRepository.findByToken(token).size() > 0){
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not valid");
                }
            }
        }else if ("GET".equals(method)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
