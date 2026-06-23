package com.his.pojo.dto;

public class StatisticResultDto {
    private Long n;
    private String desc;
    private String code;

    public StatisticResultDto(Long n, String desc, String code) {
        this.n = n;
        this.desc = desc;
        this.code = code;
    }

    // Getter / Setter
    public Long getN() { return n; }
    public void setN(Long n) { this.n = n; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
