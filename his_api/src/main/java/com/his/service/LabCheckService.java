package com.his.service;

import com.his.pojo.LabCheck;
import com.his.pojo.dto.LabCheckDto;
import com.his.repository.LabCheckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LabCheckService implements ILabCheckService {

    @Autowired
    private LabCheckRepository repository;

    @Override
    public LabCheck add(LabCheckDto dto) {
        LabCheck labCheck = new LabCheck();
        BeanUtils.copyProperties(dto, labCheck);
        if (labCheck.getApplyStatus() == null || labCheck.getApplyStatus().isEmpty()) {
            labCheck.setApplyStatus("APPLIED");
        }
        if (labCheck.getApplyTime() == null) {
            labCheck.setApplyTime(new Date());
        }
        return repository.save(labCheck);
    }

    @Override
    public LabCheck edit(LabCheckDto dto) {
        LabCheck labCheck = new LabCheck();
        BeanUtils.copyProperties(dto, labCheck);
        return repository.save(labCheck);
    }

    @Override
    public LabCheck completeResult(LabCheckDto dto) {
        LabCheck labCheck = repository.findById(dto.getCheckId()).orElseThrow();
        labCheck.setResultValue(dto.getResultValue());
        labCheck.setResultDoctor(dto.getResultDoctor());
        labCheck.setRemark(dto.getRemark());
        labCheck.setResultTime(dto.getResultTime() == null ? new Date() : dto.getResultTime());
        labCheck.setApplyStatus("COMPLETED");
        return repository.save(labCheck);
    }

    @Override
    public LabCheck get(Integer checkId) {
        return repository.findById(checkId).orElse(null);
    }

    @Override
    public List<LabCheck> findByDoctor(String doctName, String applyStatus) {
        if (applyStatus == null || applyStatus.isEmpty()) {
            return repository.findByDoctName(doctName);
        }
        return repository.findByDoctNameAndApplyStatus(doctName, applyStatus);
    }

    @Override
    public List<LabCheck> findByPid(String pid) {
        return repository.findByPid(pid);
    }

    @Override
    public List<LabCheck> findByRid(String rid) {
        return repository.findByRid(rid);
    }

    @Override
    public Iterable<LabCheck> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer checkId) {
        repository.deleteById(checkId);
    }
}
