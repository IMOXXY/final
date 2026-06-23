package com.his.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DictDiagnoseICD", 
       indexes = {}, 
       uniqueConstraints = {})
public class DiagnoseICD {

    @Id
    @Column(name = "ICD", nullable = false, length = 15)
    private String icd;

    @Column(name = "ICDName", length = 40)
    private String icdname;

    @Column(name = "InputStr", length = 40)
    private String inputstr;

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
    
    
}
