package com.code.with.bisky.api;


import com.code.with.bisky.dto.UserRegistrationRecord;
import com.code.with.bisky.service.keycloak.KeycloakUserService;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class KeycloakUserApi {


    private final KeycloakUserService keycloakUserService;


    @PostMapping
    public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord userRegistrationRecord) {

        return keycloakUserService.createUser(userRegistrationRecord);
    }

    @GetMapping
    public UserRepresentation getUser(Principal principal) {

        return keycloakUserService.getUserById(principal.getName());
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        keycloakUserService.deleteUserById(userId);
    }


    @PutMapping("/{userId}/send-verify-email")
    public void sendVerificationEmail(@PathVariable String userId) {
        keycloakUserService.emailVerification(userId);
    }
    @PutMapping("/update-password")
    public void updatePassword(Principal principal) {
        keycloakUserService.updatePassword(principal.getName());
    }
}
