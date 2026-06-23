package com.his.controller;

import com.his.pojo.LabCheck;
import com.his.pojo.ResponseMessage;
import com.his.pojo.dto.LabCheckDto;
import com.his.service.ILabCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labcheck")
public class LabCheckController {

    @Autowired
    private ILabCheckService service;

    @PostMapping
    public ResponseMessage add(@RequestBody LabCheckDto dto) {
        LabCheck labCheck = service.add(dto);
        return ResponseMessage.success(labCheck);
    }

    @PutMapping
    public ResponseMessage edit(@RequestBody LabCheckDto dto) {
        LabCheck labCheck = service.edit(dto);
        return ResponseMessage.success(labCheck);
    }

    @PutMapping("/result")
    public ResponseMessage completeResult(@RequestBody LabCheckDto dto) {
        LabCheck labCheck = service.completeResult(dto);
        return ResponseMessage.success(labCheck);
    }

    @GetMapping("/{checkId}")
    public ResponseMessage get(@PathVariable Integer checkId) {
        LabCheck labCheck = service.get(checkId);
        return ResponseMessage.success(labCheck);
    }

    @GetMapping("/doctor/{doctName}")
    public ResponseMessage findByDoctor(
            @PathVariable String doctName,
            @RequestParam(required = false) String status) {
        List<LabCheck> checks = service.findByDoctor(doctName, status);
        return ResponseMessage.success(checks);
    }

    @GetMapping("/pid/{pid}")
    public ResponseMessage findByPid(@PathVariable String pid) {
        List<LabCheck> checks = service.findByPid(pid);
        return ResponseMessage.success(checks);
    }

    @GetMapping("/rid/{rid}")
    public ResponseMessage findByRid(@PathVariable String rid) {
        List<LabCheck> checks = service.findByRid(rid);
        return ResponseMessage.success(checks);
    }

    @GetMapping("/all")
    public ResponseMessage findAll() {
        Iterable<LabCheck> checks = service.findAll();
        return ResponseMessage.success(checks);
    }

    @DeleteMapping("/{checkId}")
    public ResponseMessage delete(@PathVariable Integer checkId) {
        service.delete(checkId);
        return ResponseMessage.success();
    }
}
