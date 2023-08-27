package stivka.net.helmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stivka.net.helmes.model.RegistrationForm;

public interface RegistrationRepository extends JpaRepository<RegistrationForm, Integer> {
}
