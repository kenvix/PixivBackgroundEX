package com.kenvix.pixiv;

import com.kenvix.pixiv.driver.Taskable;

public class Tasker<T extends Taskable> implements Runnable {
    private int sleep = 0;
    private T driver;

    Tasker(T driver) {
        this.driver = driver;
    }
    Tasker(T driver, int time) {
        sleep = time;
        this.driver = driver;
    }

    @Override
    public void run() {
        driver.start(sleep);
    }

    public T start() {
        (new Thread(this)).start();
        return driver;
    }

    public T start(String name) {
        (new Thread(this, name)).start();
        return driver;
    }
}
