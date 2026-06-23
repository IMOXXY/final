# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

old = """    mounted() {
        window.title = '\u8d39\u7528\u652f\u4ed8'
        window.outPayV=this
        this.selectedOrders = (this.$route.query.selectedOrders || \x22\x22).split(\x22,\x22).filter(Boolean) 
        this.rid = this.$route.query.rid"""

new = """    mounted() {
        window.title = '\u8d39\u7528\u652f\u4ed8'
        window.outPayV=this
        const token = this.$route.query.token
        if (token) {
            sessionStorage.setItem('token', token)
        }
        this.selectedOrders = (this.$route.query.selectedOrders || \x22\x22).split(\x22,\x22).filter(Boolean) 
        this.rid = this.$route.query.rid"""

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: OutBillPay - token fix applied")
else:
    print("FAIL: pattern not found")
    idx = c.find("mounted")
    if idx >= 0:
        print(repr(c[idx:idx+200]))
