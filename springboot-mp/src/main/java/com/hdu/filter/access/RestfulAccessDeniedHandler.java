package com.hdu.filter.access;



import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raptor
 * @description RestfulAccessDeniedHandler
 * @date 2021/7/6 14:20
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.response(request, response, HttpServletResponse.SC_FORBIDDEN, ResultEnum.NO_PERMISSION_USE);
    }
}
