package giovxna.posie.controller;

import giovxna.posie.domain.user.User;
import giovxna.posie.dto.LoginRequestDTO;
import giovxna.posie.infra.security.TokenService;
import giovxna.posie.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("user not found"));
        if(passwordEncoder.matches(user.getPassword(), body.password())){
            String token = this.tokenService.generateToken(User);
            return ResponseEntity.ok()
        }

    }
}
