package com.lxseason.proxy.utils;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProxyUtils {
    /**
     * 把根据类信息动态生成的二进制字节码保存到硬盘中，默认的是clazz目录下的params：clazz需要生成动态代理类的类
     * proxyName:是动态生成的代理类的名称
     * 使用反编译工具可以对.class文件进反编译，查看源码
     * 通过反射动态代理生成$ProxyN.class可以了解动态反射的原理
     */
    public static void generateClassFile(Class clazz ,String proxyName){
        //根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName,
                new Class[]{clazz});
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out =null;
        try{
            //保存到硬盘
            out = new FileOutputStream(paths + proxyName+".class");
            out.write(classFile);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
