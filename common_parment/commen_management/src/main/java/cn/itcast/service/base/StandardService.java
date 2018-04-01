package cn.itcast.service.base;

import cn.itcast.domain.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StandardService {
    public void save(Standard standard);


    Page<Standard> findAll(Pageable pageable);

    List<Standard> findAll();
}
