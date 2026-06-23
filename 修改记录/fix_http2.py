# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Fix the broken reference - replace the wrong text
old = "await this.\\.get('/doctororder/rid/'+patiInfo.rid)"
new = "await this.$http.get('/doctororder/rid/'+patiInfo.rid)"

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Fixed broken $http reference")
else:
    print("FAIL: pattern not found, checking alternatives...")
    # Find what's actually there
    idx = c.find("getDoctorOrders")
    if idx >= 0:
        print(repr(c[idx:idx+300]))
