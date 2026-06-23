package com.his.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "doctororder_dict")
public class DoctorOrderDict {

    @Id
    @Column(name = "ordid", length = 20, nullable = false)
    private String ordid;

    @Column(name = "ordername", length = 100, nullable = false)
    private String ordername;

    @Column(name = "ordertype", length = 50, nullable = false)
    private String ordertype;

    @Column(name = "inputstr", length = 50, nullable = false)
    private String inputstr;

    @Column(name = "drugSpecifications", length = 100)
    private String drugSpecifications;

    @Column(name = "takeMedicine", length = 50)
    private String takeMedicine;

    @Column(name = "Frequency", length = 50)
    private String frequency;

    @Column(name = "price")
    private Float price;

    
    
    
	public DoctorOrderDict(String ordid, String ordername, String ordertype, String inputstr, String drugSpecifications,
			String takeMedicine, String frequency, Float price) {
		super();
		this.ordid = ordid;
		this.ordername = ordername;
		this.ordertype = ordertype;
		this.inputstr = inputstr;
		this.drugSpecifications = drugSpecifications;
		this.takeMedicine = takeMedicine;
		this.frequency = frequency;
		this.price = price;
	}
	
	
	

	public DoctorOrderDict() {
		super();
	}




	public String getOrdid() {
		return ordid;
	}

	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}

	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getInputstr() {
		return inputstr;
	}

	public void setInputstr(String inputstr) {
		this.inputstr = inputstr;
	}

	public String getDrugSpecifications() {
		return drugSpecifications;
	}

	public void setDrugSpecifications(String drugSpecifications) {
		this.drugSpecifications = drugSpecifications;
	}

	public String getTakeMedicine() {
		return takeMedicine;
	}

	public void setTakeMedicine(String takeMedicine) {
		this.takeMedicine = takeMedicine;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

    // Getters and Setters
    
}
