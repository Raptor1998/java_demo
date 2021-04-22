package com.hdu.mapper;

import com.hdu.entity.Author;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author raptor
 * @description AuthorMapper
 * @date 2021/4/19 19:16
 */
public interface AuthorMapper {
    Author findById(int id);
    Author findByIdWithBook(int id);
    Author findByIdWithBookStep(int id);



    //动态sql
    List<Author> findByCondition(Author author);

    List<Author> findByConditionList(List<String> usernames);


    void addUsers(@Param("authors") List<Author> authors);
}
