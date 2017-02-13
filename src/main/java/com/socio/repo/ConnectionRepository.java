package com.socio.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socio.beans.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long>{

}
