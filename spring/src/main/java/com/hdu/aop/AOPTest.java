package com.hdu.aop;

import com.hdu.aop.config.AopConfig;
import com.hdu.aop.dao.UserDao;
import com.hdu.aop.dao.UserDaoImpl;
import com.hdu.aop.entity.Game;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.PipedOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author raptor
 * @description AOPTest
 * @date 2021/4/14 17:30
 */
public class AOPTest {
    @Test
    public void context(){
        //使用proxy增强
//        Proxy.newProxyInstance(AOPTest.class.getClassLoader(), new Class[]{UserDao.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });
        UserDaoImpl userDao=new UserDaoImpl();
        UserDao o = (UserDao) Proxy.newProxyInstance(AOPTest.class.getClassLoader(), new Class[]{UserDao.class}, new UserDaoProxy(userDao));
        int add = o.add(1, 2);
        System.out.println(add);
    }
    @Test
    public void context2(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        Game game = context.getBean("game", Game.class);
        game.play();
    }

}

class UserDaoProxy implements InvocationHandler{
    /*
     * 把创建的是谁的代理对象传递富哦来
     */

    private Object obj;

    public UserDaoProxy(Object obj) {
        this.obj = obj;
    }

    //增强的逻辑

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法之前
        System.out.println("方法之前执行");
        System.out.println("方法名："+method.getName() + "，参数："+ Arrays.toString(args));

        //被增强的方法执行
        Object res = method.invoke(obj, args);
        //方法之后
        System.out.println("方法之后执行");
        return res;
    }
}