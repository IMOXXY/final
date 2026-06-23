package com.his.service;

import com.his.pojo.MedicalRecord;
import com.his.pojo.dto.MedicalRecordDto;
import com.his.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    @Autowired
    private MedicalRecordRepository repository;

    @Override
    public MedicalRecord add(MedicalRecordDto dto) {
        return repository.save(convertDtoToEntity(dto));
    }

    @Override
    public MedicalRecord edit(MedicalRecordDto dto) {
        MedicalRecord record = repository.findById(dto.getDocId())
                .orElseThrow(() -> new RuntimeException("未找到病历"));
        record.setRid(dto.getRid());
        record.setPid(dto.getPid());
        record.setPname(dto.getPname());
        record.setDiagnoseICD(dto.getDiagnoseICD());
        record.setDiagnoseICDName(dto.getDiagnoseICDName());
        record.setMedicalContent(dto.getMedicalContent());
        record.setVisitTime(dto.getVisitTime());
        return repository.save(record);
    }

    @Override
    public void delete(Integer docId) {
        repository.deleteById(docId);
    }

    @Override
    public MedicalRecord getById(Integer docId) {
        return repository.findById(docId).orElse(null);
    }

    @Override
    public List<MedicalRecord> findByPid(String pid) {
        return repository.findByPid(pid);
    }
    
    @Override
    public List<MedicalRecord> findByRid(String rid) {
        return repository.findByRid(rid);
    }

    @Override
    public List<MedicalRecord> findByPname(String pname) {
        return repository.findByPnameContaining(pname);
    }

    private MedicalRecord convertDtoToEntity(MedicalRecordDto dto) {
        MedicalRecord record = new MedicalRecord();
        record.setDocId(dto.getDocId());
        record.setRid(dto.getRid());
        record.setPid(dto.getPid());
        record.setPname(dto.getPname());
        record.setDiagnoseICD(dto.getDiagnoseICD());
        record.setDiagnoseICDName(dto.getDiagnoseICDName());
        record.setMedicalContent(dto.getMedicalContent());
        record.setVisitTime(dto.getVisitTime());
        return record;
    }
}
