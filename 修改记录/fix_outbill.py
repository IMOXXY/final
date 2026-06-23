# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Fix: if selectedOrders is empty, show all unpaid orders
old = "this.orderInfos = _.filter(res.data,o=>{ return !o.ispaid && this.selectedOrders.indexOf(\x27\x27+o.orderid)>-1 })"
new = "this.orderInfos = _.filter(res.data,o=>{ return !o.ispaid && (this.selectedOrders.length===0 || this.selectedOrders.indexOf(\x27\x27+o.orderid)>-1) })"

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: OutBillPay now handles empty selectedOrders")
else:
    print("FAIL")
    idx = c.find("return !o.ispaid")
    if idx >= 0:
        print(repr(c[idx:idx+120]))
