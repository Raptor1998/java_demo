package com.hdu.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @author raptor
 * @description Author
 * @date 2021/4/19 19:12
 */
public class Author implements Serializable {
    private BigInteger createdAt;
    private BigInteger updatedAt;
    private int id;
    private String username;
    private String address;
    private String password;
    private List<Book> bookList;


    public BigInteger getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    public BigInteger getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(BigInteger updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Author() {
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Author(String username, String address, String password) {
        this.username = username;
        this.address = address;
        this.password = password;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Author(BigInteger createdAt, BigInteger updatedAt, int id, String username, String address, String password) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.username = username;
        this.address = address;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
