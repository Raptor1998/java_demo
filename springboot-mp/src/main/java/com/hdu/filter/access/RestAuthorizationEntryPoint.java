package com.hdu.filter.access;


import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raptor
 * @description RestAuthorizationEntryPoint
 * @date 2021/7/6 14:19
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.response(request, response, HttpServletResponse.SC_UNAUTHORIZED, ResultEnum.ACCESS_NOT_AUTHORIZED);
    }
}
