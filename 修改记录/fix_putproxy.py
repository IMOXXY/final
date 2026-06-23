# -*- coding: utf-8 -*-
# Fix the proxy to support all HTTP methods explicitly
f = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Update the /doctororder proxy to use http-proxy-middleware with method support
old = """\x27/doctororder\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true },"""
new = """\x27/doctororder\x27: { target: \x27http://127.0.0.1:5003\x27, changeOrigin: true, onProxyReq: (proxyReq, req) => { if (req.method === \x27PUT\x27) { proxyReq.setHeader(\x27Access-Control-Allow-Methods\x27, \x27GET, POST, PUT, DELETE, OPTIONS\x27) } } },"""

c = c.replace(old, new)

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Updated proxy config")
