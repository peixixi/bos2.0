package cn.itcast.dao.base;

import cn.itcast.domain.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourierRepository extends JpaRepository<Courier,Integer>,JpaSpecificationExecutor<Courier> {
    Page<Courier> findAll(Specification<Courier> specification, Pageable pageable);
    @Query(value = "update Courier set  deltag='1'where id=?1")
    @Modifying
    public void delCourier(int id1);
}
