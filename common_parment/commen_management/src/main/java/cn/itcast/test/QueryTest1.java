package cn.itcast.test;

import cn.itcast.dao.base.StandardRepository;
import cn.itcast.domain.Standard;
import cn.itcast.service.base.StandardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class QueryTest1 {


    @Autowired
    private StandardRepository standardRepository;
     @Test
    public void test1(){
         Standard standard=standardRepository.findOne(1);
         System.out.println(standard);
     }

     /*@Test
     @Query(value="update Standard set name=?2 where id=?1")
     @Modifying
    public void test2(){
         standardRepository.update("11",1);

     }*/
}
