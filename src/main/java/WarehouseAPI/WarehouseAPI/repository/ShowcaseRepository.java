package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowcaseRepository extends JpaRepository<Showcase,Long> {
    Optional<Showcase> findById(Long id);
}
