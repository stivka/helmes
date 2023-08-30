package stivka.net.helmes.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import stivka.net.helmes.model.RegistrationForm;
import stivka.net.helmes.repository.RegistrationRepository;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<RegistrationForm> getAllRegistrationForms() {
        return registrationRepository.findAll();
    }

    public RegistrationForm getRegistrationFormByName(String name) {
        return registrationRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("RegistrationForm with name " + name + " not found"));
    }

    @Transactional
    public RegistrationForm saveOrUpdateRegistrationForm(RegistrationForm registrationForm) {
        String uuid = registrationForm.getUuid();
        if (uuid == null || uuid.isEmpty()) {
            registrationForm.setUuid(UUID.randomUUID().toString());
            return registrationRepository.save(registrationForm);
        } else {
            Optional<RegistrationForm> existingFormOpt = registrationRepository.findByUuid(uuid);
            if (existingFormOpt.isPresent()) {
                RegistrationForm existingForm = existingFormOpt.get();
                existingForm.setName(registrationForm.getName());
                existingForm.setSectors(registrationForm.getSectors());
                existingForm.setAgreeToTerms(registrationForm.isAgreeToTerms());
                return registrationRepository.save(existingForm);
            } else {
                registrationForm.setUuid(UUID.randomUUID().toString());
                return registrationRepository.save(registrationForm);
            }
        }
    }

    public RegistrationForm findByUuid(String uuid) {
        Optional<RegistrationForm> registrationFormOpt = registrationRepository.findByUuid(uuid);
        return registrationFormOpt.orElse(null);
    }
}
