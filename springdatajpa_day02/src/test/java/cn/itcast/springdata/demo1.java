package cn.itcast.springdata;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class demo1 {

    //1引入Dao层接口
    @Autowired
    private CustomerDao cd;

    /**
     * 保存用户
     */
    @Test
    public void fun1(){
        Customer customer = new Customer();
        customer.setCustName("猪八贱泡脚城");
        cd.save(customer);
    }

    //查询,通过id查询
    @Test
    public void fun2(){

        Customer c = cd.findOne(3L);
        System.out.println(c);
    }

    @Test
    //这是查询全部数据
    public void fun2_1(){

        List<Customer> list = cd.findAll();
        System.out.println(list);
    }

    @Test
    //这是通过传过来的ids进行条件查询
    public void fun2_2(){
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(5L);

        List<Customer> customerList = cd.findAll(ids);
        System.out.println(customerList);
    }

    @Test
    //对查到的数据进行分页处理
    public void fun2_3(){

        Page<Customer> page = cd.findAll(new PageRequest(0, 2));

        List<Customer> content = page.getContent();//这是每页的记录条数
        long totalElements = page.getTotalElements();//这是数据库中的总记录数
        int totalPages = page.getTotalPages();//这是获取总页数
        int number = page.getNumber();
        System.out.println(content);
        System.out.println(totalElements);
        System.out.println(number);
    }

    @Test
    //修改，先查，在修改数据
    public void fun3(){

        Customer one = cd.findOne(5L);
        one.setCustName("大黄养殖场");
        cd.save(one);

    }

    @Test
    //删除数据--》先查后删
    public void fun4(){

        Customer one = cd.findOne(2L);
        cd.delete(one);


    }

    @Test
    //判断是否存在
    public void fun5(){

        System.out.println(cd.exists(5L));
        System.out.println("=======================");
        System.out.println(cd.exists(8L));


    }

    @Test
    //统计有多少条数据
    public void fun6(){

        System.out.println(cd.count());
        System.out.println("=======================");


    }

}
