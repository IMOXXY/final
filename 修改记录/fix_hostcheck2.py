# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

c = c.replace("disableHostCheck: true,", "allowedHosts: [\x27.all\x27],")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Changed to allowedHosts")
