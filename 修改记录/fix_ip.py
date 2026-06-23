# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

c = c.replace("172.18.64.140", "10.5.19.145")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: IP changed to 10.5.19.145")
