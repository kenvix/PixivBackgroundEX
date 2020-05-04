package com.kenvix.pixiv.driver;

import java.lang.reflect.Method;
import java.util.stream.IntStream;

public class Event {
    private Object object;
    private String methodName;
    private Object[] params;
    private Class[] paramTypes;

    public Event(Object object, String method, Object...args) {
        this.object = object;
        this.methodName = method;
        this.params = args;
        contractParamTypes(this.params);
    }

    private void contractParamTypes(Object[] params) {
        this.paramTypes = new Class[params.length];
        IntStream.range(0, params.length).forEach(i -> this.paramTypes[i] = params[i].getClass());
    }

    public void invoke() throws Exception {
        Method method = object.getClass().getMethod(this.methodName, this.paramTypes);
        if (null == method)
            return;
        method.invoke(this.object, this.params);
    }
}
