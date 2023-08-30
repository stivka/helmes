package stivka.net.helmes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import stivka.net.helmes.model.RegistrationForm;

public interface RegistrationRepository extends JpaRepository<RegistrationForm, Integer> {

    Optional<RegistrationForm> findByUuid(String uuid);
}
