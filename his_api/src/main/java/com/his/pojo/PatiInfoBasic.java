package com.his.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pati_info_basic", 
       indexes = {}, 
       uniqueConstraints = {})
public class PatiInfoBasic {

    @Id
    @Column(name = "Pid", nullable = false, length = 15)
    private String pid;

    @Column(name = "Pname", length = 45)
    private String pname;

    @Column(name = "Pgender", length = 45)
    private String pgender;

    @Column(name = "Pidcard", length = 45)
    private String pidcard;

    @Column(name = "Pbirthday")
    private String pbirthday;

    @Column(name = "Ptel", length = 45)
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
}
