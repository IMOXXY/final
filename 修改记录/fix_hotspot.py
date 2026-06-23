# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Replace ngrok URL with a placeholder for now
c = c.replace("https://diabetes-polar-coffee.ngrok-free.dev", "http://192.168.1.100:8080")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: QR code uses placeholder IP - CHANGE IT BEFORE DEMO!")

# Also fix request.js to use same IP
f2 = "D:/codex-workspace/final/his_web/src/request.js"
with open(f2, "r", encoding="utf-8") as fh:
    c2 = fh.read()

# baseURL is empty now (proxy mode), so no change needed
print("Note: request.js uses relative URL (proxy mode), no change needed")
