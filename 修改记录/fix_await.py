# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Fix 1: Make toOrderPay async
c = c.replace("toOrderPay(){", "async toOrderPay(){", 1)

# Fix 2: Add await before getDoctorOrders
old = "this.getDoctorOrders() // \u652f\u4ed8\u540e\u540c\u6b65\u5237\u65b0\u533b\u564e"
new = "await this.getDoctorOrders() // \u652f\u4ed8\u540e\u540c\u6b65\u5237\u65b0\u533b\u564e"
c = c.replace(old, new)

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Added async/await to toOrderPay")
