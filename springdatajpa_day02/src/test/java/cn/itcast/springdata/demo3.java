package cn.itcast.springdata;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class demo3 {

    @Autowired
    private CustomerDao customerDao;

    @Test
    //根据id查询客户
    public void fun1(){
        //specification接口 => 简单说,就是用于表达查询条件的接口,用匿名内部类，
        Customer customer = customerDao.findOne(new Specification<Customer>() {
            @Override
         /*表达条件的方法 => Predicate
            参数1:用于表达实体属性的对象
            参数2:(可选参数)拼装查询语句(条件|排序|分组...)
            参数3:用于表达条件|排序
          */
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            /*
            cb.equal方法 =>精确查询
                  root.get("custId").as(Long.class) => 根据custId属性查询
                  6L =>查询的值是6L
             */
                 return cb.equal(root.get("custId").as(Long.class),5L);
             }
         });
        System.out.println(customer);
    }

    @Test
    //根据id查询客户 => 使用拉姆达表达式
    public void fun2(){
        //specification接口 => 简单说,就是用于表达查询条件的接口
        Customer customer = customerDao.findOne(
                (root,query,cb)->cb.equal(root.get("custId").as(Long.class),5L)
        );
        System.out.println(customer);
    }

    @Test
    //多条件&多返回值
    public void fun3(){
       customerDao.findAll(new Specification<Customer>() {
           @Override
           public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
               Predicate p1 = cb.between(root.get("custId").as(Long.class), 2L, 6L);
               Predicate p2 = cb.like(root.get("custName").as(String.class), "%空%");

               return cb.and(p1,p2);
           }
       }).forEach(c -> System.out.println(c));
    }

    //多条件&多返回值&排序
    @Test
    public void fun4(){
        List<Customer> list = customerDao.findAll(
                (root, query, cb) -> {
                    Predicate p1 = cb.between(root.get("custId").as(Long.class), 1L, 5L);
                    Predicate p2 = cb.like(root.get("custName").as(String.class), "%养%");
                    Predicate p3 = cb.and(p1, p2);

                    return query.
                            where(p3).//拼条件
                            orderBy(cb.desc(root.get("custId").as(Long.class))).//这是排序的拼接
                            getRestriction();//这是结束标志
                }
        );
        System.out.println(list);

    }

    //多条件&多返回值&排序&分页
    @Test
    public void fun5(){
        Page<Customer> page = customerDao.findAll(
                (root, query, cb) -> {
                    Predicate p1 = cb.between(root.get("custId").as(Long.class), 1L, 5L);
                    Predicate p2 = cb.like(root.get("custName").as(String.class), "%养%");
                    Predicate p3 = cb.and(p1, p2);

                    return query.
                            where(p3).//拼条件
                            orderBy(cb.desc(root.get("custId").as(Long.class))).//这是排序的拼接
                            getRestriction();//这是结束标志
                },
                new PageRequest(1, 2));

        List<Customer> content = page.getContent();
        long total = page.getTotalElements();
        System.out.println(content);
        System.out.println(total);

    }

}
