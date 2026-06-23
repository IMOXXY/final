package com.his.service;

import com.his.pojo.HospitalDepartment;
import java.util.List;

public interface IHospitalDepartmentService {

   
    /**
     * 获取所有科室信息
     */
    List<HospitalDepartment> findAll();
}
