package com.hdu.mapper;

import com.hdu.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author raptor
 * @description BookMapper
 * @date 2021/4/16 14:03
 */
public interface BookMapper {
    Book queryById(int id);
    void add(@Param("book") Book book);
    void update(Book book);
    void delete(int id);
    Book queryByIdAndName(@Param("id") int id , @Param("name") String name);
    List<Book> queryAll();
}
