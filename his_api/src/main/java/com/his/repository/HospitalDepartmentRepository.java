package com.his.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.his.pojo.HospitalDepartment;
import java.util.List;

@Repository     // spring bean
public interface HospitalDepartmentRepository extends CrudRepository<HospitalDepartment,String> {

	// 查询所有部门
	List<HospitalDepartment> findAll();
}
