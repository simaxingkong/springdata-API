package cn.itcast.Jpi;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//详解EntityManagerFactory对象
/*功能:
      创建EntityManager对象
  注意:
      1.EntityManagerFactory对象消耗内存资源较大
      2.EntityManagerFactory线程安全
   结论: 确保一个项目中只创建一个EntityManagerFactory
 */
public class EntityMangerFactoryDemo {

    @Test
    public void fun1() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();
    }
}
