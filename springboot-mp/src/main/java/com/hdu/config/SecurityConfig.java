package com.hdu.config;


import com.hdu.filter.JwtLoginFilter;
import com.hdu.filter.JwtVerifyFilter;
import com.hdu.filter.access.RestAuthorizationEntryPoint;
import com.hdu.filter.access.RestfulAccessDeniedHandler;
import com.hdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private RsaKeyProperties rsaKeyProperties;

    @Autowired
    private JwtProperties jwtProperties;


    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ?????????????????????url
        web.ignoring().antMatchers("/user/get/token", "/register");

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtLoginFilter(super.authenticationManager(),jwtProperties))
                .addFilter(new JwtVerifyFilter(super.authenticationManager(),jwtProperties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
        // ????????????
        http.cors().configurationSource(corsConfigurationSource());
    }

    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // ???????????????*????????????????????????????????????????????????ip???????????????????????????localhost???8080?????????????????????????????????
        corsConfiguration.addAllowedOrigin("*");
        // header???????????????header????????????????????????token???????????????*?????????token???
        corsConfiguration.addAllowedHeader("*");
        // ????????????????????????POST???GET???
        corsConfiguration.addAllowedMethod("*");
        // ???????????????????????????url
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}