package com.his.pojo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class PatiOutVisitDto {

    @NotBlank(message = "挂号编码不能为空")
    @Length(max = 15, message = "挂号编码不能超过15个字符")
    private String rid;

    @Length(max = 20, message = "病人编号不能超过20个字符")
    private String pid;

    @Length(max = 45, message = "挂号类型不能超过45个字符")
    private String rtype;

    @Length(max = 45, message = "科室名称不能超过45个字符")
    private String dpmtnNme;

    @Length(max = 45, message = "医生姓名不能超过45个字符")
    @Column(name = "DoctName", length = 45)
    private String doctName;

    @Length(max = 45, message = "挂号日期不能超过45个字符")
    private String registDate;

    // Getters and Setters

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

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getDpmtnNme() {
        return dpmtnNme;
    }

    public void setDpmtnNme(String dpmtnNme) {
        this.dpmtnNme = dpmtnNme;
    }

    public String getDoctName() {
        return doctName;
    }

    public void setDoctName(String doctName) {
        this.doctName = doctName;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    // toString 方法

    @Override
    public String toString() {
        return "PatiOutVisitDTO{" +
                "rid='" + rid + '\'' +
                ", pid='" + pid + '\'' +
                ", rtype='" + rtype + '\'' +
                ", dpmtnNme='" + dpmtnNme + '\'' +
                ", doctName='" + doctName + '\'' +
                ", registDate='" + registDate + '\'' +
                '}';
    }
}
