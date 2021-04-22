package com.hdu.aop.entity;

import org.springframework.stereotype.Component;

/**
 * @author raptor
 * @description Game
 * @date 2021/4/14 17:58
 */
@Component
public class Game {
    public void play() {
        System.out.println("play game !");
    }
}
