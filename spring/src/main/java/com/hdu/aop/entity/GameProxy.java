package com.hdu.aop.entity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author raptor
 * @description GameProxy
 * @date 2021/4/14 17:59
 */

@Aspect
@Component
public class GameProxy {

    @Pointcut(value = "execution(* com.hdu.aop.entity.Game.play(..))")
    public void pointDemo(){}

    @Before(value = "pointDemo()")
    public void Before(){
        System.out.println("before");
    }
    @After(value = "pointDemo()")
    public void After(){
        System.out.println("after");
    }
    @AfterReturning(value = "execution(* com.hdu.aop.entity.Game.play(..))")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    @AfterThrowing(value = "execution(* com.hdu.aop.entity.Game.play(..))")
    public void AfterThrowing(){
        System.out.println("AfterThrowing");
    }
    @Around(value = "execution(* com.hdu.aop.entity.Game.play(..))")
    public void Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around之前");
        //被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("Around之后");
    }
}
