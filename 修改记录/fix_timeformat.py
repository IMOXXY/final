# -*- coding: utf-8 -*-
f = "D:/codex-workspace/final/his_web/src/main.js"
with open(f, "r", encoding="utf-8") as fh:
    c = fh.read()

# Remove hours:minutes:seconds from the format
old = """  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');"""

new = """  // 只保留日期，不包含时间
  // const hours = String(now.getHours()).padStart(2, \'0\');
  // const minutes = String(now.getMinutes()).padStart(2, \'0\');
  // const seconds = String(now.getSeconds()).padStart(2, \'0\');"""

if old in c:
    c = c.replace(old, new)
    # Also fix the return to only use YYYY-MM-DD
    c = c.replace(
        "return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;",
        "return `${year}-${month}-${day}`;"
    )
    with open(f, "w", encoding="utf-8") as fh:
        fh.write(c)
    print("OK: Time format changed to YYYY-MM-DD")
else:
    print("FAIL: Pattern not found")
    idx = c.find("const hours")
    if idx >= 0:
        print(c[idx:idx+200])
