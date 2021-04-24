package com.hdu.entity.domain;


import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "Bank--银行实体类")
public class Bank {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bank.id
     *
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    @ApiModelProperty(value = "主键id", example = "1")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bank.username
     *
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    @ApiModelProperty(value = "用户名", example = "Liudehua")
    @NotBlank(message = "不能为空")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bank.money
     *
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    @ApiModelProperty(value = "余额", example = "200")
    private Integer money;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bank.id
     *
     * @return the value of bank.id
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bank.id
     *
     * @param id the value for bank.id
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bank.username
     *
     * @return the value of bank.username
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bank.username
     *
     * @param username the value for bank.username
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bank.money
     *
     * @return the value of bank.money
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bank.money
     *
     * @param money the value for bank.money
     * @mbg.generated Thu Apr 22 14:49:32 CST 2021
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", money=" + money +
                '}';
    }
}