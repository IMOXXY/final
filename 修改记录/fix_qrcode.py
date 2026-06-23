# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# 1. Change default payUrl from baidu to empty, and add :key to force QR re-render
old_default = "payUrl: \x27https://baidu.com\x27"
new_default = "payUrl: \x27\x27"
c = c.replace(old_default, new_default)
print("Fix 1: Changed default payUrl")

# 2. Add :key to qrcode-vue to force re-render when URL changes
old_qr = "<qrcode-vue :value=\"orderToPayCtr.payUrl\"  :size=\"160\" />"
new_qr = "<qrcode-vue :value=\"orderToPayCtr.payUrl\" :key=\"orderToPayCtr.payUrl\" :size=\"160\" />"
c = c.replace(old_qr, new_qr)
print("Fix 2: Added :key to qrcode-vue")

# 3. In toOrderPay, set a default URL (without selected orders) when dialog opens
old_to = """        toOrderPay(){
            // Check if payment was done via QR code (sync from OutBillPay)
            const curPati = this.patients[this.patient_edit_index]
            if (curPati) {
                const syncKey = "payment_sync_"+curPati.rid
                const lastSync = sessionStorage.getItem(syncKey)
                if (lastSync) {
                    sessionStorage.removeItem(syncKey)
                    this.getDoctorOrders()
                }
            }
            this.getDoctorOrders() // 支付后同步刷新医嘱
            const patiInfo = this.patients[this.patient_edit_index]
            this.orderToPayCtr.patientVisitInfo = patiInfo

            this.orderToPayCtr.orderToPayDataTable = _.filter(this.doctorOrders,o=>{return !o.ispaid})

            this.orderToPayCtr.visible = true"""

new_to = """        toOrderPay(){
            // Check if payment was done via QR code (sync from OutBillPay)
            const curPati = this.patients[this.patient_edit_index]
            if (curPati) {
                const syncKey = "payment_sync_"+curPati.rid
                const lastSync = sessionStorage.getItem(syncKey)
                if (lastSync) {
                    sessionStorage.removeItem(syncKey)
                    this.getDoctorOrders()
                }
            }
            this.getDoctorOrders() // 支付后同步刷新医嘱
            const patiInfo = this.patients[this.patient_edit_index]
            this.orderToPayCtr.patientVisitInfo = patiInfo

            this.orderToPayCtr.orderToPayDataTable = _.filter(this.doctorOrders,o=>{return !o.ispaid})
            
            // Set default QR code URL (pointing to outbillpay without specific orders)
            if (patiInfo) {
                this.orderToPayCtr.payUrl = "https://diabetes-polar-coffee.ngrok-free.dev/#/outbillpay?rid="+patiInfo.rid+"&selectedOrders="
                this.orderToPayCtr.underQrcodeText = "请勾选要支付的医嘱后再扫码"
            }

            this.orderToPayCtr.visible = true"""

if old_to in c:
    c = c.replace(old_to, new_to)
    print("Fix 3: Added default URL in toOrderPay")
else:
    print("Fix 3: FAIL - pattern mismatch")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("Done")
