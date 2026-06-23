package com.his.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lab_check")
public class LabCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkId;

    @Column(name = "Rid", nullable = false, length = 15)
    private String rid;

    @Column(name = "Pid", nullable = false, length = 20)
    private String pid;

    @Column(name = "Pname", length = 45)
    private String pname;

    @Column(name = "DoctName", length = 45)
    private String doctName;

    @Column(name = "CheckItem", length = 80)
    private String checkItem;

    @Column(name = "Specimen", length = 45)
    private String specimen;

    @Column(name = "ClinicalDiagnosis", length = 120)
    private String clinicalDiagnosis;

    @Column(name = "ApplyStatus", length = 20)
    private String applyStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ApplyTime")
    private Date applyTime;

    @Column(name = "ResultValue", length = 500)
    private String resultValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ResultTime")
    private Date resultTime;

    @Column(name = "ResultDoctor", length = 45)
    private String resultDoctor;

    @Column(name = "Remark", length = 200)
    private String remark;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
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

    public String getDoctName() {
        return doctName;
    }

    public void setDoctName(String doctName) {
        this.doctName = doctName;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    public String getSpecimen() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen = specimen;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    public String getResultDoctor() {
        return resultDoctor;
    }

    public void setResultDoctor(String resultDoctor) {
        this.resultDoctor = resultDoctor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
