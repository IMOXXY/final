package com.his.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medicalrecord")
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocId", nullable = false)
    private Integer docId;

    @Column(name = "Rid", nullable = false, length = 15)
    private String rid;

    @Column(name = "Pid", length = 15)
    private String pid;

    @Column(name = "Pname", length = 45)
    private String pname;

    @Column(name = "DiagnoseICD", length = 15, nullable = false)
    private String diagnoseICD;

    @Column(name = "DiagnoseICDName", length = 15, nullable = false)
    private String diagnoseICDName;

    @Column(name = "MedicalContent", length = 4000)
    private String medicalContent;

    @Column(name = "VisitTime", length = 30)
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
