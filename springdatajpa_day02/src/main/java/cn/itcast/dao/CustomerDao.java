package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//这里是继承一个springdatajpa接口，就可以不用创建实现类，这个内部会自动生成实现类，用到的技术动态代理
public interface CustomerDao extends JpaRepository<Customer,Long> ,JpaSpecificationExecutor<Customer> {

    //下面就是Dao层的拓展方法
    // 1,jpql语句查询
    //查询客户名称中包含指定值的客户
    @Query("from Customer where custName like ?2 or custId =?1")
    List<Customer> gethaha(Long id,String custName);

    //通过id去更新用户名
    @Modifying //如果自己扩充的方法需要修改数据库,一定要加上该注解
    @Query("update Customer set custName=?2 where custId=?1")
    void update(Long id,String custName);

    //2，SQL查询
    //nativeQuery属性:是否使用原生SQL
    //查询客户名称中包含指定值的客户
    @Query(value = " select * from cst_customer where custName like ?2 or custId = ?1  ",nativeQuery = true)
    List<Customer> getHehe2(Long id , String custName);

    //通过id去更新用户名
    @Modifying //如果自己扩充的方法需要修改数据库,一定要加上该注解
    @Query(value = "update cst_customer set custName=?2 where custId=?1",nativeQuery = true)
    void update2(Long id,String custName);

    //3,方法命名查询
    //方法命名规范 => findBy属性名[条件][条件连接符][条件]
    //查询客户名称中包含指定值的客户
    List<Customer> findBycustNameLike(String custName);
    //查询客户id值在指定范围内的
    List<Customer> findByCustIdBetween(Long min,Long max);
    //查询客户名称包含指定值并且id值在指定范围内
    List<Customer> findByCustIdBetweenAndCustNameLike(Long min,Long max,String custName);

}
