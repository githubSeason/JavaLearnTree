package com.lxseason.proxy.stat;

/**
 * 真正提供服务的类
 */
public class ManToolFactoryImpl implements ManToolsFactory {
    public void saleManTools(String size) {
        System.out.println("工厂(ManTool) -投产需求，已生产size ="+size+" 的物品");
    }
}
