package com.his.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "hospitaldepartment")
public class HospitalDepartment {

    @Id
    @Column(name = "officeid", length = 10)
    private String officeId;

    @Column(name = "officename", length = 45)
    private String officeName;

    @Column(name = "officetype", length = 45)
    private String officeType;

    // --- Getter & Setter ---

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    @Override
    public String toString() {
        return "HospitalDepartment{" +
                "officeId='" + officeId + '\'' +
                ", officeName='" + officeName + '\'' +
                ", officeType='" + officeType + '\'' +
                '}';
    }
}
