package stivka.net.helmes.service;

import org.springframework.stereotype.Service;

import stivka.net.helmes.repository.RegistrationRepository;

@Service
public class RegistrationService {
    
    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    

}
