# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Add @select-all handler
old = '@select="onPayOrderSelect"'
new = '@select="onPayOrderSelect" @select-all="onPayOrderSelect"'
c = c.replace(old, new)

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Added @select-all handler")
