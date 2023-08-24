package stivka.net.helmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stivka.net.helmes.model.Sector;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
}
