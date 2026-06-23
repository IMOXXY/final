package com.his.service;

import com.his.pojo.DoctorOrderDict;
import com.his.pojo.dto.DoctorOrderDictDto;
import com.his.repository.DoctorOrderDictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorOrderDictService implements IDoctorOrderDictService {

    @Autowired
    private DoctorOrderDictRepository repository;

    @Override
    public List<DoctorOrderDict> findAll() {
        return repository.findAll();
    }

    @Override
    public List<DoctorOrderDict> findByOrdertype(String type) {
        return repository.findByOrdertype(type);
    }

    @Override
    public List<DoctorOrderDict> searchByOrdername(String keyword) {
        return repository.findByOrdernameContaining(keyword);
    }

    @Override
    public List<DoctorOrderDict> searchByInputstr(String keyword) {
        return repository.findByInputstrContaining(keyword);
    }

    @Override
    public List<DoctorOrderDict> searchByKeyword(String ordertype,String keyword) {
        return repository.searchByKeyword(ordertype,keyword);
    }
}
