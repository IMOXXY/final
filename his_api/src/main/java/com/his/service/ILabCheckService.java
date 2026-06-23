package com.his.service;

import com.his.pojo.LabCheck;
import com.his.pojo.dto.LabCheckDto;

import java.util.List;

public interface ILabCheckService {

    LabCheck add(LabCheckDto dto);

    LabCheck edit(LabCheckDto dto);

    LabCheck completeResult(LabCheckDto dto);

    LabCheck get(Integer checkId);

    List<LabCheck> findByDoctor(String doctName, String applyStatus);

    List<LabCheck> findByPid(String pid);

    List<LabCheck> findByRid(String rid);

    Iterable<LabCheck> findAll();
    void delete(Integer checkId);
}
