package com.demo.repository;


import com.demo.entity.Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrendRepository extends JpaRepository<Trend, Long> {
    @Query("select t from Trend t where t.id = :id")
    Trend findById(@Param("id") long id);

    @Query("select t from Trend t where t.investid =:investId order by t.currentdate desc")
    List<Trend> findByInvestId(@Param("investId") String investId);

}
