package stivka.net.helmes.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stivka.net.helmes.model.RegistrationForm;
import stivka.net.helmes.repository.RegistrationRepository;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    public RegistrationForm saveOrUpdateRegistrationForm(RegistrationForm registrationForm) {
        String uuid = registrationForm.getUuid();
        if (uuid == null || uuid.isEmpty()) {
            registrationForm.setUuid(UUID.randomUUID().toString());
            return registrationRepository.save(registrationForm);
        } else {
            RegistrationForm existingForm = registrationRepository.findByUuid(uuid);
            if (existingForm != null) {
                existingForm.setName(registrationForm.getName());
                existingForm.setSectors(registrationForm.getSectors());
                existingForm.setAgreeToTerms(registrationForm.isAgreeToTerms());
                return registrationRepository.save(existingForm);
            } else {
                return registrationRepository.save(registrationForm);
            }
        }
    }

    public RegistrationForm findByUuid(String uuid) {
        return registrationRepository.findByUuid(uuid);
    }
}
