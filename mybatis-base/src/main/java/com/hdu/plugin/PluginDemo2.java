package com.hdu.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author raptor
 * @description PluginDemo2
 * @date 2021/4/22 10:00
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args = java.sql.Statement.class )
})
public class PluginDemo2 implements Interceptor {
    /*
        拦截目标对象的执行
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("PluginDemo2 intercept-------"+invocation.getMethod());
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
        System.out.println("PluginDemo2 plugin-----"+target);
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