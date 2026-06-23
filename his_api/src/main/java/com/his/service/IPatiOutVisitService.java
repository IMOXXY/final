package com.his.service;

import com.his.pojo.PatiOutVisit;
import com.his.pojo.dto.PatiOutVisitDto;
import java.util.List;
public interface IPatiOutVisitService {
    /**
     * 插入挂号
     *
     * @param PatiOutVisit 参数
     * @return
     */
    PatiOutVisit add(PatiOutVisitDto PatiOutVisit);

    /**
     * 查询挂号
     * @param PatiOutVisitId 挂号id
     * @return 
     */
    PatiOutVisit getPatiOutVisit(String PatiOutVisitId);


    /**
     * 根据挂号医生姓名查询
     * @param doctName 医生姓名
     * @return
     */
     List<PatiOutVisit> findAllByDoctName(String doctName);

    
    
    /**
     * 修改挂号
     * @param PatiOutVisit 修改挂号对象
     * @return
     */
    PatiOutVisit edit(PatiOutVisitDto PatiOutVisit);
    

    /**
     * 删除
     *
     * @param PatiOutVisitId
     */
    void delete(String PatiOutVisitId);
}
