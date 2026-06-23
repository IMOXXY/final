# -*- coding: utf-8 -*-

# 1. vue.config.js - add proxy to backend
f1 = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f1, "r", encoding="utf-8") as fh:
    c = fh.read()

new_config = """// vue.config.js
module.exports = {
    devServer: {
        port: 8080,
        host: \x270.0.0.0\x27,
        open: false,
        allowedHosts: [\x27.all\x27],
        proxy: {
            \x27/patient\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },
            \x27/doctororder\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },
            \x27/outvisit\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },
            \x27/medicalrecord\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },
            \x27/labcheck\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },
            \x27/overview\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },
            \x27/user\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },
            \x27/outbillpay\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true }
        }
    }
}"""

with open(f1, "w", encoding="utf-8") as fh:
    fh.write(new_config)
print("OK: vue.config.js - added proxy")

# 2. request.js - use relative URL (same origin, goes through proxy)
f2 = "D:/codex-workspace/final/his_web/src/request.js"
with open(f2, "r", encoding="utf-8") as fh:
    c = fh.read()

old = "baseURL: \x27http://127.0.0.1:5003\x27"
new = "baseURL: \x27\x27"
c = c.replace(old, new)
with open(f2, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: request.js - baseURL changed to empty (relative)")

# 3. MedicalOrder.vue QR code URL - use relative path, not full URL
f3 = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f3, "r", encoding="utf-8") as fh:
    c = fh.read()

# Replace the full ngrok/LAN URL with just the hash route
c = c.replace("https://diabetes-polar-coffee.ngrok-free.dev", "")
c = c.replace("http://10.5.19.145:8080", "")
# If empty prefix, the URL will just be "/#/outbillpay?rid=..."
# But QR code needs full URL. Let me use window.location.origin
old_url_line = "this.orderToPayCtr.payUrl = \x22/#/outbillpay?rid=\x22+patiInfo.rid+\x22&selectedOrders=\x22"
new_url_line = "this.orderToPayCtr.payUrl = window.location.origin+\x22/#/outbillpay?rid=\x22+patiInfo.rid+\x22&selectedOrders=\x22"
c = c.replace(old_url_line, new_url_line)

# Also fix the second URL in onPayOrderSelect
old_url_line2 = "this.orderToPayCtr.payUrl = \x27/#/outbillpay?rid=\x27+this.orderToPayCtr.patientVisitInfo.rid+\x27&selectedOrders=\x27+this.orderToPayCtr.selectedOrder.join(\x27,\x27)"
new_url_line2 = "this.orderToPayCtr.payUrl = window.location.origin+\x27/#/outbillpay?rid=\x27+this.orderToPayCtr.patientVisitInfo.rid+\x27&selectedOrders=\x27+this.orderToPayCtr.selectedOrder.join(\x27,\x27)"
c = c.replace(old_url_line2, new_url_line2)

with open(f3, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: MedicalOrder.vue - QR code URL uses window.location.origin")
