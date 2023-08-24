package stivka.net.helmes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stivka.net.helmes.model.RegistrationForm;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    // TODO: implement the form submission
    @PostMapping
    public ResponseEntity<String> submitForm(@RequestBody RegistrationForm form) {
        System.out.println(form);
        return ResponseEntity.ok("Form submitted successfully!");
    }
}
