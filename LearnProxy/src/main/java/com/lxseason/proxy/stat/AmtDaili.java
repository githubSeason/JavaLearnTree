package com.lxseason.proxy.stat;

import com.lxseason.proxy.dynamic.WomanToolsFactory;

//代理对象，包含真实对象，为真实对象的服务进行增强，
public class AmtDaili implements ManToolsFactory, WomanToolsFactory {
    public ManToolsFactory factory; //被包含的真实对象
    public WomanToolsFactory womanToolsFactory;//违反开闭原则,若要扩展功能则要修改AaDaili源码

    public AmtDaili(ManToolsFactory factory){
        this.factory = factory;
    }

    public void saleManTools(String size) {
        doSomethingBefore();
        factory.saleManTools(size);
        doSomethingEnd();
    }
    //前置增强
    private void doSomethingBefore(){
        System.out.println("售前服务：分析需求，产品推荐");
    }
    //后置增强
    private void doSomethingEnd(){
        System.out.println("售后服务：精美包装，邮递货物");
    }

    public void saleWomanTools(float length) {
        womanToolsFactory.saleWomanTools(length);
    }
}
