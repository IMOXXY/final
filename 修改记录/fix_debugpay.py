# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/OutBillPay.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Add try-catch and more detailed error handling to outPay
old = """async outPay() {
            let success = true
            for (let o of this.orderInfos) {
                const {data:res} = await this.$http.put('/doctororder/pay/'+o.orderid)
                if(res.code !== 200){ success = false }
            }"""

new = """async outPay() {
            let success = true
            for (let o of this.orderInfos) {
                try {
                    console.log("Paying order:", o.orderid)
                    const resp = await this.$http.put('/doctororder/pay/'+o.orderid)
                    console.log("Pay response:", resp)
                    if (resp.data && resp.data.code === 200) {
                        console.log("Order", o.orderid, "paid successfully")
                    } else {
                        console.error("Pay failed for order", o.orderid, resp)
                        success = false
                    }
                } catch (err) {
                    console.error("Pay error for order", o.orderid, err)
                    success = false
                }
            }"""

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Added detailed logging to outPay")
else:
    print("FAIL")
    idx = c.find("async outPay")
    if idx >= 0:
        print(repr(c[idx:idx+200]))
