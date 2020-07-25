package com.lxseason.proxy.dynamic;

public class WomanToolFactoryImpl implements WomanToolsFactory {
    public void saleWomanTools(float length) {
        System.out.println("工厂(womanTool) -投产需求，已生产length ="+length+" 的物品");
    }
}
