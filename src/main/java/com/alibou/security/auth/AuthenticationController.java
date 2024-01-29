package com.alibou.security.auth;

import com.alibou.security.payload.ApiResult;
import com.alibou.security.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Tag(name = "auth",description = "authentication")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<ApiResult> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/verify/{code}")
    public ResponseEntity<ApiResult<String>> verify(@PathVariable String code) {
        ApiResult<String> result = service.confirmEmail(code);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        service.refreshToken(request, response);

    }


}
