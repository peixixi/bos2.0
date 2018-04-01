package cn.itcast.service.base.impl;

import cn.itcast.dao.base.CourierRepository;
import cn.itcast.domain.Courier;
import cn.itcast.service.base.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierRepository courierRepository;

    @Override
    public void save(Courier courier) {
        courierRepository.save(courier);
    }

    @Override
    public Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable) {
        Page<Courier> pages=courierRepository.findAll(specification,pageable);
        return pages;
    }

    @Override
    public void delCourier(String[] idd) {
        for(String id:idd){
            int id1=Integer.parseInt(id);
            courierRepository.delCourier(id1);
        }
    }
}
