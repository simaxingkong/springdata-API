package cn.itcart.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutils {

    //将变量提取到作用域最大，这样静态代码块和方法中都能获取到。
    private static EntityManagerFactory factory;
    static {
        //将创建的工厂放到静态代码块中，这样只需要创建一次，减少性能浪费
        factory = Persistence.createEntityManagerFactory("abc");
    }

    public static EntityManager getEntityManager(){

        EntityManager manager = factory.createEntityManager();
        return manager;
    }
}
