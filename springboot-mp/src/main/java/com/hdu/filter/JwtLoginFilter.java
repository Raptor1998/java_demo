package com.hdu.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hdu.config.JwtProperties;
import com.hdu.entity.domain.SysRole;
import com.hdu.entity.domain.SysUser;
import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.utils.JwtUtils;
import com.hdu.utils.ResponseUtil;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    private JwtProperties jwtProperties;

    public JwtLoginFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
        this.authenticationManager = authenticationManager;
        this.jwtProperties=jwtProperties;
    }

    /* 接收并解析用户凭证，出現错误时，返回json数据前端 */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //将json格式请求体转成JavaBean对象
            SysUser sysUser = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
            this.setDetails(request, authRequest);
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            try {
                //如果认证失败，提供自定义json格式异常
                ResponseUtil.response(request, response, HttpServletResponse.SC_UNAUTHORIZED, ResultEnum.WRONG_PASSWORD);
            } catch (Exception outException) {
                outException.printStackTrace();
            }
//            throw new RuntimeException(e);
            return null;
        }
    }

    /* 用户登录成功后，生成token,并且返回json数据给前端 */
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SysUser user = new SysUser();
        user.setUsername(authResult.getName());
        user.setRoles((List<SysRole>) authResult.getAuthorities());
        //json web token构建
//      String token = JwtUtils.generateTokenExpireInMinutes(user, rsaKeyProperties.getPrivateKey(), 7 * 24 * 60);
        String token = JwtUtils.generateTokenExpireInMinutes(user, jwtProperties);
        try {
            //登录成功時，返回json格式进行提示
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpServletResponse.SC_OK);
            map.put("msg", "认证通过！！");
            map.put("token", "Bearer " + token);
            //实际上已经在token中封装了角色
            List<String> roles = new ArrayList<>();
            for (SysRole role : user.getRoles()) {
                roles.add(role.getRoleName());
            }
            map.put("role", roles);
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        } catch (Exception outException) {
            outException.printStackTrace();
        }
    }
}
