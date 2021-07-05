package com.hdu.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @author raptor
 * @description Role
 * @date 2021/4/24 19:11
 */
public class Role implements GrantedAuthority , Serializable {
    private Integer id;
    private String roleName;
    private String roleDesc;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return null;
    }

    public Role() {
    }

    public Role(Integer id, String roleName, String roleDesc) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
