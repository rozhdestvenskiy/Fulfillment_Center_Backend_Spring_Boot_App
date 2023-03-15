package com.warehouse.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.service.models.ClientStatus;
import com.warehouse.service.models.enums.EnumClientStatus;


@Repository
public interface ClientStatusRepository extends JpaRepository<ClientStatus, Long> {
  Optional<ClientStatus> findByName(EnumClientStatus name);
}
