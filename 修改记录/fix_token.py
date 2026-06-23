# -*- coding: utf-8 -*-

# 1. MedicalOrder.vue - add token to QR code URL
f1 = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f1, "r", encoding="utf-8") as fh:
    c = fh.read()

# Add token to the default URL in toOrderPay
old1 = 'this.orderToPayCtr.payUrl = "https://diabetes-polar-coffee.ngrok-free.dev/#/outbillpay?rid="+patiInfo.rid+"&selectedOrders="'
new1 = 'this.orderToPayCtr.payUrl = "https://diabetes-polar-coffee.ngrok-free.dev/#/outbillpay?rid="+patiInfo.rid+"&selectedOrders=&token="+sessionStorage.getItem("token")'
c = c.replace(old1, new1)

# Add token to the URL in onPayOrderSelect
old2 = "this.orderToPayCtr.payUrl = 'https://diabetes-polar-coffee.ngrok-free.dev/#/outbillpay?rid='+this.orderToPayCtr.patientVisitInfo.rid+'&selectedOrders='+this.orderToPayCtr.selectedOrder.join(',')"
new2 = "this.orderToPayCtr.payUrl = 'https://diabetes-polar-coffee.ngrok-free.dev/#/outbillpay?rid='+this.orderToPayCtr.patientVisitInfo.rid+'&selectedOrders='+this.orderToPayCtr.selectedOrder.join(',')+'&token='+sessionStorage.getItem('token')"
c = c.replace(old2, new2)

with open(f1, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: MedicalOrder.vue - token added to QR URL")

# 2. OutBillPay.vue - read token from URL and set it
f2 = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
with open(f2, "r", encoding="utf-8") as fh:
    c = fh.read()

old_mounted = """    mounted() {
        window.title = '\u8d39\u7528\u652f\u4ed8'
        window.outPayV=this
        this.selectedOrders = (this.\u0024route.query.selectedOrders || \x22\x22).split(\x22,\x22).filter(Boolean)
        this.rid = this.\u0024route.query.rid"""

new_mounted = """    mounted() {
        window.title = '\u8d39\u7528\u652f\u4ed8'
        window.outPayV=this
        // Read token from URL params (phone scan from QR code)
        const token = this.\u0024route.query.token
        if (token) {
            sessionStorage.setItem('token', token)
        }
        this.selectedOrders = (this.\u0024route.query.selectedOrders || \x22\x22).split(\x22,\x22).filter(Boolean)
        this.rid = this.\u0024route.query.rid"""

if old_mounted in c:
    c = c.replace(old_mounted, new_mounted)
    with open(f2, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: OutBillPay.vue - reads token from URL")
else:
    print("FAIL: OutBillPay pattern not found")
    idx = c.find("mounted")
    if idx >= 0:
        print(repr(c[idx:idx+300]))
