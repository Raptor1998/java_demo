package com.hdu.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author raptor
 * @description Book
 * @date 2021/4/15 10:28
 */
public class Book implements Serializable {
    private int id;
    private String name;
    private int price;
    private Author author;
    public Book() {
    }

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Book(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author=" + author +
                '}';
    }
}
