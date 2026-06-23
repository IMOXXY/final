# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

old = "devServer: {"
new = "devServer: {\n      disableHostCheck: true,"
c = c.replace(old, new)

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Added disableHostCheck")
