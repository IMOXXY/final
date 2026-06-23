# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Fix: QR code should use a fixed ngrok URL, not window.location.origin
ngrok_url = "https://diabetes-polar-coffee.ngrok-free.dev"

old1 = 'this.orderToPayCtr.payUrl = window.location.origin+"/#/outbillpay?rid="+patiInfo.rid+"&selectedOrders="'
new1 = 'this.orderToPayCtr.payUrl = "'+ngrok_url+'/#/outbillpay?rid="+patiInfo.rid+"&selectedOrders="'
c = c.replace(old1, new1)

old2 = "this.orderToPayCtr.payUrl = window.location.origin+'/#/outbillpay?rid='+this.orderToPayCtr.patientVisitInfo.rid+'&selectedOrders='+this.orderToPayCtr.selectedOrder.join(',')"
new2 = "this.orderToPayCtr.payUrl = '"+ngrok_url+"/#/outbillpay?rid='+this.orderToPayCtr.patientVisitInfo.rid+'&selectedOrders='+this.orderToPayCtr.selectedOrder.join(',')"
c = c.replace(old2, new2)

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: QR code now uses ngrok URL")
