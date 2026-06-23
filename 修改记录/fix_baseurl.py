# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/request.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Change baseURL - use a placeholder for now, user will fill in
old = "baseURL: \x27http://127.0.0.1:5003\x27"
new = "baseURL: \x27http://10.5.19.145:5003\x27"

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Changed baseURL to 10.5.19.145:5003")
else:
    print("FAIL")
    print(old)
    idx = c.find("baseURL")
    if idx >= 0:
        print(repr(c[idx:idx+60]))
