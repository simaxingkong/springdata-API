package cn.itcast.Jpi;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//详解Transaction对象
/*功能:
      管理事务=> 开启|提交|回滚...
 */
public class TransactionText {


    @Test
    public void tranTest(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        transaction.commit();
        transaction.rollback();

    }
}
