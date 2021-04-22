package com.hdu;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hdu.entity.Author;
import com.hdu.entity.Book;
import com.hdu.mapper.AuthorMapper;
import com.hdu.mapper.BookMapper;
import com.hdu.mapper.BookMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author raptor
 * @description AppTest
 * @date 2021/4/16 14:36
 */
public class AppTest {

    /*
        1. 获取sqlSessionFactory对象
        2. 获取SQLSession对象
        3. 获取接口的实现类斗代理对象（MapperProxy）
        4. 执行增删改查
     */

    @Test
    public void context() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
//        Object o = sqlSession.selectOne("com.hdu.mapper.BookMapper.queryById", 2);
//        System.out.println(o);
//        sqlSession.close();
        //select
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
//        System.out.println(mapper);
//        Book book = mapper.queryById(7);
//        System.out.println(book);
//        Book book1 = new Book(""+(Math.random()*100),30);
//        mapper.add(book1);
//        System.out.println(book1);
        //mapper.delete(2);
        //mapper.update(new Book(book1.getId(),"哈哈",20));



        Book qwe = mapper.queryByIdAndName(3, "疏忽转");
        System.out.println(qwe);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void context2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapperPlus mapper = sqlSession.getMapper(BookMapperPlus.class);
        Book book = mapper.findByIdWithUserStep(24);
        System.out.println(book);
//        System.out.println(book.getName());
//        System.out.println(book.getAuthor().getUsername());
    }
    @Test
    public void context3() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuthorMapper mapper = sqlSession.getMapper(AuthorMapper.class);
        Author author = mapper.findByIdWithBookStep(3);
        System.out.println(author.getUsername());
        System.out.println(author.getBookList());
    }

    @Test
    public void context4() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuthorMapper mapper = sqlSession.getMapper(AuthorMapper.class);
        Author temp = new Author();
        temp.setId(1);
//        temp.setUsername("rap");
        List<Author> authors = mapper.findByConditionList(new ArrayList<>(Arrays.asList("raptor", "陈文豪")));
        System.out.println(authors);
    }
    @Test
    public void context5() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuthorMapper mapper = sqlSession.getMapper(AuthorMapper.class);
        List<Author> authors=new ArrayList<>(Arrays.asList(new Author("zxcx","xxzc","xsaasaxs"),
                new Author("qwxe","qwxe","qxw")));
        mapper.addUsers(authors);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void context6() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        BookMapperPlus mapper = sqlSession.getMapper(BookMapperPlus.class);
        BookMapperPlus mapper2 = sqlSession2.getMapper(BookMapperPlus.class);
        Book book = mapper.findByIdWithUser(24);
        System.out.println(book);
        sqlSession.close();
        Book book1 = mapper2.findByIdWithUser(24);
        System.out.println(book1);
        sqlSession2.close();
    }
    @Test
    public void context7() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Page<Object> page = PageHelper.startPage(2, 5);
        List<Book> books = mapper.queryAll();
        System.out.println(page.getTotal());
        for (Book b:books){
            System.out.println(b);
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
