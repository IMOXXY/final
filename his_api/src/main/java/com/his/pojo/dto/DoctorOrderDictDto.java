package com.his.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class DoctorOrderDictDto {

    @NotBlank(message = "医嘱ID不能为空")
    @Length(max = 20)
    private String ordid;

    @NotBlank(message = "医嘱名称不能为空")
    @Length(max = 100)
    private String ordername;

    @NotBlank(message = "医嘱类型不能为空")
    @Length(max = 50)
    private String ordertype;

    @NotBlank(message = "首字母不能为空")
    @Length(max = 50)
    private String inputstr;

    @Length(max = 100)
    private String drugSpecifications;

    @Length(max = 50)
    private String takeMedicine;

    @Length(max = 50)
    private String frequency;

    private Float price;

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
