package com.lxseason.mybatis.executor;

import com.lxseason.mybatis.config.Configuration;
import com.lxseason.mybatis.config.MappedStatment;
import com.lxseason.mybatis.reflection.ReflectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现jdbc规范的底层SQL查询
 */
public class DefaultExecutor implements  Executor {
    private Configuration conf;

    public DefaultExecutor(Configuration conf) {
        this.conf = conf;
    }

    /**
     *通过conf中配置的数据库连接信息，以及传入mappedstatemen中指定的mapper
     */
    @Override
    public <E> List<E> query(MappedStatment ms, Object parameter) {
        List<E> ret = new ArrayList<E>();//定义返回结果集
        try {
            Class.forName(conf.getJdbcDriver());//加载驱动程序
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            //获取连接，从MappedStatement中获取去数据库信息
            connection = DriverManager.getConnection(conf.getJdbcUrl(),conf.getJdbcUserName(),conf.getJdbcPassword());
            //创建prepareStatement，从MappedStatement中获取sql语句
            preparedStatement = connection.prepareStatement(ms.getSql());
            //处理sql语句中的占位符
            parameterize(preparedStatement ,parameter);
            //执行查询操作获取resultSet
            resultSet = preparedStatement.executeQuery();
            //将结果集通过反射技术，填充到List中
            handlerResultSet(resultSet, ret ,ms.getResultType());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    private void parameterize(PreparedStatement preparedStatement ,Object parameter) throws SQLException{
        if(parameter instanceof Integer){
            preparedStatement.setInt(1,(Integer) parameter);
        }else if(parameter instanceof  Long){
            preparedStatement.setLong(1,(long)parameter);
        }else if(parameter instanceof String){
            preparedStatement.setString(1,(String)parameter);
        }
    }

    private  <E> void handlerResultSet(ResultSet resultSet ,List<E> ret,String className){
        Class<E> clazz = null;
        try {
            //通过反射获取类对象
            clazz = (Class<E>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            while (resultSet.next()){
                //通过反射实例化对象
                Object entity = clazz.newInstance();
                //使用反射工具将resultSet中的数据填充到entity中
                ReflectionUtil.setPropToBeanFromResultSet(entity, resultSet);
                //对象加入返回集合中
                ret.add((E)entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
