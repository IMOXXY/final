# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/components/MedicalOrder.vue"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Restore scoped
c = c.replace("<style>", "<style scoped>")

# Add an unscoped style block at the end for row colors
old_end = "</style>"
new_end = """</style>

<style>
.el-table .row-drug { background-color: #f0f9eb !important; }
.el-table .row-check { background-color: #fdf0e8 !important; }
.el-table .row-treat { background-color: #e8f4fd !important; }
.el-table .row-lab { background-color: #f5e8fd !important; }
</style>"""

c = c.replace(old_end, new_end)

with open(f, "w", encoding="utf-8") as fh:
    fh.write(c)
print("OK: Added unscoped style block for row colors")
