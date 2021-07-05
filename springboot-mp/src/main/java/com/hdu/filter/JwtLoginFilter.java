package com.hdu.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdu.config.RsaKeyProperties;
import com.hdu.entity.domain.Role;
import com.hdu.entity.domain.User;
import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.exception.specificException.ParameterServiceException;
import com.hdu.utils.JwtUtils;
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

    private RsaKeyProperties rsaKeyProperties;

    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperties rsaKeyProperties) {
        this.authenticationManager = authenticationManager;
        this.rsaKeyProperties = rsaKeyProperties;
    }

    /* 接收并解析用户凭证，出現错误时，返回json数据前端 */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //将json格式请求体转成JavaBean对象
            User sysUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
            this.setDetails(request, authRequest);
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            try {
                //如果认证失败，提供自定义json格式异常
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("code", HttpServletResponse.SC_UNAUTHORIZED);
                map.put("msg", "账号或密码错误！");
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
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
        User user = new User();
        user.setUsername(authResult.getName());
        user.setRoles((List<Role>) authResult.getAuthorities());
//        System.out.println(user);
        //json web token构建
        String token = JwtUtils.generateTokenExpireInMinutes(user, rsaKeyProperties.getPrivateKey(), 7 * 24 * 60);
        //返回token
        //response.addHeader("Authorization", "Bearer " + token);
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
            for (Role role : user.getRoles()) {
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
