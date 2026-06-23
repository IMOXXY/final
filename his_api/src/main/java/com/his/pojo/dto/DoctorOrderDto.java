package com.his.pojo.dto;

import java.util.Date;

public class DoctorOrderDto {

    private Integer orderid;
    private String rid;
    private String ordername;
    private String ordertype;
    private Date prescriptiontime;
    private Float orderprice;
    private Boolean ispaid;
    private String drugSpecifications;
    private Float orderallprice;
    private String takeMedicine;
    private String frequency;
    private Integer totalOrder;
    private String patiid;
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
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
	public Date getPrescriptiontime() {
		return prescriptiontime;
	}
	public void setPrescriptiontime(Date prescriptiontime) {
		this.prescriptiontime = prescriptiontime;
	}
	public Float getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(Float orderprice) {
		this.orderprice = orderprice;
	}
	public Boolean getIspaid() {
		return ispaid;
	}
	public void setIspaid(Boolean ispaid) {
		this.ispaid = ispaid;
	}
	public String getDrugSpecifications() {
		return drugSpecifications;
	}
	public void setDrugSpecifications(String drugSpecifications) {
		this.drugSpecifications = drugSpecifications;
	}
	public Float getOrderallprice() {
		return orderallprice;
	}
	public void setOrderallprice(Float orderallprice) {
		this.orderallprice = orderallprice;
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
	public Integer getTotalOrder() {
		return totalOrder;
	}
	public void setTotalOrder(Integer totalOrder) {
		this.totalOrder = totalOrder;
	}
	public String getPatiid() {
		return patiid;
	}
	public void setPatiid(String patiid) {
		this.patiid = patiid;
	}

    // Getters and Setters
    
}
