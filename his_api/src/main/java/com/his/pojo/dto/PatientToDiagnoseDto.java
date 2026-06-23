package com.his.pojo.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.*;

public class PatientToDiagnoseDto {
	
	private String pid;
    private String pname;
    private String pidcard;
    private String ptel;
    
    private String rid;
    private String rtype;
    private String dpmtnNme;
    private String doctName;
    private String registDate;
    
    // 必须加一个无参构造器，Spring Data JPA使用new对象时需要
    public PatientToDiagnoseDto() {
    }
    
	public PatientToDiagnoseDto(String pid, String pname, String pidcard, String ptel, String rid, String rtype,
			String dpmtnNme, String doctName, String registDate) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pidcard = pidcard;
		this.ptel = ptel;
		this.rid = rid;
		this.rtype = rtype;
		this.dpmtnNme = dpmtnNme;
		this.doctName = doctName;
		this.registDate = registDate;
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
	public String getPidcard() {
		return pidcard;
	}
	public void setPidcard(String pidcard) {
		this.pidcard = pidcard;
	}
	public String getPtel() {
		return ptel;
	}
	public void setPtel(String ptel) {
		this.ptel = ptel;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
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
	@Override
	public String toString() {
		return "PatientToDiagnoseDto [pid=" + pid + ", pname=" + pname + ", pidcard=" + pidcard + ", ptel=" + ptel
				+ ", rid=" + rid + ", rtype=" + rtype + ", dpmtnNme=" + dpmtnNme + ", doctName=" + doctName
				+ ", registDate=" + registDate + "]";
	}
    
    
    
}
