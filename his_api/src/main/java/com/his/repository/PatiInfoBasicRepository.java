package com.his.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.his.pojo.PatiInfoBasic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository  // spring bean
public interface PatiInfoBasicRepository extends CrudRepository<PatiInfoBasic, String> {

	// 根据姓名查询患者
    PatiInfoBasic findByPname(String pname);

    // 根据Pid查询患者
    PatiInfoBasic findByPid(String pname);
    
    // 如果需要根据身份证号查询
    PatiInfoBasic findByPidcard(String pidcard);
    
    
    // 自定义更新：根据 pid 更新电话
    @Transactional
    @Modifying
    @Query("UPDATE PatiInfoBasic p SET p.ptel = :ptel WHERE p.pid = :pid")
    int updatePtelByPid(String pid, String ptel);
    
    // 可以添加更多字段的更新
    @Transactional
    @Modifying
    @Query("UPDATE PatiInfoBasic p SET p.pname = :pname, p.pgender = :pgender WHERE p.pid = :pid")
    int updateInfoByPid(String pid, String pname, String pgender);
    
    // 根据关键词：姓名or身份证or手机号查询患者信息
    @Query("SELECT p FROM PatiInfoBasic p WHERE p.pname LIKE %:keyword% OR p.pidcard LIKE %:keyword% OR p.ptel LIKE %:keyword%")
    List<PatiInfoBasic> searchByKeyword(String keyword);

}
