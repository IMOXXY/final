# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

c = c.replace("allowedHosts: [\x27.all\x27],", "disableHostCheck: true,")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Changed to disableHostCheck: true")
