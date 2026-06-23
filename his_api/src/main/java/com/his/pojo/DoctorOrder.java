package com.his.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctororder")
public class DoctorOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;

    @Column(name = "Rid", nullable = false, length = 15)
    private String rid;

    @Column(name = "ordername", length = 80)
    private String ordername;

    @Column(name = "ordertype", length = 45)
    private String ordertype;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "prescriptiontime")
    private Date prescriptiontime;

    @Column(name = "orderprice")
    private Float orderprice;

    @Column(name = "ispaid")
    private Boolean ispaid;

    @Column(name = "drugSpecifications", length = 45)
    private String drugSpecifications;

    @Column(name = "orderallprice")
    private Float orderallprice;

    @Column(name = "takeMedicine", length = 45)
    private String takeMedicine;

    @Column(name = "Frequency", length = 45)
    private String frequency;

    @Column(name = "TotalOrder")
    private Integer totalOrder;

    @Column(name = "patiid", nullable = false, length = 45)
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
