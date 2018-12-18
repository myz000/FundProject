package com.demo.repository;


import com.demo.entity.Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;

public interface TrendRepository extends JpaRepository<Trend, Long> {
    @Query("select t from Trend t where t.id = :id")
    Trend findById(@Param("id") long id);

    @Query("select t from Trend t where t.userid = :userId")
    ArrayList findByUserId(@Param("userId") long userId);

    @Query("select t from Trend t where t.userid = :userId and t.fundcode=:fundcode and t.state=1")
    Trend findLatestByUserIdFundCode(
            @Param("userId") long userId,
            @Param("fundcode") String fundcode);

    @Query("select t from Trend t where t.investid=:investId and t.currentdate=(select max(t.currentdate) from Trend t where t.investid=:investId)")
    Trend findLatestByInvestId(@Param("investId") String investId);

    @Modifying
    @Transactional
    @Query("update Trend t set t.state = :state where t.userid=:userId and t.fundcode=:fundcode")
    void UpdateStateByUserIdFundCode(
            @Param("userId") long userId,
            @Param("fundcode") String fundcode,
            @Param("state") int state);
}
