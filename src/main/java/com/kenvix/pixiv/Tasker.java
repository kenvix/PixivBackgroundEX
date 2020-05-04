package com.kenvix.pixiv;

import com.kenvix.pixiv.driver.Taskable;

import java.util.HashSet;

public class Tasker<T extends Taskable> implements Runnable {
    @FunctionalInterface
    interface StartEvent {
        void onStart();
    }

    private int sleep = 0;
    private T driver;
    private HashSet<StartEvent> onStartEvents = new HashSet<>();

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
        (new Thread(this, driver.getClass().getSimpleName())).start();
        callStartEvents();
        return driver;
    }

    public T start(String name) {
        (new Thread(this, name)).start();
        callStartEvents();
        return driver;
    }

    public Tasker addStartEvent(StartEvent event) {
        this.onStartEvents.add(event);
        return this;
    }

    public Tasker removeStartEvent(StartEvent event) {
        this.onStartEvents.remove(event);
        return this;
    }

    private Tasker callStartEvents() {
        for (StartEvent event: onStartEvents)
            event.onStart();
        return this;
    }
}
