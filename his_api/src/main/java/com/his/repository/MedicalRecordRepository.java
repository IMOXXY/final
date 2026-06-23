package com.his.repository;

import com.his.pojo.MedicalRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Integer> {

    List<MedicalRecord> findByPid(String pid);
    
    List<MedicalRecord> findByRid(String rid);

    List<MedicalRecord> findByPnameContaining(String pname);
}
