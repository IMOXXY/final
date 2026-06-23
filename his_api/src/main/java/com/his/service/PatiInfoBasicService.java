package com.his.service;

import com.his.pojo.PatiInfoBasic;
import com.his.pojo.dto.PatiInfoBasicDto;
import com.his.repository.PatiInfoBasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class PatiInfoBasicService implements IPatiInfoBasicService {

    @Autowired
    private PatiInfoBasicRepository patiInfoBasicRepository;

    @Override
    public PatiInfoBasic add(PatiInfoBasicDto dto) {
        PatiInfoBasic entity = convertDtoToEntity(dto);
        return patiInfoBasicRepository.save(entity);
    }

    @Override
    public PatiInfoBasic getById(String pid) {
        return patiInfoBasicRepository.findById(pid).orElse(null);
    }

    @Override
    public PatiInfoBasic edit(PatiInfoBasicDto dto) {
        Optional<PatiInfoBasic> optional = patiInfoBasicRepository.findById(dto.getPid());
        if (optional.isPresent()) {
            PatiInfoBasic entity = optional.get();
            entity.setPname(dto.getPname());
            entity.setPgender(dto.getPgender());
            entity.setPidcard(dto.getPidcard());
            entity.setPbirthday(dto.getPbirthday());
            entity.setPtel(dto.getPtel());
            return patiInfoBasicRepository.save(entity);
        } else {
            throw new RuntimeException("未找到该患者，无法修改");
        }
    }

    @Override
    public void delete(String pid) {
        patiInfoBasicRepository.deleteById(pid);
    }

    @Override
    public boolean updatePtelByPid(String pid, String ptel) {
        return patiInfoBasicRepository.updatePtelByPid(pid, ptel) > 0;
    }

    @Override
    public boolean updateInfoByPid(String pid, String pname, String pgender) {
        return patiInfoBasicRepository.updateInfoByPid(pid, pname, pgender) > 0;
    }
    
    @Override
    public List<PatiInfoBasic> searchByKeyword(String keyword) {
        return patiInfoBasicRepository.searchByKeyword(keyword);
    }

    private PatiInfoBasic convertDtoToEntity(PatiInfoBasicDto dto) {
        PatiInfoBasic entity = new PatiInfoBasic();
        entity.setPid(dto.getPid());
        entity.setPname(dto.getPname());
        entity.setPgender(dto.getPgender());
        entity.setPidcard(dto.getPidcard());
        entity.setPbirthday(dto.getPbirthday());
        entity.setPtel(dto.getPtel());
        return entity;
    }
}
