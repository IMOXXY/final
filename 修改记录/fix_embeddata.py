# -*- coding: utf-8 -*-

f1 = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f1, "r", encoding="utf-8") as fh:
    c = fh.read()

# Replace the URL in onPayOrderSelect
old2 = "this.orderToPayCtr.payUrl = 'https://diabetes-polar-coffee.ngrok-free.dev/#/outbillpay?rid='+this.orderToPayCtr.patientVisitInfo.rid+'&selectedOrders='+this.orderToPayCtr.selectedOrder.join(',')+'&token='+sessionStorage.getItem('token')"

new2 = """const unpaidOrders = this.orderToPayCtr.orderToPayDataTable.filter(o => this.orderToPayCtr.selectedOrder.includes(o.orderid))
            const orderData = unpaidOrders.map(o => ({
                ordername: o.ordername,
                ordertype: o.ordertype,
                orderprice: o.orderprice,
                totalOrder: o.totalOrder,
                orderallprice: o.orderallprice
            }))
            this.orderToPayCtr.payUrl = 'https://diabetes-polar-coffee.ngrok-free.dev/#/outbillpay?rid='+this.orderToPayCtr.patientVisitInfo.rid+'&data='+encodeURIComponent(JSON.stringify(orderData))+'&token='+sessionStorage.getItem('token')"""

if old2 in c:
    c = c.replace(old2, new2)
    with open(f1, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: MedicalOrder - embedded order data in URL")

    # 2. OutBillPay.vue - read from data param instead of API
    f2 = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
    with open(f2, "r", encoding="utf-8") as fh:
        c2 = fh.read()

    old_mounted = """const token = this.$route.query.token
        if (token) {
            sessionStorage.setItem('token', token)
        }
        this.selectedOrders = (this.$route.query.selectedOrders || "").split(",").filter(Boolean) 
        this.rid = this.$route.query.rid
        this.getOrderToPayInfo()"""

    new_mounted = """const token = this.$route.query.token
        if (token) {
            sessionStorage.setItem('token', token)
        }
        // Read order data from URL (embedded by MedicalOrder page)
        const rawData = this.$route.query.data
        if (rawData) {
            try {
                this.orderInfos = JSON.parse(decodeURIComponent(rawData))
            } catch(e) {
                console.error("Failed to parse order data", e)
            }
        }
        this.selectedOrders = (this.$route.query.selectedOrders || "").split(",").filter(Boolean) 
        this.rid = this.$route.query.rid"""

    if old_mounted in c2:
        c2 = c2.replace(old_mounted, new_mounted)
        with open(f2, "w", encoding="utf-8") as fh:
            fh.write(c2)
        print("OK: OutBillPay - reads order data from URL")
    else:
        print("FAIL: OutBillPay pattern not found")
        idx = c2.find("const token")
        if idx >= 0:
            print(repr(c2[idx:idx+300]))
else:
    print("FAIL: pattern not found")
    idx = c.find("payUrl =")
    if idx >= 0:
        idx2 = c.find("payUrl =", idx+10)
        if idx2 >= 0:
            print(repr(c[idx2:idx2+300]))
