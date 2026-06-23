package com.his.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.his.pojo.DiagnoseICD;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository  // spring bean
public interface DiagnoseICDRepository extends CrudRepository<DiagnoseICD, String> {

	
    List<DiagnoseICD> findAll();

}
