package com.kenvix.pixiv.driver;

public interface Taskable<T> {
    T start(int time);
}
