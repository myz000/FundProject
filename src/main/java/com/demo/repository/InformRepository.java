package com.demo.repository;

import com.demo.entity.Inform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InformRepository extends JpaRepository<Inform, Long> {
    @Query("select t from Inform t where t.id = :id")
    Inform findById(@Param("id") long id);

    @Query("select t from Inform t order by time desc")
    ArrayList<Inform> findList();
}
