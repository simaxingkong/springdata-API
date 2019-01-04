package cn.itcast.Jpi;

import cn.itcart.domain.Customer;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

//详解JPQL语句
/*

   功能: Java Persistence Query Language java持久化查询语言
         出自于hibernate中的HQL语言.JPA规范借鉴过来之后,改名为JPQL
         该语言是"面向对象"的查询语言,语法与SQL类似.
         注意的是：SQL语句的表名是demain中类名，除了查询全部数据from Customer
         其它的JPQL语句和DBUtils工具类差不多，

 */
public class JPQLDemo {

    @Test
    //查询所有客户
    public void fun1() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();

        tx.begin(); //开启
        //------------------------------------------------------------------------------------------------
        //1定义JPQL语句
        // String jpql = " select c from cn.itcast.domain.Customer c "; //查询所有客户对象
        // String jpql = " select c from Customer c "; //查询所有客户对象
        String jpql="from Customer";
        //将JPQL封装为查询对象
        Query query = manager.createQuery(jpql);
        //获取结果集
        List<Customer> list = query.getResultList();
        System.out.println(list);
        //------------------------------------------------------------------------------------------------
        tx.commit();
        manager.close();
    }

    @Test
    //条件查询 => 客户名称中包含李旭的客户 (?号占位符)
    public void fun2() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();

        tx.begin(); //开启
        //------------------------------------------------------------------------------------------------
        //1定义JPQL语句
        // String jpql = " select c from cn.itcast.domain.Customer c "; //查询所有客户对象
        // String jpql = " select c from Customer c "; //查询所有客户对象
        String jpql="from Customer where custName like ?";

        //将JPQL封装为查询对象
        Query query = manager.createQuery(jpql);
        //设置模糊查询的对象
        query.setParameter(1,"%黑马%");
        //获取结果集
        List<Customer> list = query.getResultList();
        System.out.println(list);
        //------------------------------------------------------------------------------------------------
        tx.commit();
        manager.close();
    }
    @Test
    //分页
    public void fun3() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();

        tx.begin(); //开启
        //------------------------------------------------------------------------------------------------
        //1定义JPQL语句
        // String jpql = " select c from cn.itcast.domain.Customer c "; //查询所有客户对象
        // String jpql = " select c from Customer c "; //查询所有客户对象
        String jpql="from Customer";

        //将JPQL封装为查询对象
        Query query = manager.createQuery(jpql);
        //设置分页参数,类似于limit 0,2
        query.setFirstResult(0);
        query.setMaxResults(2);
        //获取结果集
        List<Customer> list = query.getResultList();
        System.out.println(list);
        //------------------------------------------------------------------------------------------------
        tx.commit();
        manager.close();
    }

    @Test
    //排序
    public void fun4() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();

        tx.begin(); //开启
        //------------------------------------------------------------------------------------------------
        //1定义JPQL语句
        // String jpql = " select c from cn.itcast.domain.Customer c "; //查询所有客户对象
        // String jpql = " select c from Customer c "; //查询所有客户对象
        String jpql="from Customer order by custId desc";

        //将JPQL封装为查询对象
        Query query = manager.createQuery(jpql);
        //获取结果集
        List<Customer> list = query.getResultList();
        System.out.println(list);
        //------------------------------------------------------------------------------------------------
        tx.commit();
        manager.close();
    }

    @Test
    //统计 => 聚合函数(count|sum|avg|max|min)
    public void fun5() {

        //1 读取配置并获得EntityManagerFactory对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        //创建EntityManager对象
        EntityManager manager = factory.createEntityManager();

        //创建transaction对象
        EntityTransaction tx = manager.getTransaction();

        tx.begin(); //开启
        //------------------------------------------------------------------------------------------------
        //1定义JPQL语句
        // String jpql = " select c from cn.itcast.domain.Customer c "; //查询所有客户对象
        // String jpql = " select c from Customer c "; //查询所有客户对象
        String jpql1="select count(c) from Customer c";
        String jpql2="select avg(c.custId) from Customer c";
        String jpql3="select sum(c.custId) from Customer c";
        String jpql4="select max(c.custId) from Customer c";
        String jpql5="select min(c.custId) from Customer c";


        //将JPQL封装为查询对象
        Query query = manager.createQuery(jpql3);
        //执行查询.并获得结果是一个单个值，只要获取到数字，都用number转换
        Number num = (Number) query.getSingleResult();
        System.out.println(num);

        //------------------------------------------------------------------------------------------------
        tx.commit();
        manager.close();
    }
}
