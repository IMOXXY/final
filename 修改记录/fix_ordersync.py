# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Add sync check in toOrderPay
old = """        toOrderPay(){"""
new = """        toOrderPay(){
            // Check if payment was done via QR code (sync from OutBillPay)
            const patiInfo = this.patients[this.patient_edit_index]
            if (patiInfo) {
                const syncKey = "payment_sync_"+patiInfo.rid
                const lastSync = sessionStorage.getItem(syncKey)
                if (lastSync) {
                    sessionStorage.removeItem(syncKey)
                    this.getDoctorOrders()
                }
            }"""

if old in c:
    c = c.replace(old, new, 1)  # Only replace first occurrence
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Added sync check in toOrderPay")
else:
    print("FAIL: pattern not found")
    idx = c.find("toOrderPay")
    if idx >= 0:
        print(repr(c[idx:idx+100]))
