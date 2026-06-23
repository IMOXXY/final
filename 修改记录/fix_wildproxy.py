# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Simplify proxy - use a single rule with wildcard bypass for static files
old = """        proxy: {
            '/patient': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/doctororder': { target: 'http://127.0.0.1:5003', changeOrigin: true, onProxyReq: (proxyReq, req) => { if (req.method === 'PUT') { proxyReq.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS') } } },
            '/outvisit': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/medicalrecord': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/labcheck': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/overview': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/user': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/outbillpay': { target: 'http://127.0.0.1:5003', changeOrigin: true }
        }"""

new = """        proxy: {
            '/': {
                target: 'http://127.0.0.1:5003',
                changeOrigin: true,
                // Only proxy API requests, let webpack serve static files
                bypass: function(req, res, proxyOptions) {
                    // Let the dev server handle these paths
                    if (req.headers.accept && req.headers.accept.indexOf('text/html') !== -1) {
                        return '/index.html'
                    }
                    if (req.path.indexOf('.') !== -1) {
                        return null  // Let webpack serve static files
                    }
                    // Everything else goes to proxy
                }
            }
        }"""

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Updated proxy to wildcard")
else:
    print("FAIL")
    idx = c.find("proxy:")
    if idx >= 0:
        print(repr(c[idx:idx+200]))
