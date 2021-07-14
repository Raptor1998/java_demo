package com.hdu.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author raptor
 * @description CacheController
 * @date 2021/7/13 11:26
 */
@RestController
@RequestMapping("/redis")
public class CacheController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    Redisson redisson;
//    @Autowired
//    DataSourceTransactionManager dataSourceTransactionManager;

//    @Transaction
//    public void lock() {
//        //手动开启事务
//        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
//        try {
//            while (true) {
//                boolean flag = this.getLock(key);
//                if (flag) {
//                    insert();
//                    //手动提交事务
//                    dataSourceTransactionManager.commit(transactionStatus);
//                }
//            }
//        } catch (Exception e) {
//            //手动回滚事务
//            dataSourceTransactionManager.rollback(transactionStatus);
//        }
//    }

    @RequestMapping(value = "/sub", method = RequestMethod.POST)
    public String subSurplus() {

        String lockKey = "product_01";
        String clientId = UUID.randomUUID().toString();
        RLock redissonLock = redisson.getLock(lockKey);
        try {
        /*
        synchronized (this)
        1. 在分布式环境下  多个jvm  进程级别锁
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "lalal");
        stringRedisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);
        stringRedisTemplate.delete(lockKey);
        1. 进程执行到  设置过期时间挂掉，则无法解锁
        2. 若A需要执行 15s, 代码未执行完成，锁过期，则后续请求B进入，加B锁，5s后，A执行到删除锁，删掉B加的锁
            同理，C进来，上锁，B此时执行了5s，  套娃下去，总会出现，多个请求，同时在做库存相关操作
        String clientId = UUID.randomUUID().toString();
        if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        1. 引入进程识别，只能删除我自己加的锁

        在主线程处理业务逻辑时，开辟新的timer线程，强行为locakKey续命，最好设置为过期时间的1/3

        */


//            Boolean result = false;
//            while (!result){
//                result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
//            }
//            if (!result) {
//                return "error";
//            }
//            https://www.cnblogs.com/youngdeng/p/12883790.html
            redissonLock.lock();
            boolean b = redissonLock.tryLock(100, TimeUnit.SECONDS);
            System.out.println(b);
            if (b == false) {
                System.out.println("等待时间超时");
                return "失败了";
            }
            int surplus = Integer.parseInt(stringRedisTemplate.opsForValue().get("shangpin"));
            if (surplus > 0) {
                double random = Math.random();
                int time = (int) (random * 1000);
                Thread.sleep(time);
                int temp = surplus - 1;
                stringRedisTemplate.opsForValue().set("shangpin", temp + "");
                System.out.println("扣减成功，当前剩余" + temp);
                return "成功";

            } else {
                System.out.println("扣减失败");
                return "失败";
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("异常");
            return "异常";
        } finally {
            System.out.println("释放锁");
            if (redissonLock.isLocked()) {  //是否锁定状态
                if (redissonLock.isHeldByCurrentThread()) {  //是否当前执行线程的锁
                    redissonLock.unlock();
                } else {
                    System.out.println("不是当前线程锁");
                }
            } else {
                System.out.println("锁已经过期");
            }
//            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
//                stringRedisTemplate.delete(lockKey);
//            }
        }
    }


    @RequestMapping(value = "/sub2", method = RequestMethod.POST)
    public String subSurplus2() throws InterruptedException {

        String lockKey = "product_01";
        String clientId = UUID.randomUUID().toString();
        try {
            Boolean result = false;
            //不等锁，直接返回失败
//            if (!result) {
//                return "error";
//            }
            //等待锁
            while (!result) {
                result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
            }
            int surplus = Integer.parseInt(stringRedisTemplate.opsForValue().get("shangpin"));
            if (surplus > 0) {
                double random = Math.random();
                int time = (int) (random * 1000);
                Thread.sleep(time);
                int temp = surplus - 1;
                stringRedisTemplate.opsForValue().set("shangpin", temp + "");
                System.out.println("扣减成功，当前剩余" + temp);
                return "成功";

            } else {
                System.out.println("扣减失败");
                return "失败";
            }

        } finally {
            System.out.println("释放锁");
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
    }


}
