package cn.itcast.service.base.impl;


import cn.itcast.dao.base.StandardRepository;
import cn.itcast.domain.Standard;
import cn.itcast.service.base.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StandardServiceImpl implements StandardService {
    @Autowired
    private StandardRepository standardRepository;
    @Override
    public void save(Standard standard) {
        standardRepository.save(standard);
    }

    @Override
    public Page<Standard> findAll(Pageable pageable) {
        Page<Standard> list = (Page<Standard>) standardRepository.findAll(pageable);
        return list;
    }

    @Override
    public List<Standard> findAll() {
        List<Standard> list = standardRepository.findAll();
        return  list;
    }


}
