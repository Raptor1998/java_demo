package com.hdu.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author raptor
 * @version V1.0
 * @Package com.hdu.domain.entity
 * @date 2021/1/31 8:55
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class SysUser implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

    private String phone;

    private String realName;

    private Boolean isWorker;

    @JsonIgnoreProperties(value = {"users"})
    private List<SysRole> roles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getWorker() {
        return isWorker;
    }

    public void setWorker(Boolean worker) {
        isWorker = worker;
    }


    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.status == 1 ? true : false;
    }

    public SysUser() {
    }

    public SysUser(Integer id, String username, String password, Integer status, String phone, String realName, Boolean isWorker, List<SysRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.phone = phone;
        this.realName = realName;
        this.isWorker = isWorker;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", isWorker=" + isWorker +
                ", roles=" + roles +
                '}';
    }
}
