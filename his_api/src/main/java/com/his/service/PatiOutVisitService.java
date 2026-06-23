package com.his.service;

import com.his.pojo.PatiOutVisit;
import com.his.pojo.dto.PatiOutVisitDto;
import com.his.repository.PatiOutVisitRepository;
import com.his.service.IPatiOutVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatiOutVisitService implements IPatiOutVisitService {

    @Autowired
    private PatiOutVisitRepository repository;

    @Override
    public PatiOutVisit add(PatiOutVisitDto dto) {
        PatiOutVisit entity = convertToEntity(dto);
        return repository.save(entity);
    }

    @Override
    public PatiOutVisit getPatiOutVisit(String patiOutVisitId) {
        Optional<PatiOutVisit> optional = repository.findById(patiOutVisitId);
        return optional.orElse(null); // 没有值就返回 null
    }

    @Override
    public List<PatiOutVisit> findAllByDoctName(String doctName) {
        return repository.findAllByDoctName(doctName);
    }

    @Override
    public PatiOutVisit edit(PatiOutVisitDto dto) {
        if (dto.getRid() == null) {
            throw new IllegalArgumentException("挂号ID不能为空");
        }
        if (!repository.existsById(dto.getRid())) {
            throw new RuntimeException("挂号记录不存在，无法更新");
        }
        PatiOutVisit entity = convertToEntity(dto);
        return repository.save(entity);
    }

    @Override
    public void delete(String patiOutVisitId) {
        repository.deleteById(patiOutVisitId);
    }

    // 手动 DTO 转换方法（也可以用 MapStruct）
    private PatiOutVisit convertToEntity(PatiOutVisitDto dto) {
        PatiOutVisit entity = new PatiOutVisit();
        entity.setRid(dto.getRid());
        entity.setPid(dto.getPid());
        entity.setRtype(dto.getRtype());
        entity.setDpmtnNme(dto.getDpmtnNme());
        entity.setDoctName(dto.getDoctName());
        entity.setRegistDate(dto.getRegistDate());
        return entity;
    }
}
