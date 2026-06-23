package com.his.controller;

import com.his.pojo.MedicalRecord;
import com.his.pojo.ResponseMessage;
import com.his.pojo.dto.MedicalRecordDto;
import com.his.service.IMedicalRecordService;

import com.his.repository.DiagnoseICDRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController {

    @Autowired
    private IMedicalRecordService service;
    
    @Autowired
    private DiagnoseICDRepository diagnoseICDRepository;

    @PostMapping
    public ResponseMessage add(@Validated @RequestBody MedicalRecordDto dto) {
        return ResponseMessage.success(service.add(dto));
    }

    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody MedicalRecordDto dto) {
        return ResponseMessage.success(service.edit(dto));
    }

    @DeleteMapping("/{docId}")
    public ResponseMessage delete(@PathVariable Integer docId) {
        service.delete(docId);
        return ResponseMessage.success("删除成功");
    }

    @GetMapping("/{docId}")
    public ResponseMessage get(@PathVariable Integer docId) {
        return ResponseMessage.success(service.getById(docId));
    }

    @GetMapping("/pid/{pid}")
    public ResponseMessage getByPid(@PathVariable String pid) {
        return ResponseMessage.success(service.findByPid(pid));
    }
    
    @GetMapping("/rid/{rid}")
    public ResponseMessage getByRid(@PathVariable String rid) {
        return ResponseMessage.success(service.findByRid(rid));
    }


    @GetMapping("/pname/{pname}")
    public ResponseMessage getByPname(@PathVariable String pname) {
        return ResponseMessage.success(service.findByPname(pname));
    }
    
    @GetMapping("/icd/all")
    public ResponseMessage getAllICD() {
    	return ResponseMessage.success(diagnoseICDRepository.findAll());
    }
}
