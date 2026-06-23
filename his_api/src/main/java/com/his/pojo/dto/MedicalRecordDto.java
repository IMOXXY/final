package com.his.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MedicalRecordDto {

    private Integer docId;

    @NotBlank(message = "挂号编号不能为空")
    private String rid;

    private String pid;

    private String pname;

    @NotBlank(message = "诊断编码不能为空")
    private String diagnoseICD;

    @NotBlank(message = "诊断名称不能为空")
    private String diagnoseICDName;

    private String medicalContent;

    private String visitTime;

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDiagnoseICD() {
		return diagnoseICD;
	}

	public void setDiagnoseICD(String diagnoseICD) {
		this.diagnoseICD = diagnoseICD;
	}

	public String getDiagnoseICDName() {
		return diagnoseICDName;
	}

	public void setDiagnoseICDName(String diagnoseICDName) {
		this.diagnoseICDName = diagnoseICDName;
	}

	public String getMedicalContent() {
		return medicalContent;
	}

	public void setMedicalContent(String medicalContent) {
		this.medicalContent = medicalContent;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
    
    
}
