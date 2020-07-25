package com.lxseason.mybatis.reflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionUtil {
    /**
     * 为指定的bean的propName属性设置value
     * @param bean 目标对象
     * @param propName 对象的属性名
     * @param value 值
     */
    public static void setPropToBean(Object bean ,String propName ,Object value){
        Field f;
        try {
            f = bean.getClass().getDeclaredField(propName);//获取对象的指定属性
            f.setAccessible(true);//将字段设置为可通过反射进行访问
            f.set(bean ,value);//给属性设值
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从resultSet中读取一行数据，并填充至指定的实体bean
     * @param entity 待填充的实体bean
     * @param resultSet 从数据库加载的数据
     * @throws SQLException
     */
    public static void setPropToBeanFromResultSet(Object entity , ResultSet resultSet)throws SQLException {
        Field[] declaredFiels = entity.getClass().getDeclaredFields();//通过反射获取对象的所有属性
        for(int i =0 ; i<declaredFiels.length; i++){//遍历所有属性，从resultSet中取相应属性，并填充到entity对象中
            if(declaredFiels[i].getType().getSimpleName().equals("String")){//如果是字符串类型数据
                setPropToBean(entity ,declaredFiels[i].getName(),resultSet.getString(declaredFiels[i].getName()));
            }
            else if(declaredFiels[i].getType().getSimpleName().equals("Integer")){//如果是字符串类型数据
                setPropToBean(entity ,declaredFiels[i].getName(),resultSet.getInt(declaredFiels[i].getName()));
            }
            else if(declaredFiels[i].getType().getSimpleName().equals("Long")){//如果是字符串类型数据
                setPropToBean(entity ,declaredFiels[i].getName(),resultSet.getLong(declaredFiels[i].getName()));
            }
        }
    }

//    public stat void main(String[] args) {
//        TUser user = new TUser();
//        ReflectionUtil.setPropToBean(user, "userName", "alex");
//        System.out.println(user);
//    }
}
