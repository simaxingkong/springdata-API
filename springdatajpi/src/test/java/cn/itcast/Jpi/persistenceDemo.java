package cn.itcast.Jpi;

import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
* 这是持久化对象
* 创建读取配置文件的信息*/
public class persistenceDemo {

    @Test
    public void persisTest(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
    }
}
