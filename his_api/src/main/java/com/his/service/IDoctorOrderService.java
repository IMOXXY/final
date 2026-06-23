// 接口 IDoctorOrderService.java
package com.his.service;

import com.his.pojo.DoctorOrder;
import com.his.pojo.dto.DoctorOrderDto;

import java.util.List;

public interface IDoctorOrderService {
    DoctorOrder add(DoctorOrderDto dto);
    List<DoctorOrder> getByRid(String rid);
    
    Iterable<DoctorOrder> findAll();
    
    DoctorOrder edit(DoctorOrderDto order);
    
    void markAsPaid(Integer orderId);
    
}
