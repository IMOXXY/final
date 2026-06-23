// 实现类 DoctorOrderService.java
package com.his.service;

import com.his.pojo.DoctorOrder;
import com.his.pojo.User;
import com.his.pojo.dto.DoctorOrderDto;
import com.his.pojo.dto.UserDto;
import com.his.repository.DoctorOrderRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorOrderService implements IDoctorOrderService {

    @Autowired
    private DoctorOrderRepository repository;

    @Override
    public DoctorOrder add(DoctorOrderDto dto) {
        DoctorOrder order = new DoctorOrder();
        order.setRid(dto.getRid());
        order.setOrdername(dto.getOrdername());
        order.setOrdertype(dto.getOrdertype());
        order.setPrescriptiontime(dto.getPrescriptiontime());
        order.setOrderprice(dto.getOrderprice());
        order.setIspaid(dto.getIspaid());
        order.setDrugSpecifications(dto.getDrugSpecifications());
        order.setOrderallprice(dto.getOrderallprice());
        order.setTakeMedicine(dto.getTakeMedicine());
        order.setFrequency(dto.getFrequency());
        order.setTotalOrder(dto.getTotalOrder());
        order.setPatiid(dto.getPatiid());
        return repository.save(order);
    }

    @Override
    public List<DoctorOrder> getByRid(String rid) {
        return repository.findByRid(rid);
    }
    
    @Override
    public Iterable<DoctorOrder> findAll() {
    	return repository.findAll();
    }
    
    @Override
    public DoctorOrder edit(DoctorOrderDto order) {
    	DoctorOrder doctorOrderPojo=new DoctorOrder();
        BeanUtils.copyProperties(order,doctorOrderPojo);

        return repository.save(doctorOrderPojo);
    }
    
    @Override
    public void markAsPaid(Integer orderId) {
        repository.findById(orderId).ifPresent(order -> {
            order.setIspaid(true);
            repository.save(order);
        });
    }
    
}
