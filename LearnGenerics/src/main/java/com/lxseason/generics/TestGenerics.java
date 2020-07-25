package com.lxseason.generics;

import com.lxseason.generics.demo.Animal;
import com.lxseason.generics.demo.Dog;
import com.lxseason.generics.demo.MyGenericType;
import com.lxseason.generics.demo.WildDog;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型(generics)提供了编译时类型安全检测机制，其本质是参数化类型。
 * Object类型实现参数的“任意化”，但需做显示强制类型转换，存在人为的安全隐患。
 * 通配符：？表示不确定类型
 * 类型参数：
 * E(element) 一般表示自定义类型
 * T(Type)一般表示java的基础类型
 * K V一般表示键值中的Key，Value
 */
public class TestGenerics {
    public static void main(String[] args) {
        testGenericType();
    }

    public static void testGenericType(){
        MyGenericType noType =new MyGenericType();
        noType.setT("test no type");
        //不指定类型，需强制类型转换
        String a =(String) noType.getT();
        System.out.println(a);

        //指定类型，不需要强制类型转换
        MyGenericType<String> withType = new MyGenericType<String>();
        withType.setT("test with type");
        a =withType.getT();
        System.out.println(a);
    }

    /**
     * 测试无界通配符?的使用
     * 上界通配符<? extends E>,表示参数化的类型可以是E或者E的子类
     * 下界通配符<? super E>,表示参数化的类型可以是E，或者是E的父类型，直至Object
     * 类型参数列表中如果有多个类型参数上界，用逗号分开
     */
    public static void testUnboundedWildcard (){
        List<Dog> dogs =new ArrayList<Dog>();
        countLegs(dogs);
//        countLegs1(dogs); //会报错
        List<Animal> animals = new ArrayList<Animal>();
        test(animals ,dogs);
        List<WildDog> wildDogs = new ArrayList<WildDog>();
//        test(wildDogs,dogs);//会报错

    }
    public static int countLegs1(List<Animal> animals){
        int legNun =0 ;
        for (Animal animal :animals){
            legNun +=animal.countLegs();
        }
        return legNun;
    }
    public static int countLegs(List<? extends Animal> animals){
        int legNun =0 ;
        for (Animal animal :animals){
            legNun +=animal.countLegs();
        }
        return legNun;
    }

    /**
     * ? 和 T 的区别：
     * 二者都表示不确定的类型，区别在于可对T进行操作，但对？不行
     * T是一个确定的类型，通常用于泛型类和泛型方法的定义
     * ？是一个不确定的类型，通常用于泛型方法的调用代码和形参，不能用于定义类和泛型方法
     */
    public static<T> void test(List<? super T> animals ,List<T> dogs){
        for(T dog : dogs){
            animals.add(dog);
        }
        T t = dogs.get(0);  //可以对T进行操作
//        ? animals = animals.get(0);//会报错，不能操作
    }
//    public static void bark(? dog){
//
//    }

    /**
     * ? 和 T的区别：
     * 通过T可以来确保泛型参数的一致性，通配符？则不能保证参数类型相同
     * 类型参数可以使用 &符号设定多重边界，如 T extends ClassA & ClassB,指定T必须是ClassA和ClassB的公有子类型
     * 通配符是一个不确定的类型，所以不能进行多重限定
     * 通配符可以使用超类限定，？ super ClassA 、？extends ClassA 都行
     * 而类型参数不能使用超类限定，只有一种限定方式：T extends ClassA
     */
    public <T extends Number> void test1(List<T> dest ,List<T> src){

    }
    public void test2(List<? extends Number> dest ,List<? extends  Number> src){
    }
    /**
     * ? 和 T的区别：
     * TestClass<T>在实例化的时候，T要替换成具体类，
     * TestClass<?>是个通配泛型，？可以代表人和类，所以主要用于声明时的限制情况，
     * 所以当不知道声明什么类型Class时，可以Class<?> clazz
     */
}
