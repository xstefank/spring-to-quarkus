package org.acme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvengerRepository extends JpaRepository<Avenger, Long> {
    Optional<Avenger> findByName(String name);
}
