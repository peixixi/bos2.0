package cn.itcast.dao.base;


import cn.itcast.domain.Standard;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StandardRepository extends JpaRepository<Standard,Integer> {

   /* public void update(String s, int i);*/
}
