package com.his.service;

import com.his.pojo.DoctorOrderDict;
import java.util.List;

public interface IDoctorOrderDictService {

    List<DoctorOrderDict> findAll();

    List<DoctorOrderDict> findByOrdertype(String type);

    List<DoctorOrderDict> searchByOrdername(String keyword);

    List<DoctorOrderDict> searchByInputstr(String keyword);

    List<DoctorOrderDict> searchByKeyword(String ordertype,String keyword);
}
