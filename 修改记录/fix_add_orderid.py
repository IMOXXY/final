# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Find the orderData map and add orderid
old = """                ordername: o.ordername,
                ordertype: o.ordertype,
                orderprice: o.orderprice,
                totalOrder: o.totalOrder,
                orderallprice: o.orderallprice"""

new = """                orderid: o.orderid,
                ordername: o.ordername,
                ordertype: o.ordertype,
                orderprice: o.orderprice,
                totalOrder: o.totalOrder,
                orderallprice: o.orderallprice"""

if old in c:
    c = c.replace(old, new)
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Added orderid to embedded data - VERIFY this is in the file!")
    # Verify
    idx = c.find("orderid: o.orderid")
    if idx >= 0:
        print("  Verified! orderid is now in the map.")
    else:
        print("  WARNING: orderid not found after replace!")
else:
    print("FAIL: pattern not found")
    idx = c.find("ordername: o.ordername")
    if idx >= 0:
        print("Found ordername pattern at", idx)
        print(repr(c[idx:idx+200]))
