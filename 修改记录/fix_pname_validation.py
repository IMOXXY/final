# -*- coding: utf-8 -*-
"""Fix pname validation - add Chinese messages"""
import os

# Fix 1: Backend DTO - add @NotBlank on pname
f1 = "D:/codex-workspace/final/his_api/src/main/java/com/his/pojo/dto/PatiInfoBasicDto.java"
with open(f1, "r", encoding="utf-8") as f:
    content = f.read()

old = '    @Length(max = 45, message = "姓名不能超过45个字符")\n    private String pname;'
new = '    @NotBlank(message = "患者姓名不能为空")\n    @Length(max = 45, message = "姓名不能超过45个字符")\n    private String pname;'

if old in content:
    content = content.replace(old, new)
    with open(f1, "w", encoding="utf-8") as f:
        f.write(content)
    print("OK: PatiInfoBasicDto.java - added @NotBlank on pname")
else:
    print("FAIL: PatiInfoBasicDto.java - pattern not found")
    # Show the area around pname
    idx = content.find("private String pname;")
    if idx >= 0:
        print(content[idx-100:idx+50])

# Fix 2: Frontend - add validation rules
f2 = "D:/codex-workspace/final/his_web/src/components/PatientBaseInfo.vue"
with open(f2, "r", encoding="utf-8") as f:
    content = f.read()

old2 = "            patientInfoRules: {\n\n            },"
new2 = """            patientInfoRules: {
                pname: [
                    { required: true, message: '请输入患者姓名', trigger: 'blur' }
                ],
                pgender: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                pidcard: [
                    { required: true, message: '请输入身份证号', trigger: 'blur' }
                ]
            },"""

if old2 in content:
    content = content.replace(old2, new2)
    with open(f2, "w", encoding="utf-8") as f:
        f.write(content)
    print("OK: PatientBaseInfo.vue - added validation rules")
else:
    # Try without extra newline
    old2b = "patientInfoRules: {\n\n            },"
    if old2b in content:
        content = content.replace(old2b, new2)
        with open(f2, "w", encoding="utf-8") as f:
            f.write(content)
        print("OK: PatientBaseInfo.vue - added validation rules (alt)")
    else:
        print("FAIL: PatientBaseInfo.vue - pattern not found")
        idx = content.find("patientInfoRules:")
        if idx >= 0:
            print(repr(content[idx:idx+100]))
