# -*- coding: utf-8 -*-
"""Fix DiagnoseICDName column length"""

# 1. Fix entity
f1 = "D:/codex-workspace/final/his_api/src/main/java/com/his/pojo/MedicalRecord.java"
with open(f1, "r", encoding="utf-8") as f:
    content = f.read()

old = '@Column(name = "DiagnoseICDName", length = 15, nullable = false)'
new = '@Column(name = "DiagnoseICDName", length = 100, nullable = false)'

if old in content:
    content = content.replace(old, new)
    with open(f1, "w", encoding="utf-8") as f:
        f.write(content)
    print("OK: MedicalRecord.java - DiagnoseICDName length 15->100")
else:
    print("FAIL: MedicalRecord.java - pattern not found")
    idx = content.find("DiagnoseICDName")
    if idx >= 0:
        print(content[idx-50:idx+80])

# 2. Also add @Length on the DTO for consistency
f2 = "D:/codex-workspace/final/his_api/src/main/java/com/his/pojo/dto/MedicalRecordDto.java"
with open(f2, "r", encoding="utf-8") as f:
    content = f.read()

# Check if @Length is imported
if "import org.hibernate.validator.constraints.Length;" not in content:
    content = content.replace(
        "import jakarta.validation.constraints.NotBlank;",
        "import jakarta.validation.constraints.NotBlank;\nimport org.hibernate.validator.constraints.Length;"
    )

old2 = '    @NotBlank(message = "诊断名称不能为空")\n    private String diagnoseICDName;'
new2 = '    @NotBlank(message = "诊断名称不能为空")\n    @Length(max = 100, message = "诊断名称不能超过100个字符")\n    private String diagnoseICDName;'

if old2 in content:
    content = content.replace(old2, new2)
    with open(f2, "w", encoding="utf-8") as f:
        f.write(content)
    print("OK: MedicalRecordDto.java - added @Length on diagnoseICDName")
else:
    print("FAIL: MedicalRecordDto.java - pattern not found")

print("Done")
