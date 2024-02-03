package com.alibou.security.auth;

import com.alibou.security.config.JwtService;
import com.alibou.security.config.MessageByLang;
import com.alibou.security.exceptions.RestException;
import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.ResetForgottenPasswordDTO;
import com.alibou.security.service.MailService;
import com.alibou.security.token.Token;
import com.alibou.security.token.TokenRepository;
import com.alibou.security.token.TokenType;
import com.alibou.security.user.Role;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;

    public ApiResult<RegisterResponse> register(RegisterRequest request) {


        if (repository.existsByEmail(request.getEmail())) {
            throw RestException.restThrow(MessageByLang.getMessage("EMAIL_ALREADY_EXISTS"));
        }

        UUID uuid = UUID.randomUUID();
        var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).verificationCode(uuid.toString()).role(Role.USER).build();

        repository.save(user);
        mailService.sendEmailForSignUpConfirmation(request.getEmail(), String.valueOf(uuid));

        return ApiResult.successResponse(new RegisterResponse(MessageByLang.getMessage("OPEN_YOUR_EMAIL_TO_CONFORM_IT")));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        System.out.println(jwtToken + refreshToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }


    public ApiResult<String> confirmEmail(String verificationCode) {
        User user = repository.findByVerificationCode(verificationCode).orElseThrow(() ->
                RestException.restThrow(MessageByLang.getMessage("INVALID_VERIFICATION_CODE")));
        user.setEnabled(true);
        user.setVerificationCode(null);
        repository.save(user);
        return ApiResult.successResponse(MessageByLang.getMessage("USER_SUCCESSFULLY_ENABLED"));
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    public ApiResult<RegisterResponse> forgotPassword(String email) {
        User user = repository.findByEmail(email).orElseThrow(NullPointerException::new);
        String verificationCode = UUID.randomUUID().toString();
        user.setVerificationCode(verificationCode);
        repository.save(user);
        mailService.sendEmailForForForgotPassword(email, verificationCode);
        return ApiResult.successResponse(new RegisterResponse(MessageByLang.getMessage("SUCCESSFULLY_SEND_CODE_TO_EMAIL")));
    }

    public ApiResult<RegisterResponse> resetForgottenPassword(ResetForgottenPasswordDTO resetForgottenPasswordDTO) {
        if (Objects.isNull(resetForgottenPasswordDTO))
            throw RestException.restThrow(MessageByLang.getMessage("REQUEST_DATA_BE_NOT_NULL"));

        if (!Objects.equals(resetForgottenPasswordDTO.getPassword(), resetForgottenPasswordDTO.getPrePassword()))
            return ApiResult.errorResponseWithData(new RegisterResponse(false, MessageByLang.getMessage("PASSWORDS_NOT_EQUAL")));


        Optional<User> userOptional = repository.findByVerificationCode(resetForgottenPasswordDTO.getVerificationCode());
        if (userOptional.isEmpty())
            return ApiResult.errorResponseWithData(RegisterResponse.wrongVerificationCode(MessageByLang.getMessage("INVALID_VERIFICATION_CODE")));

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(resetForgottenPasswordDTO.getPassword()));
        user.setVerificationCode(null);
        repository.save(user);
        return ApiResult.successResponse(new RegisterResponse(MessageByLang.getMessage("PASSWORD_SUCCESSFULLY_CHANGED")));
    }


}
