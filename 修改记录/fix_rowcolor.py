# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Remove scoped from style, add a separate unscoped style block for row colors
old = "<style scoped>"
new = "<style>"
c = c.replace(old, new)

# Move the row color styles outside scoped by keeping them in the main style block
# (They'll be in the un-scoped block, so they work globally)

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Removed scoped from styles")
