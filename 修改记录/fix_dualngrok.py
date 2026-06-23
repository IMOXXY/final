# -*- coding: utf-8 -*-
f = 'D:/codex-workspace/final/his_web/src/components/OutBillPay.vue'
with open(f, 'r', encoding='utf-8') as fh:
    c = fh.read()

old = '                        const resp = await fetch(\"/doctororder/pay/\"+o.orderid, {method: \"GET\"})'
new = '                        const ngrokBase = window.location.origin\n                        const resp = await fetch(ngrokBase+\"/doctororder/pay/\"+o.orderid, {method: \"GET\"})'

if old in c:
    c = c.replace(old, new)
    with open(f, 'w', encoding='utf-8') as fh:
        fh.write(c)
    print('OK: Using full URL for pay call')
else:
    print('FAIL')
    idx = c.find('fetch(')
    if idx >= 0:
        print(repr(c[idx:idx+120]))
