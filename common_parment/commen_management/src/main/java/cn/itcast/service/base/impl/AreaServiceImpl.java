package cn.itcast.service.base.impl;

import cn.itcast.dao.base.AreaRepository;
import cn.itcast.domain.Area;
import cn.itcast.service.base.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;
    @Override
    public void saveBatch(List<Area> areas) {
     areaRepository.save(areas);
    }
}
