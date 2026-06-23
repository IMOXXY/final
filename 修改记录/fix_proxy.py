# -*- coding: utf-8 -*-

# 1. Fix vue.config.js - add proxy to backend + allow all hosts
f1 = "D:/codex-workspace/final/his_web/vue.config.js"
with open(f1, "r", encoding="utf-8") as fh:
    c = fh.read()

new_config = """// vue.config.js
module.exports = {
    devServer: {
        port: 8080,
        host: \x270.0.0.0\x27,
        open: false,
        allowedHosts: [\x27.all\x27],
        proxy: {
            \x27/\x27: {
                target: \x27http://127.0.0.1:5003\x27,
                changeOrigin: true
            }
        }
    }
}"""

# For proxy to work we need to be selective - proxy only /doctororder, /patient, etc.
# Actually, the simplest approach: proxy everything EXCEPT the static files
# But actually, we want the dev server to serve the vue app AND proxy API calls
# The best approach: use a path filter for API routes

new_config = """// vue.config.js
module.exports = {
    devServer: {
        port: 8080,
        host: \x270.0.0.0\x27,
        open: false,
        allowedHosts: [\x27.all\x27]
    }
}"""

# Actually, the proxy approach is getting complicated. Let me use a simpler approach:
# Change request.js to use a relative path or detect the host dynamically
# and start a second ngrok tunnel for the API

# Actually the SIMPLEST approach: just use a single ngrok for frontend,
# and in request.js, use window.location.origin to detect the current host,
# then derive the API URL from it. But the API is on port 5003...

# BEST SIMPLE APPROACH: Use two CMD windows with ngrok, one for frontend one for API.
# But ngrok free plan only supports one tunnel.

# ACTUAL BEST APPROACH: 
# 1. Keep the dev server (npm run serve) 
# 2. request.js uses window.location.origin + proxy to /api
# 3. All frontend API calls go to /api/* 
# 4. vue.config.js proxies /api to backend
# 5. Single ngrok tunnels everything

# But this requires changing all API calls in all components... too much work.

# SIMPLEST PRACTICAL APPROACH (no code changes needed):
# 1. Before demo, connect to phone hotspot
# 2. Run ipconfig, get new IP
# 3. Update the IP in 2 places (request.js + MedicalOrder.vue)
# 4. Build and run

# OR: just use the phone hotspot approach without QR code:
# Students can just open the URL on their phones manually

# Actually, let me just make request.js use a relative approach
f2 = "D:/codex-workspace/final/his_web/src/request.js"
with open(f2, "r", encoding="utf-8") as fh:
    c2 = fh.read()

# Change the baseURL approach
with open(f2, "r", encoding="utf-8") as fh:
    lines = fh.readlines()

for i, line in enumerate(lines):
    if "baseURL" in line and "5003" in line:
        print(f"Line {i+1}: {line.rstrip()}")
        lines[i] = line.replace("http://10.5.19.145:5003", "http://127.0.0.1:5003")
        with open(f2, "w", encoding="utf-8") as fh:
            fh.writelines(lines)
        print("  → Changed back to 127.0.0.1:5003")
        break

print("\\nNote: For classroom demo, user needs to change IP or use ngrok")
