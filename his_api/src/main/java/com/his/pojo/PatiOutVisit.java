package com.his.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "pati_out_visit")
public class PatiOutVisit {

    @Id
    @Column(name = "Rid", nullable = false, length = 15)
    private String rid;

    @Column(name = "Pid", length = 20)
    private String pid;

    @Column(name = "Rtype", length = 45)
    private String rtype;

    @Column(name = "DpmtnNme", length = 45)
    private String dpmtnNme;

    @Column(name = "DoctName", length = 45)
    private String doctName;

    @Column(name = "RegistDate", length = 45)
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
        return "PatiOutVisit{" +
                "rid='" + rid + '\'' +
                ", pid='" + pid + '\'' +
                ", rtype='" + rtype + '\'' +
                ", dpmtnNme='" + dpmtnNme + '\'' +
                ", doctName='" + doctName + '\'' +
                ", registDate='" + registDate + '\'' +
                '}';
    }
}
