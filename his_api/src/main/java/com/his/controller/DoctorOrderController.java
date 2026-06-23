package com.his.controller;

import com.his.pojo.DoctorOrder;
import com.his.pojo.dto.DoctorOrderDto;
import com.his.pojo.ResponseMessage;
import com.his.service.IDoctorOrderService;

import com.his.utils.OrderAlipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctororder")
public class DoctorOrderController {

    @Autowired
    private IDoctorOrderService service;

    @Autowired
    private OrderAlipay orderAlipay;
    
    /**
     * 添加医嘱
     */
    @PostMapping
    public ResponseMessage add(@RequestBody DoctorOrderDto dto) {
        DoctorOrder newOrder = service.add(dto);
        return ResponseMessage.success(newOrder);
    }
    

    /**
     *编辑医嘱
     */
    @PutMapping
    public ResponseMessage edit(@RequestBody DoctorOrderDto dto) {
        DoctorOrder newOrder = service.edit(dto);
        return ResponseMessage.success(newOrder);
    }
    
    

    /**
     * 根据挂号编号查询医嘱
     */
    @GetMapping("/rid/{rid}")
    public ResponseMessage getByRid(@PathVariable String rid) {
        List<DoctorOrder> orders = service.getByRid(rid);
        return ResponseMessage.success(orders);
    }
    
    
    /**
     * 获取所有医嘱信息
     */
    @GetMapping("/all")
    public ResponseMessage getAll() {
    	Iterable<DoctorOrder> orders = service.findAll();
        return ResponseMessage.success(orders);
    }
    
    @PutMapping("/pay/{orderId}")
    public ResponseMessage pay(@PathVariable Integer orderId) {
        service.markAsPaid(orderId);
        return ResponseMessage.success("支付成功");
    }
    
    @GetMapping("/pay/{orderId}")
    public ResponseMessage payGet(@PathVariable Integer orderId) {
        service.markAsPaid(orderId);
        return ResponseMessage.success("支付成功");
    }

    @GetMapping("/alipay/qrcode/{orderNo}")
    public String getQrCode(@PathVariable String orderNo) throws Exception {
        return orderAlipay.createQrCode(orderNo, 99.99, "门诊挂号");
    }
}
