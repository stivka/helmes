package stivka.net.helmes.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RegistrationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private String name;
    private List<String> sectors;
    private boolean agreeToTerms;
}
