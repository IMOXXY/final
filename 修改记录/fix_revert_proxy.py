# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Restore individual proxy rules
old = """        proxy: {
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

new = """        proxy: {
            '/doctororder': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/patient': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/outvisit': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/medicalrecord': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/labcheck': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/overview': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/user': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/outbillpay': { target: 'http://127.0.0.1:5003', changeOrigin: true }
        }"""

c = c.replace(old, new)
with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Restored individual proxy rules")
