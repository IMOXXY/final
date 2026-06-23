package com.his.controller;

import com.his.pojo.DoctorOrderDict;
import com.his.pojo.ResponseMessage;
import com.his.service.IDoctorOrderDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dictorder")
public class DoctorOrderDictController {

    @Autowired
    private IDoctorOrderDictService service;

    @GetMapping("/all")
    public ResponseMessage getAll() {
        return ResponseMessage.success(service.findAll());
    }

    @GetMapping("/type/{ordertype}")
    public ResponseMessage getByOrdertype(@PathVariable String ordertype) {
        return ResponseMessage.success(service.findByOrdertype(ordertype));
    }

    @GetMapping("/ordername/{keyword}")
    public ResponseMessage getByOrdername(@PathVariable String keyword) {
        return ResponseMessage.success(service.searchByOrdername(keyword));
    }

    @GetMapping("/inputstr/{keyword}")
    public ResponseMessage getByInputstr(@PathVariable String keyword) {
        return ResponseMessage.success(service.searchByInputstr(keyword));
    }

    @GetMapping("/keyword/{ordertype}/{keyword}")
    public ResponseMessage getByKeyword(@PathVariable String ordertype,@PathVariable String keyword) {
        return ResponseMessage.success(service.searchByKeyword(ordertype,keyword));
    }
}
