# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Replace ngrok URL with LAN IP
c = c.replace("https://diabetes-polar-coffee.ngrok-free.dev", "http://10.5.19.145:8080")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Changed back to LAN IP")
