# -*- coding: utf-8 -*-

# Fix controller
f2 = "D:/codex-workspace/final/his_api/src/main/java/com/his/controller/DoctorOrderController.java"
with open(f2, "r", encoding="utf-8") as fh:
    c = fh.read()

add_before = """    @GetMapping("/alipay/qrcode/{orderNo}")"""

new_endpoint = """    @PutMapping("/pay/{orderId}")
    public ResponseMessage pay(@PathVariable Integer orderId) {
        service.markAsPaid(orderId);
        return ResponseMessage.success("\u652f\u4ed8\u6210\u529f");
    }

    @GetMapping("/alipay/qrcode/{orderNo}")"""

c = c.replace(add_before, new_endpoint)
with open(f2, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Controller fixed")

# Fix interface
f4 = "D:/codex-workspace/final/his_api/src/main/java/com/his/service/IDoctorOrderService.java"
with open(f4, "r", encoding="utf-8") as fh:
    c = fh.read()

old = """    DoctorOrder edit(DoctorOrderDto order);
    
}"""

new = """    DoctorOrder edit(DoctorOrderDto order);
    
    void markAsPaid(Integer orderId);
    
}"""

if old in c:
    c = c.replace(old, new)
    with open(f4, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Interface fixed")
else:
    print("FAIL: interface pattern")
    print(repr(c[-200:]))
