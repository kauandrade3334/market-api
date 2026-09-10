package com.portifolio.marketAPI.repository;

import com.portifolio.marketAPI.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, String> {

    Optional<Establishment> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
}
