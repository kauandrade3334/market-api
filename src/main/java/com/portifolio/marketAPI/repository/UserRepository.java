package com.portifolio.marketAPI.repository;

import com.portifolio.marketAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByIdAndEstablishmentId(String id, String establishmentId);
}
