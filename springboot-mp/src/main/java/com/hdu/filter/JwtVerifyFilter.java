package com.hdu.filter;

import com.hdu.config.JwtProperties;
import com.hdu.entity.domain.SysUser;
import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.entity.pojo.Payload;
import com.hdu.utils.JwtUtils;
import com.hdu.utils.ResponseUtil;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtVerifyFilter extends BasicAuthenticationFilter {


    private JwtProperties jwtProperties;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
        super(authenticationManager);
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            //请求体的头中是否包含Authorization
            String header = request.getHeader("Authorization");
            //Authorization中是否包含Bearer，不包含直接返回
            if (header == null || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                responseJson(request, response);
                return;
            }
            //获取权限失败，会抛出异常
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            //获取后，将Authentication写入SecurityContextHolder中供后序使用
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (SignatureException e) {
            tokenExpire(request,response);
        }catch (Exception e){
            error(request,response);
        }
    }

    private void responseJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtil.response(request, response, HttpServletResponse.SC_FORBIDDEN, ResultEnum.LOGIN_EXCEPTION);
    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtil.response(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ResultEnum.UNKNOWN_ERROR);
    }

    private void tokenExpire(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //未登录提示
        ResponseUtil.response(request, response, HttpServletResponse.SC_FORBIDDEN, ResultEnum.AUTHORIZED_FAILED);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        if (token != null) {
            //通过token解析出载荷信息
            //Payload<User> payload = JwtUtils.getInfoFromToken(token.replace("Bearer ", ""), rsaKeyProperties.getPublicKey(), User.class);
            //User user = payload.getUserInfo();
            Payload<SysUser> payload = JwtUtils.getInfoFromToken(token.replace("Bearer ", ""), jwtProperties, SysUser.class);
            SysUser user = payload.getUserInfo();
//            return new UsernamePasswordAuthenticationToken(username, null, authorities);
            //不为null，返回
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getRoles());
            }
            return null;
        }
        return null;
    }
}
