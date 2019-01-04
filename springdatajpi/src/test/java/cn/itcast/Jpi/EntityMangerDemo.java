package cn.itcast.Jpi;

import cn.itcart.domain.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//详解EntityManager对象
/*功能:
      JPA操作数据库的核心对象.所有增/删/改/查都通过EntityManager对象完成
 */
public class EntityMangerDemo {

    @Test
    //增
    public void fun1() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        //------------------------------------------------------------------------------------------------
        Customer c = new Customer();
        c.setCustName("黑马程序员");

        manager.persist(c);
        //------------------------------------------------------------------------------------------------
        tx.commit(); //提交

        manager.close();
    }

    @Test
    //(OID)根据主键id查询数据
    public void fun2() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        //------------------------------------------------------------------------------------------------
        Customer customer = manager.find(Customer.class, 1L);
        System.out.println("-------------------------------------------------------------------");
        System.out.println(customer);
        //------------------------------------------------------------------------------------------------
        tx.commit(); //提交

        manager.close();
    }

    @Test
    //(OID)根据主键id查询数据
    public void fun2_1() {
        /*
        * 延迟加载，在需要对数据操作的时候才取数据库查询
        * getReference
        * */

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        //------------------------------------------------------------------------------------------------
        Customer customer = manager.getReference(Customer.class,1L);//这里不会去数据库查
        System.out.println("------------------------------------");
        System.out.println(customer);//在对数据进行操作的时候才去数据库查----------也称懒加载
        //------------------------------------------------------------------------------------------------
        tx.commit(); //提交

        manager.close();
    }
    @Test
    //   修改1
    // (OID)通过主键查询出数据，然后在修改保存
    public void fun3() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        //------------------------------------------------------------------------------------------------
        Customer customer = manager.find(Customer.class, 3L);//先通过id查数据
       customer.setCustName("创智你好");
       manager.persist(customer);
        System.out.println(customer);
        //------------------------------------------------------------------------------------------------
        tx.commit(); //提交

        manager.close();
    }

    @Test
    //   修改2
    // (OID)可以直接修改,就是合并的单词merge
    public void fun3_2() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        //------------------------------------------------------------------------------------------------
        Customer customer = new Customer();
        customer.setCustId(1L);
        customer.setCustName("李旭泡脚城");//手动指定ID再修改
        manager.merge(customer);

        //------------------------------------------------------------------------------------------------
        tx.commit(); //提交

        manager.close();
    }


    @Test
    //  删除  先查 后删
    public void fun4() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        //------------------------------------------------------------------------------------------------
        Customer customer = manager.find(Customer.class, 2L);//先查

       manager.remove(customer);//后删

        //------------------------------------------------------------------------------------------------
        tx.commit(); //提交

        manager.close();
    }
}
