package com.warehouse.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.service.models.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
