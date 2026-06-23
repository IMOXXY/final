package com.his.controller;

import com.his.pojo.PatiInfoBasic;
import com.his.pojo.ResponseMessage;
import com.his.pojo.dto.PatiInfoBasicDto;
import com.his.service.IPatiInfoBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patient")  // URL前缀：localhost:8088/patient/**
public class PatiInfoBasicController {

    @Autowired
    private IPatiInfoBasicService patiInfoBasicService;

    /**
     * 添加患者
     */
    @PostMapping
    public ResponseMessage add(@Validated @RequestBody PatiInfoBasicDto dto) {
        PatiInfoBasic newPatient = patiInfoBasicService.add(dto);
        return ResponseMessage.success(newPatient);
    }

    /**
     * 根据ID查询患者
     */
    @GetMapping("/{pid}")
    public ResponseMessage get(@PathVariable String pid) {
        PatiInfoBasic patient = patiInfoBasicService.getById(pid);
        return ResponseMessage.success(patient);
    }
    
    /**
     * 根据关键词查询患者
     */
    @GetMapping("/keyword/{keyword}")
    public ResponseMessage getFromKey(@PathVariable String keyword) {
        List<PatiInfoBasic> patients = patiInfoBasicService.searchByKeyword(keyword);
        return ResponseMessage.success(patients);
    }

    /**
     * 修改患者信息
     */
    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody PatiInfoBasicDto dto) {
        PatiInfoBasic updated = patiInfoBasicService.edit(dto);
        return ResponseMessage.success(updated);
    }

    /**
     * 删除患者信息
     */
    @DeleteMapping("/{pid}")
    public ResponseMessage delete(@PathVariable String pid) {
        patiInfoBasicService.delete(pid);
        return ResponseMessage.success();
    }

    /**
     * 根据 pid 更新电话（示例）
     */
    @PutMapping("/{pid}/tel")
    public ResponseMessage updateTel(@PathVariable String pid, @RequestParam String ptel) {
        boolean result = patiInfoBasicService.updatePtelByPid(pid, ptel);
        return result ? ResponseMessage.success("电话更新成功") : ResponseMessage.error("电话更新失败");
    }

    /**
     * 根据 pid 更新姓名和性别（示例）
     */
    @PutMapping("/{pid}/info")
    public ResponseMessage updateInfo(@PathVariable String pid,
                                      @RequestParam String pname,
                                      @RequestParam String pgender) {
        boolean result = patiInfoBasicService.updateInfoByPid(pid, pname, pgender);
        return result ? ResponseMessage.success("信息更新成功") : ResponseMessage.error("信息更新失败");
    }

}
