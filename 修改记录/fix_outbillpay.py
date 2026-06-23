# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Fix 1: Handle null selectedOrders
old1 = "this.selectedOrders = this.$route.query.selectedOrders.split(',')"
new1 = "this.selectedOrders = (this.$route.query.selectedOrders || \"\").split(\",\").filter(Boolean)"
c = c.replace(old1, new1)
print("Fix 1 applied")

# Fix 2: After successful payment, set a sync flag in sessionStorage
old2 = """if (success) {
                this.$message.success('\u652f\u4ed8\u6210\u529f\uff01')
                this.orderInfos = []
                setTimeout(() => { window.close() }, 1500)"""
new2 = """if (success) {
                sessionStorage.setItem('payment_sync_'+this.rid, Date.now())
                this.$message.success('\u652f\u4ed8\u6210\u529f\uff01')
                this.orderInfos = []
                setTimeout(() => { window.close() }, 1500)"""

if old2 in c:
    c = c.replace(old2, new2)
    print("Fix 2 applied")
else:
    print("Fix 2: pattern not found")

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("Done")
