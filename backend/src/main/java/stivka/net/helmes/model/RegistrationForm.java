package stivka.net.helmes.model;

import java.util.List;

import lombok.Data;

@Data
public class RegistrationForm {

    private String name;
    private List<String> sectors;
    private boolean agreeToTerms;
}
