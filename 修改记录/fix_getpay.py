# -*- coding: utf-8 -*-

# 1. Backend controller: add GET endpoint for payment
f1 = "D:/codex-workspace/final/his_api/src/main/java/com/his/controller/DoctorOrderController.java"
with open(f1, "r", encoding="utf-8") as fh:
    c = fh.read()

old_put = """    @PutMapping("/pay/{orderId}")
    public ResponseMessage pay(@PathVariable Integer orderId) {
        service.markAsPaid(orderId);
        return ResponseMessage.success("\u652f\u4ed8\u6210\u529f");
    }"""

new_get = """    @PutMapping("/pay/{orderId}")
    public ResponseMessage pay(@PathVariable Integer orderId) {
        service.markAsPaid(orderId);
        return ResponseMessage.success("\u652f\u4ed8\u6210\u529f");
    }
    
    @GetMapping("/pay/{orderId}")
    public ResponseMessage payGet(@PathVariable Integer orderId) {
        service.markAsPaid(orderId);
        return ResponseMessage.success("\u652f\u4ed8\u6210\u529f");
    }"""

if old_put in c:
    c = c.replace(old_put, new_get)
    with open(f1, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Added GET pay endpoint")
else:
    print("FAIL: backend PUT pattern not found")

# 2. Frontend: change to GET request
f2 = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
with open(f2, "r", encoding="utf-8") as fh:
    c2 = fh.read()

old_put_req = "await this.$http.put('/doctororder/pay/'+o.orderid)"
new_get_req = "await this.$http.get('/doctororder/pay/'+o.orderid)"
if old_put_req in c2:
    c2 = c2.replace(old_put_req, new_get_req)
    with open(f2, "w", encoding="utf-8") as fh:
        fh.write(c2)
    print("OK: Changed to GET request")
else:
    print("FAIL: PUT request pattern not found")
    idx = c2.find("/doctororder/pay/")
    if idx >= 0:
        print(repr(c2[idx-20:idx+80]))
