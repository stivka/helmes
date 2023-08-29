package stivka.net.helmes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stivka.net.helmes.model.RegistrationForm;
import stivka.net.helmes.service.RegistrationService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<String> submitForm(@RequestBody RegistrationForm form) {
        try {

            RegistrationForm savedForm = registrationService.saveOrUpdateRegistrationForm(form);

            return ResponseEntity.ok(savedForm.getUuid());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving form");
        }
    }
}
