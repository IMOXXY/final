# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

old = '<el-button icon="iconfont icon-fukuan" class="order-pay" type="info" @click="toOrderPay"></el-button>'
new = '<el-button class="order-pay" type="info" @click="toOrderPay">支付</el-button>'

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Payment button now shows text")
else:
    print("FAIL")
    idx = c.find("toOrderPay")
    if idx >= 0:
        print(repr(c[idx-50:idx+50]))
