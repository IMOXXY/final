package com.his.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class PatiInfoBasicDto {

    @NotBlank(message = "病人编号不能为空")
    @Length(max = 15, message = "病人编号不能超过15个字符")
    private String pid;

    @NotBlank(message = "患者姓名不能为空")
    @Length(max = 45, message = "姓名不能超过45个字符")
    private String pname;

    @Length(max = 45, message = "性别不能超过45个字符")
    private String pgender;

    @Length(max = 45, message = "身份证号不能超过45个字符")
    private String pidcard;

    private String pbirthday;

    @Length(max = 45, message = "电话不能超过45个字符")
    private String ptel;

    // Getters and Setters

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

    public String getPgender() {
        return pgender;
    }

    public void setPgender(String pgender) {
        this.pgender = pgender;
    }

    public String getPidcard() {
        return pidcard;
    }

    public void setPidcard(String pidcard) {
        this.pidcard = pidcard;
    }


    public String getPbirthday() {
		return pbirthday;
	}

	public void setPbirthday(String pbirthday) {
		this.pbirthday = pbirthday;
	}

	public String getPtel() {
        return ptel;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }

    @Override
    public String toString() {
        return "PatiInfoBasicDto{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", pgender='" + pgender + '\'' +
                ", pidcard='" + pidcard + '\'' +
                ", pbirthday=" + pbirthday +
                ", ptel='" + ptel + '\'' +
                '}';
    }
}
