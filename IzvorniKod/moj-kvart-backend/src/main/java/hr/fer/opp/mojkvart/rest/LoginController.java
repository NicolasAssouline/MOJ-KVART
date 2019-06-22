package hr.fer.opp.mojkvart.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/success")
    public ResponseEntity loginSuccess() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/failure")
    public ResponseEntity loginFail() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
