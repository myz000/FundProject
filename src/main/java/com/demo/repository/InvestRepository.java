package com.demo.repository;

import com.demo.entity.Invest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface InvestRepository extends JpaRepository<Invest,Long> {
    @Query("select t from Invest t where t.id = :id")
    Invest findById(@Param("id") long id);

    @Query("select t from Invest t where t.userid = :userid")
    ArrayList findByUserId(@Param("userid") long userid);

    @Query("select t from Invest t where t.userid = :userId and t.state=1")
    ArrayList findInvestedByUserId(@Param("userId") long userId);

    @Query("select t from Invest t where t.userid = :userId and t.state=1 and t.fundcode=:fundcode")
    Invest findInvestedByUserIdFundcode(@Param("userId") long userId, @Param("fundcode") String fundcode);

    @Modifying
    @Transactional
    @Query("update Invest t set t.state = :state where t.userid=:userId and t.fundcode=:fundcode")
    void UpdateStateByUserIdFundCode(@Param("userId") long userId, @Param("fundcode") String fundcode, @Param("state") int state);


}
