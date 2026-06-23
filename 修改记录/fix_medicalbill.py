# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/Home.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

old = '<el-menu-item index="/medicalBill" :key="778">'
new = '<el-menu-item index="/outbillpay" :key="778">'

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: /medicalBill -> /outbillpay")
else:
    print("FAIL: pattern not found")
    idx = c.find("medicalBill")
    if idx >= 0:
        print(repr(c[idx-50:idx+80]))
