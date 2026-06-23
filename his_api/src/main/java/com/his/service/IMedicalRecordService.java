package com.his.service;

import com.his.pojo.MedicalRecord;
import com.his.pojo.dto.MedicalRecordDto;

import java.util.List;

public interface IMedicalRecordService {

    MedicalRecord add(MedicalRecordDto dto);

    MedicalRecord edit(MedicalRecordDto dto);

    void delete(Integer docId);

    MedicalRecord getById(Integer docId);

    List<MedicalRecord> findByPid(String pid);
    
    List<MedicalRecord> findByRid(String rid);

    List<MedicalRecord> findByPname(String pname);
}
