package com.his.controller;

import com.his.pojo.HospitalDepartment;
import com.his.pojo.ResponseMessage;
import com.his.pojo.dto.HospitalDepartmentDto;
import com.his.service.IHospitalDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/department")
public class HospitalDepartmentController {

    @Autowired
    private IHospitalDepartmentService hospitalDepartmentService;

    /**
     * 根据 pid 更新姓名和性别（示例）
     */
    @GetMapping("/all")
    public ResponseMessage findAll() {
    	
        return ResponseMessage.success(hospitalDepartmentService.findAll());
    }

}
