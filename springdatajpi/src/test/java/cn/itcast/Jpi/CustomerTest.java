package cn.itcast.Jpi;

import cn.itcart.domain.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CustomerTest {



    @Test
    public void addCustomerTest(){
        //1通过persistence创建一个工厂，读取配置单元信息
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //2就是创建工厂管理者
        EntityManager manager = factory.createEntityManager();
        //3获取事务对象
        EntityTransaction transaction = manager.getTransaction();
        //4开启事务
        transaction.begin();
        System.out.println("------------------------------------------------------");
        Customer customer = new Customer();
        customer.setCustName("张山");
        customer.setCustAddress("北京");
        customer.setCustIndustry("旅游");
        customer.setCustLevel("vip");
        customer.setCustPhone("13600132258");
        customer.setCustSource("网络");
        manager.persist(customer);

        System.out.println("-------------------------------------------------------");
        //5提交事务
        transaction.commit();
        //6关闭资源

        manager.close();
        factory.close();


    }
}
