package com.alibou.security.auth;

import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.ResetForgottenPasswordDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@Tag(name = "auth", description = "authentication")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResult> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/verify/{code}")
    public RedirectView verify(@PathVariable String code) {
        ApiResult<String> result = service.confirmEmail(code);
        System.out.println(result.getMessage());
        if (result.isSuccess()) {
            return new RedirectView("https://doctor-study.uz/login");
        } else {
            return new RedirectView("https://doctor-study.uz");
        }


    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/forgot-password")
    public ApiResult<RegisterResponse> forgotPassword(@RequestParam String email) {
        return service.forgotPassword(email);
    }

    @PostMapping("/reset-forgotten-password")
    public ApiResult<RegisterResponse> resetForgottenPassword(@RequestBody ResetForgottenPasswordDTO resetForgottenPasswordDTO) {
        return service.resetForgottenPassword(resetForgottenPasswordDTO);
    }


}
