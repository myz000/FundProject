package com.demo.repository;

import com.demo.entity.LoginTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<LoginTicket,Long> {

    @Query("select t from LoginTicket t where t.ticket = :ticket")
    LoginTicket findByTicket(@Param("ticket") String ticket);
}
