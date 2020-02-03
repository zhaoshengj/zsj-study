package com.zsj.interview.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    interface IHello{
        void sayHello();
    }

    static class Hello implements IHello{


        @Override
        public void sayHello() {
            System.out.println("hello worid");
        }
    }

    static class DynamicProxy implements InvocationHandler{

        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),originalObj.getClass().getInterfaces(),this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println(" welcome");
            return method.invoke(originalObj,args);
        }
    }


    public static void main(String[] args) {

        IHello bind = (IHello) new DynamicProxy().bind(new Hello());
        bind.sayHello();
    }
}
