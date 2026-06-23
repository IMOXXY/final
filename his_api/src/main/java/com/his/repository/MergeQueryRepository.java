package com.his.repository;
import com.his.pojo.dto.StatisticResultDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.his.pojo.dto.PatientToDiagnoseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.his.pojo.PatiOutVisit;
import java.util.List;

@Repository
public interface MergeQueryRepository extends CrudRepository<PatiOutVisit, String> {

	@Query("SELECT new com.his.pojo.dto.PatientToDiagnoseDto(p.pid, p.pname, p.pidcard, p.ptel, v.rid, v.rtype, v.dpmtnNme, v.doctName, v.registDate) " +
	           "FROM PatiOutVisit v " +
	           "INNER JOIN PatiInfoBasic p ON v.pid = p.pid " +
	           "WHERE FUNCTION('DATE_FORMAT', CURRENT_DATE, '%Y-%m-%d') = v.registDate AND v.doctName = :doctName")
    List<PatientToDiagnoseDto> findPatientToDiagnose(@Param("doctName") String doctName);
	    
	@Query("SELECT new com.his.pojo.dto.PatientToDiagnoseDto(p.pid, p.pname, p.pidcard, p.ptel, v.rid, v.rtype, v.dpmtnNme, v.doctName, v.registDate) " +
	           "FROM PatiOutVisit v " +
	           "INNER JOIN PatiInfoBasic p ON v.pid = p.pid " +
	           "INNER JOIN MedicalRecord r ON v.rid = r.rid " +
	           "WHERE FUNCTION('DATE_FORMAT', CURRENT_DATE, '%Y-%m-%d') = v.registDate AND v.doctName = :doctName")
    List<PatientToDiagnoseDto> findPatientToOrder(@Param("doctName") String doctName);
	    
	    
    @Query(value="select p.pid,p.pname,p.pidcard,p.ptel,v.rid,v.rtype,v.dpmtnNme,v.doctName,v.registDate from pati_out_visit v inner join pati_info_basic p on v.pid=p.pid and v.registDate=CURDATE() where doctName=:doctName", nativeQuery = true)
    List<Object[]> findPatientToDiagnose2(@Param("doctName") String doctName);
    
    
    @Query(value = """
            select count(*) n,'总建档人数' as `desc`, '01' as code from pati_info_basic
            union all 
            select count(*) n,'当日就诊人数' as `desc`, '02' as code from pati_out_visit where RegistDate = CURDATE()
            union all 
            select count(*) n,'当日检查人数' as `desc`, '03' as code from doctororder where ordertype='检查' and substr(prescriptiontime,1,10)=CURDATE()
            union all 
            select count(*) n,'当日处方数' as `desc`, '04' as code from doctororder where ordertype='药品' and substr(prescriptiontime,1,10)=CURDATE()
            union all 
            select * from (
                select count(*) n,RegistDate as `desc`, '05' as code
                from pati_out_visit
                where RegistDate in (:regdates)
                group by RegistDate
                order by RegistDate
            ) a
            union all
            select count(*) n, ordertype as `desc`, '06' as code from doctororder group by ordertype
            union all
            select * from (
                select count(distinct pid) n, DpmtnNme as `desc`, '07' as code
                from pati_out_visit
                group by DpmtnNme
                order by n desc
            ) b
            """, nativeQuery = true)
    List<Object[]> getStatisticsRaw(@Param("regdates") List<String> regdates);
}
