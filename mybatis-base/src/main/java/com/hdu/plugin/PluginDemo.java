package com.hdu.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author raptor
 * @description PluginDemo
 * @date 2021/4/21 18:30
 */
/*
    1、编写Interceptor的实现类
    2、使用@Intercepts注解完成插件签名
    3、将写好的插件注册到全局配置文件中
 */
/*
    完成插件签名：
        告诉mybatis当前插件用来拦截那个对象的那个方法
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args = java.sql.Statement.class )
})
public class PluginDemo implements Interceptor {
    /*
        拦截目标对象的执行
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("PluginDemo intercept-------"+invocation.getMethod());
        Object target = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println(value);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name","疏忽转");
        map.put("id",3);
        metaObject.setValue("parameterHandler.parameterObject",map);
        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }
    /*
        包装目标对象，为目标创建一个代理对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("PluginDemo plugin-----"+target);
        //可以借助Plugin.wrap方法来使用当前Interceptor包装我们目标对象
        Object wrap = Plugin.wrap(target, this);
        //返回为当前target创建的动态代理
        return wrap;
    }

    /*
        将插件注册时的properties属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置信息："+properties);
    }
}
