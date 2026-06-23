package com.his.repository;

import com.his.pojo.DoctorOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorOrderRepository extends CrudRepository<DoctorOrder, Integer> {

    // 根据挂号编号查询多个医嘱
    List<DoctorOrder> findByRid(String rid);
}
