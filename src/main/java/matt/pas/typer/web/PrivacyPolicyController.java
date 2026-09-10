package matt.pas.typer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivacyPolicyController {

    @GetMapping("/polityka-prywatnosci")
    String privacyPolicy () {
        return "privacy-policy";
    }
}
