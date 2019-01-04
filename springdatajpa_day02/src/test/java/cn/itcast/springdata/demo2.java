package cn.itcast.springdata;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class demo2 {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 这是JPQL语法
     * 的模糊查询
     */
    @Test
    public void getlikequery(){
        List<Customer> list = customerDao.gethaha(3L, "%孙悟空%");
        System.out.println(list);
    }

    @Test
    @Transactional//这是更新，需要添加事务，必须在实现类中
    @Commit
    public void getupdate(){
         customerDao.update(2L,"小新画展");

    }

    /**
     * 这是nativeSQL是原始的SQL语句
     */
    @Test
    public void getlikequery2(){
        List<Customer> hehe2 = customerDao.getHehe2(1L, "%孙%");
        System.out.println(hehe2);
    }

    @Test
    @Transactional//这是更新，需要添加事务，必须在实现类中
    @Commit
    public void getupdate2(){
        customerDao.update2(6L,"小新画展");

    }

    @Test
    public void getlikequery3(){
        List<Customer> hehe2 = customerDao.findBycustNameLike( "%孙%");
        System.out.println(hehe2);
        List<Customer> list = customerDao.findByCustIdBetween(1L, 6L);
        System.out.println(list);
        List<Customer> cuslist = customerDao.findByCustIdBetweenAndCustNameLike(2L, 5L, "%悟空%");
        System.out.println(cuslist);
    }





}
