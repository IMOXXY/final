package com.his.repository;

import com.his.pojo.LabCheck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabCheckRepository extends CrudRepository<LabCheck, Integer> {

    List<LabCheck> findByDoctName(String doctName);

    List<LabCheck> findByPid(String pid);

    List<LabCheck> findByRid(String rid);

    List<LabCheck> findByDoctNameAndApplyStatus(String doctName, String applyStatus);
}
