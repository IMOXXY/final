# -*- coding: utf-8 -*-

f1 = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f1, "r", encoding="utf-8") as fh:
    c = fh.read()

# Add orderid to the embedded data
old = """const unpaidOrders = this.orderToPayCtr.orderToPayDataTable.filter(o => this.orderToPayCtr.selectedOrder.includes(o.orderid))
            const orderData = unpaidOrders.map(o => ({
                ordername: o.ordername,
                ordertype: o.ordertype,
                orderprice: o.orderprice,
                totalOrder: o.totalOrder,
                orderallprice: o.orderallprice
            }))"""

new = """const unpaidOrders = this.orderToPayCtr.orderToPayDataTable.filter(o => this.orderToPayCtr.selectedOrder.includes(o.orderid))
            const orderData = unpaidOrders.map(o => ({
                orderid: o.orderid,
                ordername: o.ordername,
                ordertype: o.ordertype,
                orderprice: o.orderprice,
                totalOrder: o.totalOrder,
                orderallprice: o.orderallprice
            }))"""

if old in c:
    c = c.replace(old, new)
    with open(f1, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Added orderid to embedded data")
else:
    print("FAIL")
