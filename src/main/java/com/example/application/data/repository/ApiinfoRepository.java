
package com.example.application.data.repository;

import com.example.application.data.entity.Apiinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiinfoRepository extends JpaRepository<Apiinfo, Long> {

    @Query("select c from Apiinfo c " +
            "where lower(c.apiName) like lower(concat('%', :searchTerm, '%')) ")
    List<Apiinfo> search(@Param("searchTerm") String searchTerm);
}
