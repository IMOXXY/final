# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Update outPay to only send orderid+ispaid, and add rid
old_outpay = """        async outPay() {
            let success = true
            for (let o of this.orderInfos) {
                o.ispaid=true
                const {data:res} = await this.$http.put('/doctororder',o)
                if(res.code !== 200){ success = false }
            }"""

new_outpay = """        async outPay() {
            let success = true
            for (let o of this.orderInfos) {
                const {data:res} = await this.$http.put('/doctororder/pay/'+o.orderid)
                if(res.code !== 200){ success = false }
            }"""

if old_outpay in c:
    c = c.replace(old_outpay, new_outpay)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Updated outPay method")

    # Add a simple backend endpoint
    f2 = "D:/codex-workspace/final/his_api/src/main/java/com/his/controller/DoctorOrderController.java"
    with open(f2, "r", encoding="utf-8") as fh:
        c2 = fh.read()

    # Add a simple pay endpoint
    old_endpoint = """    @GetMapping("/alipay/qrcode/{orderNo}")
    public String getQrCode(@PathVariable String orderNo) throws Exception {
        return orderAlipay.createQrCode(orderNo, 99.99, "閮ㄨ剳鎸傚彿");
    }"""

    new_endpoint = """    @PutMapping("/pay/{orderId}")
    public ResponseMessage pay(@PathVariable Integer orderId) {
        service.markAsPaid(orderId);
        return ResponseMessage.success("鏀粯鎴愬姛");
    }

    @GetMapping("/alipay/qrcode/{orderNo}")
    public String getQrCode(@PathVariable String orderNo) throws Exception {
        return orderAlipay.createQrCode(orderNo, 99.99, "闂ㄨ瘖鎸傚彿");
    }"""

    if old_endpoint in c2:
        c2 = c2.replace(old_endpoint, new_endpoint)
        with open(f2, "w", encoding="utf-8") as fh:
            fh.write(c2)
        print("OK: Added pay endpoint to controller")
    else:
        print("FAIL: backend endpoint pattern not found")

    # Add markAsPaid method to service
    f3 = "D:/codex-workspace/final/his_api/src/main/java/com/his/service/DoctorOrderService.java"
    with open(f3, "r", encoding="utf-8") as fh:
        c3 = fh.read()

    # Add method before the closing of the class
    old_end = "}"  # Last closing brace
    # Find the interface too
    new_method = """
    @Override
    public void markAsPaid(Integer orderId) {
        repository.findById(orderId).ifPresent(order -> {
            order.setIspaid(true);
            repository.save(order);
        });
    }
}"""

    # Find the last occurrence of "}" at the end of the file
    # Actually let's find the end of the edit method and add after it
    add_after = """        return repository.save(doctorOrderPojo);
    }
    
}"""

    add_new = """        return repository.save(doctorOrderPojo);
    }
    
    @Override
    public void markAsPaid(Integer orderId) {
        repository.findById(orderId).ifPresent(order -> {
            order.setIspaid(true);
            repository.save(order);
        });
    }
    
}"""

    if add_after in c3:
        c3 = c3.replace(add_after, add_new)
        with open(f3, "w", encoding="utf-8") as fh:
            fh.write(c3)
        print("OK: Added markAsPaid to service")
    else:
        print("FAIL: service pattern not found")
        idx = c3.rfind("}")
        print(repr(c3[max(0,idx-200):idx+5]))

    # Also add to interface
    f4 = "D:/codex-workspace/final/his_api/src/main/java/com/his/service/IDoctorOrderService.java"
    with open(f4, "r", encoding="utf-8") as fh:
        c4 = fh.read()

    old_interface_end = """    Iterable<DoctorOrder> findAll();
}"""
    new_interface_end = """    Iterable<DoctorOrder> findAll();
    
    void markAsPaid(Integer orderId);
}"""

    if old_interface_end in c4:
        c4 = c4.replace(old_interface_end, new_interface_end)
        with open(f4, "w", encoding="utf-8") as fh:
            fh.write(c4)
        print("OK: Added markAsPaid to interface")
    else:
        print("FAIL: interface pattern not found")

else:
    print("FAIL: pattern not found")
    idx = c.find("outPay")
    if idx >= 0:
        print(repr(c[idx:idx+200]))
