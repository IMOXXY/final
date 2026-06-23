package com.his.repository;

import com.his.pojo.DoctorOrderDict;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorOrderDictRepository extends CrudRepository<DoctorOrderDict, String> {

    List<DoctorOrderDict> findAll();

    List<DoctorOrderDict> findByOrdertype(String ordertype);

    List<DoctorOrderDict> findByOrdernameContaining(String keyword);

    List<DoctorOrderDict> findByInputstrContaining(String keyword);

    @Query("SELECT d FROM DoctorOrderDict d WHERE ordertype= :ordertype  and (d.ordername LIKE %:keyword% OR d.inputstr LIKE %:keyword%)")
    List<DoctorOrderDict> searchByKeyword(String ordertype,String keyword);
}
