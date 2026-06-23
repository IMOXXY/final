package com.his.service;

import com.his.pojo.PatiInfoBasic;
import com.his.pojo.dto.PatiInfoBasicDto;
import java.util.List;

public interface IPatiInfoBasicService {

    /**
     * 插入患者信息
     *
     * @param patientDto 患者数据传输对象
     * @return 插入后的患者实体
     */
    PatiInfoBasic add(PatiInfoBasicDto patientDto);

    /**
     * 查询患者信息
     *
     * @param pid 病人编号
     * @return 对应的患者实体
     */
    PatiInfoBasic getById(String pid);

    /**
     * 修改患者信息（整体覆盖）
     *
     * @param patientDto 修改后的患者数据
     * @return 修改后的患者实体
     */
    PatiInfoBasic edit(PatiInfoBasicDto patientDto);

    /**
     * 删除患者信息
     *
     * @param pid 病人编号
     */
    void delete(String pid);

    /**
     * 根据 pid 更新患者电话
     *
     * @param pid 病人编号
     * @param ptel 新的电话
     * @return 是否更新成功
     */
    boolean updatePtelByPid(String pid, String ptel);

    /**
     * 根据 pid 更新患者姓名和性别
     *
     * @param pid 病人编号
     * @param pname 姓名
     * @param pgender 性别
     * @return 是否更新成功
     */
    boolean updateInfoByPid(String pid, String pname, String pgender);

    /**
     * 根据姓名、身份证或电话查询患者信息
     *
     * @param keyword 关键词
     * @return 符合条件的患者列表
     */
    List<PatiInfoBasic> searchByKeyword(String keyword);
}
