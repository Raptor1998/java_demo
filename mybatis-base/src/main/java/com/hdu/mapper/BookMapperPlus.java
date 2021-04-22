package com.hdu.mapper;

import com.hdu.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author raptor
 * @description BookMapperPlus
 * @date 2021/4/19 19:02
 */
public interface BookMapperPlus {
    Book findByIdWithUser(int id);
    Book findByIdWithUserStep(int id);
    List<Book> findByUserId(@Param("userId") int userId);
}
