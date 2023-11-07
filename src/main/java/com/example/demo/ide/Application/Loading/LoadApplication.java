package com.example.demo.ide.Application.Loading;

import org.springframework.stereotype.Component;

@Component
public class LoadApplication extends Thread {
    public void run() {
        System.out.println(this.getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.getState());
    }
}
