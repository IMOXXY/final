package com.his.pojo.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.*;

public class DiagnoseICDDto {
	
	private String icd;
	private String icdname;
	private String inputstr;
    
    
    public DiagnoseICDDto(String icd, String icdname, String inputstr) {
		super();
		this.icd = icd;
		this.icdname = icdname;
		this.inputstr = inputstr;
	}


	// 必须加一个无参构造器，Spring Data JPA使用new对象时需要
    public DiagnoseICDDto() {
    }


	public String getIcd() {
		return icd;
	}


	public void setIcd(String icd) {
		this.icd = icd;
	}


	public String getIcdname() {
		return icdname;
	}


	public void setIcdname(String icdname) {
		this.icdname = icdname;
	}


	public String getInputstr() {
		return inputstr;
	}


	public void setInputstr(String inputstr) {
		this.inputstr = inputstr;
	}


	@Override
	public String toString() {
		return "DiagnoseICDDto [icd=" + icd + ", icdname=" + icdname + ", inputstr=" + inputstr + "]";
	}
    
	
	
	
    
    
}
