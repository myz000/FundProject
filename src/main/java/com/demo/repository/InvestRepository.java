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
public interface InvestRepository extends JpaRepository<Invest, Long> {
    @Query("select t from Invest t where t.id =:id")
    Invest findById(@Param("id") String id);

    @Query("select t from Invest t where t.userid = :userid")
    ArrayList findByUserId(@Param("userid") long userid);

    @Modifying
    @Transactional
    @Query("update Invest t set t.state = :state where t.id=:id")
    void UpdateStateById(@Param("id") String id, @Param("state") int state);


}
