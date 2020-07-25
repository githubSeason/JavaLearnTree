package com.lxseason.generics.demo;
/**
 * ? 和 T的区别：
 * TestClass<T>在实例化的时候，T要替换成具体类，
 * TestClass<?>是个通配泛型，？可以代表人和类，所以主要用于声明时的限制情况，
 * 所以当不知道声明什么类型Class时，可以Class<?> clazz
 */
public class TestClass1<T> {
    public Class<?> clazz1;
    public Class<T> clazz;
}
