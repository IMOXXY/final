# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_api/src/main/java/com/his/pojo/MedicalRecord.java"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

old = '@Column(name = "VisitTime", length = 15)'
new = '@Column(name = "VisitTime", length = 30)'
if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: VisitTime 15 -> 30")
else:
    print("FAIL: pattern not found")
