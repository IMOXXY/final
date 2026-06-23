# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Fix the broken $http - remove the stray backslash-dot before $
c = c.replace("this.\.$http.get", "this.$http.get")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)

# Verify
if "this.\.$http" in c:
    print("Still broken!")
else:
    print("OK: Fixed")
    # Show the fixed lines
    idx = c.find("getDoctorOrders")
    if idx >= 0:
        print(c[idx:idx+250])
