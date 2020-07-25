package com.lxseason.mybatis.session;

import com.lxseason.mybatis.config.Configuration;
import com.lxseason.mybatis.config.MappedStatment;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * 1.把配置信息加载到内存
 * 2.生产sqlsession
 */
public class SqlSessionFactory {
    public static final String DB_CONFIG_FILE ="db.properties";
    public static final String MAPPER_CONFIG_LOCATION="mappers";
    private Configuration conf = new Configuration();

    public SqlSessionFactory(){
        loadDbInfo();
        loadMappersInfo();
    }

    private void loadDbInfo(){
        InputStream is = SqlSessionFactory.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        conf.setJdbcDriver(pro.get("jdbc.driver").toString());
        conf.setJdbcPassword(pro.get("jdbc.password").toString());
        conf.setJdbcUrl(pro.get("jdbc.url").toString());
        conf.setJdbcUserName(pro.get("jdbc.username").toString());
    }

    //加载指定文件夹下所有的mapper.xml
    private void loadMappersInfo(){
        URL resources = null;
        resources = SqlSessionFactory.class.getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
        File mappers = new File(resources.getFile());//获取指定文件夹信息
        if(mappers.isDirectory()){
            File[] listFiles = mappers.listFiles();
            for(File file : listFiles){
                loadMapperInfo(file);
            }
        }
    }

    private  void loadMapperInfo(File file){
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取根节点元素对象（mapper）
        Element root = document.getRootElement();
        String namespace = root.attribute("namespace").getData().toString();
        List<Element> selects = root.elements("select");
        for(Element ele :selects){
            MappedStatment mappedStatment = new MappedStatment();
            String id = ele.attribute("id").getData().toString();
            String resultType = ele.attribute("resultType").getData().toString();
            String sql = ele.getData().toString();
            String sourceId = namespace + "." + id;
            mappedStatment.setSourceId(sourceId);
            mappedStatment.setResultType(resultType);
            mappedStatment.setSql(sql);
            mappedStatment.setNamespace(namespace);
            conf.getMappedStatementMap().put(sourceId,mappedStatment);
        }
    }

    public SqlSession openSqlSession(){
        return  new DefaultSqlSession(conf);
    }
}
