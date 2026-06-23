package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.pojo.HospitalDepartment;
import com.his.repository.HospitalDepartmentRepository;

@Service    // spring的bean
public class HospitalDepartmentService implements IHospitalDepartmentService {

    @Autowired
    HospitalDepartmentRepository hospitalDepartmentRepository;

   

    @Override
    public List<HospitalDepartment> findAll() {

        return hospitalDepartmentRepository.findAll();
    }

    

}
