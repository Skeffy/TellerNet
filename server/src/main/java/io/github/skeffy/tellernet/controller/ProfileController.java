package io.github.skeffy.tellernet.controller;

import io.github.skeffy.tellernet.model.Profile;
import io.github.skeffy.tellernet.service.ProfileBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileBuilder profileBuilder;

    public ProfileController(ProfileBuilder profileBuilder) {
        this.profileBuilder = profileBuilder;
    }

    @GetMapping(path = "/customer")
    public Profile getProfileByCustomer(int customerId) {
        return profileBuilder.createProfileByCustomer(customerId);
    }

    @GetMapping(path = "/account")
    public Profile getProfileByAccount(int accountId) {
        return profileBuilder.createProfileByAccount(accountId);
    }
}
